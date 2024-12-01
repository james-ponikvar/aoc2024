package com.ponikvar.aoc2024.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class HistorianHysteria {

  private static final Pattern LINE_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)$");

  int similarity(Path input, int size) throws IOException {
    // read the file into two lists
    List<Integer> left = new ArrayList<>(size);
    Map<Integer, Integer> right = new HashMap<>();

    try (Stream<String> lines = Files.lines(input)) {
      lines.forEach(line -> {
        Matcher matcher = LINE_PATTERN.matcher(line);
        if (matcher.matches()) {
          left.add(Integer.valueOf(matcher.group(1)));
          right.compute(
              Integer.valueOf(matcher.group(2)),
              (k, v) -> v == null ? 1 : v + 1
          );
        }
      });
    }

    return left.stream().reduce(0, (total, element) ->
      right.getOrDefault(element, 0) * element + total
    );
  }

  int distance(Path input, int size) throws IOException {
    // read the file into two lists
    List<Integer> left = new ArrayList<>(size);
    List<Integer> right = new ArrayList<>(size);

    try (Stream<String> lines = Files.lines(input)) {
      lines.forEach(line -> {
        Matcher matcher = LINE_PATTERN.matcher(line);
        if (matcher.matches()) {
          left.add(Integer.valueOf(matcher.group(1)));
          right.add(Integer.valueOf(matcher.group(2)));
        }
      });
    }

    // sort the lists
    Collections.sort(left);
    Collections.sort(right);

    // find the distance
    int distance = 0;

    for (int i = 0; i < left.size() && i < right.size(); i++) {
      distance += Math.abs(left.get(i) - right.get(i));
    }

    return distance;
  }
}
