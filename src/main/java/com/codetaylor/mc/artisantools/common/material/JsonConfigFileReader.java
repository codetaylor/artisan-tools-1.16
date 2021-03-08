package com.codetaylor.mc.artisantools.common.material;

import com.codetaylor.mc.artisantools.common.util.FileHelper;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Consumer;

public class JsonConfigFileReader<T> {

  private final Gson gson;
  private final ConfigFilePathSupplier customPathSupplier;
  private final Class<T> resultClass;
  private final Consumer<T> resultConsumer;
  private final Logger logger;

  public JsonConfigFileReader(Gson gson, ConfigFilePathSupplier customPathSupplier, Class<T> resultClass, Consumer<T> resultConsumer, Logger logger) {

    this.gson = gson;
    this.customPathSupplier = customPathSupplier;
    this.resultClass = resultClass;
    this.resultConsumer = resultConsumer;
    this.logger = logger;
  }

  public void read() {

    BufferedReader reader = null;

    try {
      reader = Files.newBufferedReader(this.customPathSupplier.get());
      T result = this.gson.fromJson(reader, this.resultClass);
      this.resultConsumer.accept(result);

    } catch (IOException e) {
      this.logger.error("", e);

    } finally {
      FileHelper.closeSilently(reader);
    }
  }
}