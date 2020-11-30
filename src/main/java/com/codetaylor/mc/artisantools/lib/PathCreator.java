package com.codetaylor.mc.artisantools.lib;

import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathCreator {

  private final Logger logger;
  private final Path path;

  public PathCreator(Path path, Logger logger) {

    this.logger = logger;
    this.path = path;
  }

  public boolean create() {

    try {
      Files.createDirectories(this.path);

    } catch (IOException e) {
      this.logger.error("Error creating path: " + this.path, e);
      return false;
    }

    return true;
  }
}
