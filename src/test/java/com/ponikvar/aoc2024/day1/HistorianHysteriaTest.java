package com.ponikvar.aoc2024.day1;

import com.ponikvar.aoc2024.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class HistorianHysteriaTest extends BaseTest {
  HistorianHysteria solver;

  @BeforeEach
  void setUp() {
    solver = new HistorianHysteria();
  }

  @Test
  void distance() throws IOException {
    Path input = getPath("day1-input.txt");
    int size = 1000;
    assertEquals(1873376, solver.distance(input, size));
  }

  @Test
  void distanceSample() throws IOException {
    Path input = getPath("day1-sample.txt");
    int size = 6;
    assertEquals(11, solver.distance(input, size));
  }

  @Test
  void similarity() throws IOException {
    Path input = getPath("day1-input.txt");
    int size = 1000;
    assertEquals(18997088, solver.similarity(input, size));
  }

  @Test
  void similaritySample() throws IOException {
    Path input = getPath("day1-sample.txt");
    int size = 6;
    assertEquals(31, solver.similarity(input, size));
  }
}
