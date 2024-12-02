package com.ponikvar.aoc2024.day2;

import static org.junit.jupiter.api.Assertions.*;

import com.ponikvar.aoc2024.BaseTest;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class RedNosedReportsTest extends BaseTest {

  @Test
  void countSafe() throws IOException {
    RedNosedReports solver = RedNosedReports.standard();
    Path input = getPath("day2-input.txt");
    assertEquals(321, solver.countSafe(input));
  }

  @Test
  void countSafeSample() throws IOException {
    RedNosedReports solver = RedNosedReports.standard();
    Path input = getPath("day2-sample.txt");
    assertEquals(2, solver.countSafe(input));
  }

  @Test
  void countSafeWithProblemDampener() throws IOException {
    RedNosedReports solver = RedNosedReports.problemDampener();
    Path input = getPath("day2-input.txt");
    assertEquals(386, solver.countSafe(input));
  }

  @Test
  void countSafeSampleWithProblemDampener() throws IOException {
    RedNosedReports solver = RedNosedReports.problemDampener();
    Path input = getPath("day2-sample.txt");
    assertEquals(4, solver.countSafe(input));
  }
}
