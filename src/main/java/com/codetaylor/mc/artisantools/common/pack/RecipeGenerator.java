package com.codetaylor.mc.artisantools.common.pack;

import com.codetaylor.mc.artisantools.ArtisanToolsMod;
import com.codetaylor.mc.artisantools.api.CustomToolMaterial;
import com.codetaylor.mc.artisantools.api.CustomToolMaterialRegistrationEntry;
import com.codetaylor.mc.artisantools.api.ICustomToolMaterial;
import com.codetaylor.mc.artisantools.common.pack.IPackContentGenerator;
import com.codetaylor.mc.artisantools.common.recipe.DataRecipeTemplate;
import com.codetaylor.mc.artisantools.common.util.FileHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class RecipeGenerator
    implements IPackContentGenerator {

  private final Gson gson;
  private final Path path;
  private final List<CustomToolMaterial> materialList;
  private final List<CustomToolMaterialRegistrationEntry> customMaterialList;
  private final List<String> enabledToolTypeList;
  private final Map<String, DataRecipeTemplate> recipeTemplateMap;
  private final Logger logger;

  public RecipeGenerator(
      Gson gson,
      Path path, List<CustomToolMaterial> materialList,
      List<CustomToolMaterialRegistrationEntry> customMaterialList,
      List<String> enabledToolTypeList,
      Map<String, DataRecipeTemplate> recipeTemplateMap,
      Logger logger
  ) {

    this.gson = gson;
    this.path = path;
    this.materialList = materialList;
    this.customMaterialList = customMaterialList;
    this.enabledToolTypeList = enabledToolTypeList;
    this.recipeTemplateMap = recipeTemplateMap;
    this.logger = logger;
  }

  @Override
  public void generate() {

    for (String typeName : this.enabledToolTypeList) {

      DataRecipeTemplate recipeTemplate = this.recipeTemplateMap.get(typeName);

      if (recipeTemplate == null) {
        this.logger.error("Missing recipe template for type: " + typeName);
        continue;
      }

      for (CustomToolMaterial material : this.materialList) {
        String materialName = material.getName();
        String ingredientString = material.getIngredientString();

        try {
          JsonObject jsonObject = this.createJsonObject(typeName, materialName, ingredientString, recipeTemplate);
          this.writeRecipe(typeName, materialName, jsonObject);
        } catch (IllegalArgumentException ignore) {
          //
        }
      }

      for (CustomToolMaterialRegistrationEntry entry : this.customMaterialList) {
        ICustomToolMaterial material = entry.getMaterial();
        String materialName = material.getName();
        String ingredientString = material.getIngredientString();

        try {
          JsonObject jsonObject = this.createJsonObject(typeName, materialName, ingredientString, recipeTemplate);
          this.writeRecipe(typeName, materialName, jsonObject);
        } catch (IllegalArgumentException ignore) {
          //
        }
      }
    }
  }

  private void writeRecipe(String typeName, String materialName, JsonObject jsonObject) {

    String filename = typeName + "_" + materialName + ".json";

    BufferedWriter writer = null;

    try {
      writer = Files.newBufferedWriter(this.path.resolve(filename));
      this.gson.toJson(jsonObject, writer);
      writer.close();

    } catch (IOException e) {
      this.logger.error("", e);

    } finally {
      FileHelper.closeSilently(writer);
    }
  }

  private JsonObject createJsonObject(String typeName, String materialName, String ingredientString, DataRecipeTemplate recipeTemplate) {

    JsonObject root = new JsonObject();
    root.addProperty("type", "minecraft:crafting_shaped");

    JsonArray pattern = new JsonArray();
    root.add("pattern", pattern);
    for (int i = 0; i < recipeTemplate.getPattern().length; i++) {
      pattern.add(recipeTemplate.getPattern()[i]);
    }

    JsonObject key = new JsonObject();
    root.add("key", key);
    for (int i = 0; i < recipeTemplate.getKey().length; i += 2) {
      String k = recipeTemplate.getKey()[i];
      String v = recipeTemplate.getKey()[i + 1];
      JsonObject ingredient = this.parseIngredient(v);
      key.add(k, ingredient);
    }

    JsonObject ingredient = this.parseIngredient(ingredientString);
    key.add(".", ingredient);

    JsonObject result = new JsonObject();
    root.add("result", result);
    result.addProperty("item", ArtisanToolsMod.MOD_ID + ":" + typeName + "_" + materialName);
    return root;
  }

  private JsonObject parseIngredient(String v) {

    String[] split = v.split("#");

    if (split.length != 2) {
      this.logger.error("Ingredient string missing type: " + v);
      throw new IllegalArgumentException();
    }

    switch (split[0]) {
      case "item": {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("item", split[1]);
        return jsonObject;
      }

      case "tag": {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("tag", split[1]);
        return jsonObject;
      }

      default:
        this.logger.error("Unknown ingredient type: " + split[0]);
        throw new IllegalArgumentException();
    }
  }

}
