package com.ponikvar.aoc2024.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

class CeresSearch {
  int solve(Path input) throws IOException {
    List<String> puzzle;
    try (Stream<String> lines = Files.lines(input)) {
      puzzle = lines.toList();
    }
    int count = 0;
    for (int y = 1; y < puzzle.size() - 1; y++) {
      for (int x = 1; x < puzzle.get(y).length() - 1; x++) {
        if (puzzle.get(y).charAt(x) == 'A') {
          count += isXmas(puzzle, y, x);
        }
      }
    }
    return count;
  }

  private int isXmas(List<String> puzzle, int y, int x) {
    char topL = puzzle.get(y - 1).charAt(x - 1);
    char botR = puzzle.get(y + 1).charAt(x + 1);
    char topR = puzzle.get(y - 1).charAt(x + 1);
    char botL = puzzle.get(y + 1).charAt(x - 1);
    return ((topL == 'M' && botR == 'S') || (topL == 'S' && botR == 'M'))
            && ((topR == 'M' && botL == 'S') || (topR == 'S' && botL == 'M'))
        ? 1
        : 0;
  }
}
