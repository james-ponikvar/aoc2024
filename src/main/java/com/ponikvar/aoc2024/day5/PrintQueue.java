package com.ponikvar.aoc2024.day5;

import com.google.common.collect.Sets;
import com.google.common.io.LineProcessor;
import com.google.common.io.MoreFiles;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

class PrintQueue {
  int solve(Path input) throws IOException {
    RuleAndUpdateProcessor processor = new RuleAndUpdateProcessor();
    MoreFiles.asCharSource(input, StandardCharsets.UTF_8).readLines(processor);
    return processor.getResult();
  }

  static class RuleAndUpdateProcessor implements LineProcessor<Integer> {
    private final RuleProcessor ruleProcessor = new RuleProcessor();
    private UpdateProcessor updateProcessor;
    private boolean updates = false;

    @Override
    public boolean processLine(String line) throws IOException {
      if (line.isBlank()) {
        updates = true;
        updateProcessor = new UpdateProcessor(ruleProcessor.getResult());
      } else if (updates) {
        updateProcessor.processLine(line);
      } else {
        ruleProcessor.processLine(line);
      }
      return true;
    }

    @Override
    public Integer getResult() {
      return updateProcessor.getResult();
    }
  }

  static class RuleProcessor implements LineProcessor<Set<Rule>> {
    private final Set<Rule> rules = Sets.newHashSet();

    @Override
    public boolean processLine(String line) throws IOException {
      rules.add(Rule.parse(line));
      return true;
    }

    @Override
    public Set<Rule> getResult() {
      return rules;
    }
  }

  static class UpdateProcessor implements LineProcessor<Integer> {
    private final Comparator<Integer> ruleComparator;
    private int count = 0;

    UpdateProcessor(Set<Rule> rules) {
      this.ruleComparator = new RuleComparator(rules);
    }

    @Override
    public boolean processLine(String line) throws IOException {
      List<Integer> updates = parseUpdates(line);
      List<Integer> sorted = new ArrayList<>(updates);
      sorted.sort(ruleComparator);
      if (!updates.equals(sorted)) {
        count += sorted.get(sorted.size() / 2);
      }
      return true;
    }

    @Override
    public Integer getResult() {
      return count;
    }

    private List<Integer> parseUpdates(String line) {
      return Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
    }
  }

  record RuleComparator(Set<Rule> rules) implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
      return a.equals(b) ? 0 : rules.contains(new Rule(a, b)) ? -1 : 1;
    }
  }

  record Rule(int x, int y) {
    static Rule parse(String line) {
      String[] parts = line.split("\\|");
      return new Rule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
  }
}
