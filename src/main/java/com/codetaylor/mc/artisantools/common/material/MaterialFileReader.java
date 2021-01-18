package com.codetaylor.mc.artisantools.common.material;

import com.codetaylor.mc.artisantools.api.CustomToolMaterial;
import com.codetaylor.mc.artisantools.common.util.FileHelper;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class MaterialFileReader {

  private final Gson gson;
  private final ConfigFilePathSupplier customPathSupplier;
  private final List<CustomToolMaterial> materialList;
  private final Logger logger;

  public MaterialFileReader(Gson gson, ConfigFilePathSupplier customPathSupplier, List<CustomToolMaterial> materialList, Logger logger) {

    this.gson = gson;
    this.customPathSupplier = customPathSupplier;
    this.materialList = materialList;
    this.logger = logger;
  }

  public void read() {

    BufferedReader reader = null;

    try {
      reader = Files.newBufferedReader(this.customPathSupplier.get());
      DataCustomMaterialList dataCustomMaterialList = gson.fromJson(reader, DataCustomMaterialList.class);
      CustomMaterialListConverter customMaterialListConverter = new CustomMaterialListConverter(
          new CustomMaterialValidator(),
          new CustomMaterialConverter()
      );
      this.materialList.addAll(customMaterialListConverter.convert(dataCustomMaterialList, this.logger));

    } catch (IOException e) {
      this.logger.error("", e);

    } finally {
      FileHelper.closeSilently(reader);
    }
  }
}