package com.ponikvar.aoc2024.day3;

import com.google.common.io.LineProcessor;
import com.google.common.io.MoreFiles;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MullItOver {

  static MullItOver standard() {
    return new MullItOver(new MemoryProcessor());
  }

  static MullItOver conditional() {
    return new MullItOver(new ConditionalMemoryProcessor());
  }

  private final LineProcessor<Integer> processor;

  MullItOver(LineProcessor<Integer> processor) {
    this.processor = processor;
  }

  int solve(Path input) throws IOException {
    MoreFiles.asCharSource(input, StandardCharsets.UTF_8).readLines(processor);
    return processor.getResult();
  }

  private static class ConditionalMemoryProcessor implements LineProcessor<Integer> {
    private final MemoryProcessor delegate = new MemoryProcessor();
    private boolean enabled = true;

    @Override
    public boolean processLine(String line) throws IOException {
      int left = 0;
      int right = 0;

      while (left != -1 && right != -1) {
        if (enabled) {
          right = line.indexOf("don't()", left);
          if (right != -1) {
            delegate.processLine(line.substring(left, right));
            enabled = false;
          } else {
            delegate.processLine(line.substring(left));
          }
        } else {
          left = line.indexOf("do()", right);
          if (left != -1) {
            enabled = true;
          }
        }
      }
      return true;
    }

    @Override
    public Integer getResult() {
      return delegate.getResult();
    }
  }

  private static class MemoryProcessor implements LineProcessor<Integer> {
    private static final Pattern PATTERN = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

    private int sum = 0;

    @Override
    public boolean processLine(String line) throws IOException {
      Matcher matcher = PATTERN.matcher(line);
      while (matcher.find()) {
        sum += (Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2)));
      }
      return true;
    }

    @Override
    public Integer getResult() {
      return sum;
    }
  }
}
