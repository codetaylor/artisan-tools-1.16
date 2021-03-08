package com.codetaylor.mc.artisantools.common.material;

import com.codetaylor.mc.artisantools.common.util.FileHelper;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public class JsonConfigFileGenerator<T> {

  private final Gson gson;
  private final ConfigFilePathSupplier generatedPathSupplier;
  private final ConfigFilePathSupplier customPathSupplier;
  private final Supplier<T> generatedDataSupplier;
  private final Logger logger;

  public JsonConfigFileGenerator(
      Gson gson,
      ConfigFilePathSupplier generatedPathSupplier,
      ConfigFilePathSupplier customPathSupplier,
      Supplier<T> generatedDataSupplier,
      Logger logger
  ) {

    this.gson = gson;
    this.generatedPathSupplier = generatedPathSupplier;
    this.customPathSupplier = customPathSupplier;
    this.generatedDataSupplier = generatedDataSupplier;
    this.logger = logger;
  }

  public void generate() {

    Path generatedPath = this.generatedPathSupplier.get();
    Path customPath = this.customPathSupplier.get();

    // Delete the generated file if it exists.
    if (Files.exists(generatedPath)) {

      try {
        Files.delete(generatedPath);

      } catch (IOException e) {
        this.logger.error("", e);
      }
    }

    // Create and write the generated file.
    BufferedWriter writer = null;

    try {
      writer = Files.newBufferedWriter(generatedPath);
      this.gson.toJson(this.generatedDataSupplier.get(), writer);
      writer.close();

    } catch (IOException e) {
      this.logger.error("", e);

    } finally {
      FileHelper.closeSilently(writer);
    }

    // Copy the generated file to the custom file if the custom file doesn't exist.
    if (!Files.exists(customPath)) {

      try {
        Files.copy(generatedPath, customPath);

      } catch (IOException e) {
        this.logger.error("", e);
      }
    }
  }
}
