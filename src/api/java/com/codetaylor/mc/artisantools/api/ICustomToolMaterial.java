package com.codetaylor.mc.artisantools.api;

public interface ICustomToolMaterial {

  String getName();

  int getHarvestLevel();

  int getMaxUses();

  float getEfficiency();

  float getDamage();

  int getEnchantability();

  String getColor();

  boolean isShiny();

  String getIngredientString();

  String getLangKey();
}
