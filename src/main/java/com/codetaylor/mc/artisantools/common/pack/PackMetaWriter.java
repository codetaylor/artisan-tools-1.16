package com.codetaylor.mc.artisantools.common.pack;

import com.codetaylor.mc.artisantools.common.util.FileHelper;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PackMetaWriter {

  private static final String PACK_MCMETA = "pack.mcmeta";

  private final Gson gson;
  private final Path path;
  private final String description;
  private final Logger logger;

  public PackMetaWriter(Gson gson, Path path, String description, Logger logger) {

    this.gson = gson;
    this.path = path;
    this.description = description;
    this.logger = logger;
  }

  public void write() {

    BufferedWriter writer = null;

    try {
      writer = Files.newBufferedWriter(this.path.resolve(PACK_MCMETA));
      this.gson.toJson(new PackMeta(this.description), writer);
      writer.close();

    } catch (IOException e) {
      this.logger.error("", e);

    } finally {
      FileHelper.closeSilently(writer);
    }
  }
}
