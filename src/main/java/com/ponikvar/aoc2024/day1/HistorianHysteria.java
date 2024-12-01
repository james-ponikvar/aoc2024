package com.ponikvar.aoc2024.day1;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.LineProcessor;
import com.google.common.io.MoreFiles;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class HistorianHysteria {

  private static final Pattern LINE_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)$");

  int similarity(Path input) throws IOException {
    return solve(input, new SimilarityProcessor());
  }

  int distance(Path input) throws IOException {
    return solve(input, new DistanceProcessor());
  }

  private int solve(Path input, LineProcessor<Integer> processor) throws IOException {
    MoreFiles.asCharSource(input, Charsets.UTF_8).readLines(processor);
    return processor.getResult();
  }

  private static final class SimilarityProcessor implements LineProcessor<Integer> {
    private final List<Integer> left = Lists.newArrayList();
    private final Map<Integer, Integer> right = Maps.newHashMap();

    @Override
    public boolean processLine(String line) throws IOException {
      Matcher matcher = LINE_PATTERN.matcher(line);
      if (matcher.matches()) {
        left.add(Integer.valueOf(matcher.group(1)));
        right.compute(Integer.valueOf(matcher.group(2)), (k, v) -> v == null ? 1 : v + 1);
      }
      return true;
    }

    @Override
    public Integer getResult() {
      return left.stream()
          .reduce(0, (total, element) -> right.getOrDefault(element, 0) * element + total);
    }
  }

  private static final class DistanceProcessor implements LineProcessor<Integer> {
    private final List<Integer> left = new ArrayList<>();
    private final List<Integer> right = new ArrayList<>();

    @Override
    public boolean processLine(String line) throws IOException {
      Matcher matcher = LINE_PATTERN.matcher(line);
      if (matcher.matches()) {
        left.add(Integer.valueOf(matcher.group(1)));
        right.add(Integer.valueOf(matcher.group(2)));
      }
      return true;
    }

    @Override
    public Integer getResult() {
      Collections.sort(left);
      Collections.sort(right);
      int distance = 0;
      for (int i = 0; i < left.size() && i < right.size(); i++) {
        distance += Math.abs(left.get(i) - right.get(i));
      }
      return distance;
    }
  }
}
