package com.codetaylor.mc.artisantools.material;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

public class ConfigFilePathSupplier
    implements Supplier<Path> {

  private final Path path;
  private final String fileName;

  public ConfigFilePathSupplier(Path path, String fileName) {

    this.path = path;
    this.fileName = fileName;
  }

  @Override
  public Path get() {

    return Paths.get(this.path.toString(), this.fileName);
  }
}
