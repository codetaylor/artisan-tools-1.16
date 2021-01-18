package com.codetaylor.mc.artisantools.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

public class PathRemover {

  private final Logger logger;
  private final Path path;

  public PathRemover(Path path, Logger logger) {

    this.logger = logger;
    this.path = path;
  }

  public void remove() {

    try {
      FileUtils.deleteDirectory(this.path.toFile());

    } catch (IOException e) {
      this.logger.error("Error deleting path: " + this.path, e);
    }
  }
}