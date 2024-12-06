package com.ponikvar.aoc2024.day5;

import static org.junit.jupiter.api.Assertions.*;

import com.ponikvar.aoc2024.BaseTest;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class PrintQueueTest extends BaseTest {
  @Test
  void solve() throws IOException {
    Path input = getPath("day5-input.txt");
    PrintQueue solver = new PrintQueue();
    assertEquals(5799, solver.solve(input));
  }
}
