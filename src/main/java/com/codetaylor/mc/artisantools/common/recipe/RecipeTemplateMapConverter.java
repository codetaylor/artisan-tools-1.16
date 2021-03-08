package com.codetaylor.mc.artisantools.common.recipe;

import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class RecipeTemplateMapConverter {

  private final RecipeTemplateValidator validator;

  public RecipeTemplateMapConverter(
      RecipeTemplateValidator validator
  ) {

    this.validator = validator;
  }

  public Map<String, DataRecipeTemplate> convert(DataRecipeTemplateMap data, Logger logger) {

    Map<String, DataRecipeTemplate> result = new HashMap<>(data.recipes.size());
    Map<String, DataRecipeTemplate> recipes = data.recipes;

    for (Map.Entry<String, DataRecipeTemplate> entry : recipes.entrySet()) {

      try {
        this.validator.validate(entry.getKey(), entry.getValue());

      } catch (RecipeValidationException e) {
        logger.error("", e);
        continue;
      }

      result.put(entry.getKey(), entry.getValue());
    }

    return result;
  }
}
