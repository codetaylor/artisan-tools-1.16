package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.ArtisanToolsMod;
import com.codetaylor.mc.artisantools.api.CustomToolMaterial;
import com.codetaylor.mc.artisantools.api.CustomToolMaterialRegistrationEntry;
import com.codetaylor.mc.artisantools.api.ICustomToolMaterial;
import com.codetaylor.mc.artisantools.lib.FileHelper;
import com.google.gson.Gson;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagGenerator
    implements IPackContentGenerator {

  private final Gson gson;
  private final Path path;
  private final List<CustomToolMaterial> materialList;
  private final List<CustomToolMaterialRegistrationEntry> customMaterialList;
  private final List<String> enabledToolTypeList;
  private final Logger logger;

  public TagGenerator(
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

    Map<String, List<String>> toolByMaterialMap = new HashMap<>();

    for (String typeName : this.enabledToolTypeList) {

      List<String> toolByTypeList = new ArrayList<>();

      for (CustomToolMaterial material : this.materialList) {
        String materialName = material.getName();
        String toolName = ArtisanToolsMod.MOD_ID + ":" + typeName + "_" + materialName;
        toolByTypeList.add(toolName);
        toolByMaterialMap.computeIfAbsent(materialName, s -> new ArrayList<>()).add(toolName);
      }

      for (CustomToolMaterialRegistrationEntry entry : this.customMaterialList) {
        ICustomToolMaterial material = entry.getMaterial();
        String materialName = material.getName();
        String toolName = ArtisanToolsMod.MOD_ID + ":" + typeName + "_" + materialName;
        toolByTypeList.add(toolName);
        toolByMaterialMap.computeIfAbsent(materialName, s -> new ArrayList<>()).add(toolName);
      }

      this.writeTag("type/" + typeName, new Tag(toolByTypeList));
    }

    for (Map.Entry<String, List<String>> entry : toolByMaterialMap.entrySet()) {
      this.writeTag("material/" + entry.getKey(), new Tag(entry.getValue()));
    }
  }

  private void writeTag(String tagName, Tag tag) {

    String filename = tagName + ".json";

    BufferedWriter writer = null;

    try {
      writer = Files.newBufferedWriter(this.path.resolve(filename));
      this.gson.toJson(tag, writer);
      writer.close();

    } catch (IOException e) {
      this.logger.error("", e);

    } finally {
      FileHelper.closeSilently(writer);
    }
  }

  @SuppressWarnings("unused")
  public static class Tag {

    private final boolean replace = false;

    @SuppressWarnings("FieldCanBeLocal")
    private final List<String> values;

    public Tag(List<String> values) {

      this.values = values;
    }
  }
}
