package com.codetaylor.mc.artisantools.common.pack;

import com.codetaylor.mc.artisantools.ArtisanToolsMod;
import com.codetaylor.mc.artisantools.api.CustomToolMaterial;
import com.codetaylor.mc.artisantools.api.CustomToolMaterialRegistrationEntry;
import com.codetaylor.mc.artisantools.api.ICustomToolMaterial;
import com.codetaylor.mc.artisantools.common.util.FileHelper;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ModelGenerator
    implements IPackContentGenerator {

  private final Gson gson;
  private final Path path;
  private final List<CustomToolMaterial> materialList;
  private final List<CustomToolMaterialRegistrationEntry> customMaterialList;
  private final List<String> enabledToolTypeList;
  private final Logger logger;

  public ModelGenerator(
      Gson gson,
      Path path,
      List<CustomToolMaterial> materialList,
      List<CustomToolMaterialRegistrationEntry> customMaterialList,
      List<String> enabledToolTypeList,
      Logger logger
  ) {

    this.gson = gson;
    this.path = path;
    this.materialList = materialList;
    this.customMaterialList = customMaterialList;
    this.enabledToolTypeList = enabledToolTypeList;
    this.logger = logger;
  }

  @Override
  public void generate() {

    for (String typeName : this.enabledToolTypeList) {

      for (CustomToolMaterial material : this.materialList) {
        String materialName = material.getName();
        boolean materialShiny = material.isShiny();
        this.writeModel(typeName, materialName, materialShiny);
      }

      for (CustomToolMaterialRegistrationEntry entry : this.customMaterialList) {
        ICustomToolMaterial material = entry.getMaterial();
        String materialName = material.getName();
        boolean materialShiny = material.isShiny();
        this.writeModel(typeName, materialName, materialShiny);
      }
    }
  }

  private void writeModel(String typeName, String materialName, boolean materialShiny) {

    String filename = typeName + "_" + materialName + ".json";
    String parent;

    if (materialShiny) {
      parent = ArtisanToolsMod.MOD_ID + ":item/artisans_" + typeName + "_highlighted";

    } else {
      parent = ArtisanToolsMod.MOD_ID + ":item/artisans_" + typeName;
    }

    BufferedWriter writer = null;

    try {
      writer = Files.newBufferedWriter(this.path.resolve(filename));
      this.gson.toJson(new ModelData(parent), writer);
      writer.close();

    } catch (IOException e) {
      this.logger.error("", e);

    } finally {
      FileHelper.closeSilently(writer);
    }
  }

  public static class ModelData {

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final String parent;

    public ModelData(String parent) {

      this.parent = parent;
    }
  }
}