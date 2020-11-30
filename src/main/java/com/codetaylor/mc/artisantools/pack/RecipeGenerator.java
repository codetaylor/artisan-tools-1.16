package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.ArtisanToolsMod;
import com.codetaylor.mc.artisantools.lib.EnabledToolTypePredicate;
import com.codetaylor.mc.artisantools.api.tool.CustomMaterial;
import com.codetaylor.mc.artisantools.api.tool.CustomToolMaterialRegistrationEntry;
import com.codetaylor.mc.artisantools.api.tool.ICustomToolMaterial;
import com.codetaylor.mc.artisantools.api.tool.reference.EnumWorktableToolType;
import com.codetaylor.mc.artisantools.lib.FileHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.List;

public class RecipeGenerator {

  private final Gson gson;
  private final Path path;
  private final List<CustomMaterial> materialList;
  private final List<CustomToolMaterialRegistrationEntry> customMaterialList;
  private final EnabledToolTypePredicate enabledToolTypePredicate;
  private final Logger logger;

  private static final EnumMap<EnumWorktableToolType, RecipeTemplate> RECIPE_MAP;

  static {
    RECIPE_MAP = new EnumMap<>(EnumWorktableToolType.class);

    RECIPE_MAP.put(
        EnumWorktableToolType.BLACKSMITHS_CUTTERS,
        new RecipeTemplate(
            new String[]{
                ". .",
                " x ",
                "s s"
            },
            new String[]{
                "x", "tag#forge:string",
                "s", "item#minecraft:stick"
            }
        )
    );
  }

  public RecipeGenerator(
      Gson gson,
      Path path, List<CustomMaterial> materialList,
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

      RecipeTemplate recipeTemplate = RECIPE_MAP.get(type);

      if (recipeTemplate == null) {
        this.logger.error("Missing recipe template for type: " + type);
        continue;
      }

      for (CustomMaterial material : this.materialList) {
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

  private JsonObject createJsonObject(String typeName, String materialName, String ingredientString, RecipeTemplate recipeTemplate) {

    JsonObject root = new JsonObject();
    root.addProperty("type", "minecraft:crafting_shaped");

    JsonArray pattern = new JsonArray();
    root.add("pattern", pattern);
    for (int i = 0; i < recipeTemplate.pattern.length; i++) {
      pattern.add(recipeTemplate.pattern[i]);
    }

    JsonObject key = new JsonObject();
    root.add("key", key);
    for (int i = 0; i < recipeTemplate.key.length; i += 2) {
      String k = recipeTemplate.key[i];
      String v = recipeTemplate.key[i + 1];
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

  public static class RecipeTemplate {

    private final String[] pattern;
    private final String[] key;

    public RecipeTemplate(String[] pattern, String[] key) {

      this.pattern = pattern;
      this.key = key;
    }
  }
}
