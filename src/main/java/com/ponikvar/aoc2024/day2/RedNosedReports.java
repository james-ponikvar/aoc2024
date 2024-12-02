package com.ponikvar.aoc2024.day2;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.LineProcessor;
import com.google.common.io.MoreFiles;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

class RedNosedReports {

  static RedNosedReports standard() {
    return new RedNosedReports(new ReportProcessor(new SafetyInspector.Standard()));
  }

  static RedNosedReports problemDampener() {
    return new RedNosedReports(new ReportProcessor(new SafetyInspector.ProblemDampener()));
  }

  private final ReportProcessor processor;

  RedNosedReports(ReportProcessor processor) {
    this.processor = processor;
  }

  int countSafe(Path input) throws IOException {
    MoreFiles.asCharSource(input, StandardCharsets.UTF_8).readLines(processor);
    return processor.getResult();
  }

  private static final class ReportProcessor implements LineProcessor<Integer> {
    private static final Splitter SPLITTER =
        Splitter.on(CharMatcher.whitespace()).omitEmptyStrings();

    private final SafetyInspector inspector;
    private int safe = 0;

    private ReportProcessor(SafetyInspector inspector) {
      this.inspector = inspector;
    }

    @Override
    public boolean processLine(String line) throws IOException {
      var report = SPLITTER.splitToStream(line).map(Integer::valueOf).toList();
      if (inspector.isSafe(report)) {
        safe += 1;
      }
      return true;
    }

    @Override
    public Integer getResult() {
      return safe;
    }
  }

  abstract static sealed class SafetyInspector {
    abstract boolean isSafe(List<Integer> report);

    static final class Standard extends SafetyInspector {
      @Override
      public boolean isSafe(List<Integer> report) {
        var sign = report.get(1) - report.get(0);
        return isSafe(report, 1, sign);
      }

      private boolean isSafe(List<Integer> report, int index, int sign) {
        if (index == report.size()) {
          return true;
        }
        var prev = report.get(index - 1);
        var next = report.get(index);
        var diff = next - prev;
        return diff != 0
            && Math.abs(diff) <= 3
            && (sign ^ diff) >= 0
            && isSafe(report, index + 1, sign);
      }
    }

    static final class ProblemDampener extends SafetyInspector {
      private final SafetyInspector standard = new Standard();

      @Override
      public boolean isSafe(List<Integer> report) {
        return standard.isSafe(report)
            || IntStream.range(0, report.size())
                .anyMatch(index -> standard.isSafe(slice(report, index)));
      }
    }

    protected List<Integer> slice(List<Integer> report, int index) {
      var copy = Lists.newArrayList(report);
      copy.remove(index);
      return copy;
    }
  }
}
