package com.codetaylor.mc.artisantools.common.pack;

public class RecipeTemplate {

  private final String[] pattern;
  private final String[] key;

  public RecipeTemplate(String[] pattern, String[] key) {

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
