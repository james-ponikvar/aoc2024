package com.ponikvar.aoc2024.day1;

import static org.junit.jupiter.api.Assertions.*;

import com.ponikvar.aoc2024.BaseTest;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class HistorianHysteriaTest extends BaseTest {
  HistorianHysteria solver = new HistorianHysteria();

  @Test
  void distance() throws IOException {
    Path input = getPath("day1-input.txt");
    assertEquals(1873376, solver.distance(input));
  }

  @Test
  void distanceSample() throws IOException {
    Path input = getPath("day1-sample.txt");
    assertEquals(11, solver.distance(input));
  }

  @Test
  void similarity() throws IOException {
    Path input = getPath("day1-input.txt");
    assertEquals(18997088, solver.similarity(input));
  }

  @Test
  void similaritySample() throws IOException {
    Path input = getPath("day1-sample.txt");
    assertEquals(31, solver.similarity(input));
  }
}
