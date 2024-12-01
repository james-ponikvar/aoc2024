package com.ponikvar.aoc2024;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

public class BaseTest {
  public final Path getPath(String filename) {
    Path resources = Paths.get("src", "test", "resources");
    requireNonNull(resources);
    return resources.resolve(filename);
  }
}
