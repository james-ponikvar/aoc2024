package com.ponikvar.aoc2024.day4;

import static org.junit.jupiter.api.Assertions.*;

import com.ponikvar.aoc2024.BaseTest;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class CeresSearchTest extends BaseTest {

  @Test
  void solve() throws IOException {
    Path input = getPath("day4-input.txt");
    CeresSearch solver = new CeresSearch();
    assertEquals(1807, solver.solve(input));
  }
}
