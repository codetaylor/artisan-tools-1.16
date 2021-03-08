package com.codetaylor.mc.artisantools.common.recipe;

public class DataRecipeTemplate {

  private final String[] pattern;
  private final String[] key;

  public DataRecipeTemplate(String[] pattern, String[] key) {

    this.pattern = pattern;
    this.key = key;
  }

  public String[] getPattern() {

    return this.pattern;
  }

  public String[] getKey() {

    return this.key;
  }
}
