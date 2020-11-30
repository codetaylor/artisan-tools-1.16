package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.ArtisanToolsMod;
import com.codetaylor.mc.artisantools.lib.EnabledToolTypePredicate;
import com.codetaylor.mc.artisantools.api.tool.CustomMaterial;
import com.codetaylor.mc.artisantools.api.tool.CustomToolMaterialRegistrationEntry;
import com.codetaylor.mc.artisantools.api.tool.ICustomToolMaterial;
import com.codetaylor.mc.artisantools.api.tool.reference.EnumWorktableToolType;
import com.codetaylor.mc.artisantools.lib.FileHelper;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ModelGenerator {

  private final Gson gson;
  private final Path path;
  private final List<CustomMaterial> materialList;
  private final List<CustomToolMaterialRegistrationEntry> customMaterialList;
  private final EnabledToolTypePredicate enabledToolTypePredicate;
  private final Logger logger;

  public ModelGenerator(
      Gson gson,
      Path path,
      List<CustomMaterial> materialList,
      List<CustomToolMaterialRegistrationEntry> customMaterialList,
      EnabledToolTypePredicate enabledToolTypePredicate,
      Logger logger
  ) {

    this.gson = gson;
    this.path = path;
    this.materialList = materialList;
    this.customMaterialList = customMaterialList;
    this.enabledToolTypePredicate = enabledToolTypePredicate;
    this.logger = logger;
  }

  public void generate() {

    for (EnumWorktableToolType type : EnumWorktableToolType.values()) {
      String typeName = type.getName();

      if (!this.enabledToolTypePredicate.test(typeName)) {
        // User has disabled this tool type.
        continue;
      }

      for (CustomMaterial material : this.materialList) {
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
      parent = ArtisanToolsMod.MOD_ID + ":item/" + typeName + "_highlighted";

    } else {
      parent = ArtisanToolsMod.MOD_ID + ":item/" + typeName;
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