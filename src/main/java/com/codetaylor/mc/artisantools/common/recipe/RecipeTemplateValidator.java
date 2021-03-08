package com.codetaylor.mc.artisantools.common.recipe;

import java.util.HashSet;
import java.util.Set;

public class RecipeTemplateValidator {

  public void validate(String name, DataRecipeTemplate template) throws RecipeValidationException {

    String[] pattern = template.getPattern();

    if (pattern == null || pattern.length == 0) {
      throw new RecipeValidationException("Recipe: [" + name + "], missing pattern");
    }

    if (pattern.length > 3) {
      throw new RecipeValidationException("Recipe: [" + name + "], pattern height > 3");
    }

    Set<String> patternChars = new HashSet<>();

    for (String p : pattern) {

      if (p.length() > 3) {
        throw new RecipeValidationException("Recipe: [" + name + "], pattern width > 3");
      }

      for (char c : p.toCharArray()) {
        patternChars.add(String.valueOf(c));
      }
    }

    if (!patternChars.contains(".")) {
      throw new RecipeValidationException("Recipe: [" + name + "], missing placeholder '.' in pattern");

    } else {
      patternChars.remove(".");
    }

    patternChars.remove(" ");

    String[] key = template.getKey();

    if (key.length % 2 == 1) {
      throw new RecipeValidationException("Recipe: [" + name + "], odd number of key entries - must be even");
    }

    int foundKeys = 0;

    for (int i = 0; i < key.length; i += 2) {

      if (!patternChars.contains(key[i])) {
        throw new RecipeValidationException("Recipe: [" + name + "], extra key found [" + key[i] + "]");

      } else {
        foundKeys += 1;
      }
    }

    if (foundKeys != patternChars.size()) {
      throw new RecipeValidationException("Recipe: [" + name + "], missing key definitions");
    }

    for (int i = 1; i < key.length; i += 2) {

      if (!key[i].startsWith("item#") && !key[i].startsWith("tag#")) {
        throw new RecipeValidationException("Recipe: [" + name + "], invalid prefix for key [" + key[i] + "], expects item# or tag#");
      }
    }
  }

}
