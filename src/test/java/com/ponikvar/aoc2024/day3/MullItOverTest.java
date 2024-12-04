package com.ponikvar.aoc2024.day3;

import static org.junit.jupiter.api.Assertions.*;

import com.ponikvar.aoc2024.BaseTest;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class MullItOverTest extends BaseTest {
  @Test
  void solve() throws IOException {
    Path input = getPath("day3-input.txt");
    MullItOver solver = MullItOver.standard();
    assertEquals(190604937, solver.solve(input));
  }

  @Test
  void solveConditional() throws IOException {
    Path input = getPath("day3-input.txt");
    MullItOver solver = MullItOver.conditional();
    assertEquals(82857512, solver.solve(input));
  }
}
