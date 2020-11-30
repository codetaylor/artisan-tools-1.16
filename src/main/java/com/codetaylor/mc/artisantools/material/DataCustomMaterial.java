package com.codetaylor.mc.artisantools.material;

import com.codetaylor.mc.artisantools.api.tool.ICustomToolMaterial;
import com.google.gson.annotations.SerializedName;

public class DataCustomMaterial
    implements ICustomToolMaterial {

  private String name;
  private int harvestLevel;
  private int maxUses;
  private float efficiency;
  private float damage;
  private int enchantability;
  private String color;
  private boolean shiny;

  @SerializedName("ingredient")
  private String ingredientString;
  private String langKey;

  public DataCustomMaterial(
      String name,
      int harvestLevel,
      int maxUses,
      float efficiency,
      float damage,
      int enchantability,
      String color,
      boolean shiny,
      String ingredientString,
      String langKey
  ) {

    this.name = name;
    this.harvestLevel = harvestLevel;
    this.maxUses = maxUses;
    this.efficiency = efficiency;
    this.damage = damage;
    this.enchantability = enchantability;
    this.color = color;
    this.shiny = shiny;
    this.ingredientString = ingredientString;
    this.langKey = langKey;
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public int getHarvestLevel() {

    return this.harvestLevel;
  }

  @Override
  public int getMaxUses() {

    return this.maxUses;
  }

  @Override
  public float getEfficiency() {

    return this.efficiency;
  }

  @Override
  public float getDamage() {

    return this.damage;
  }

  @Override
  public int getEnchantability() {

    return this.enchantability;
  }

  @Override
  public String getColor() {

    return this.color;
  }

  @Override
  public boolean isShiny() {

    return this.shiny;
  }

  @Override
  public String getIngredientString() {

    return this.ingredientString;
  }

  @Override
  public String getLangKey() {

    return this.langKey;
  }
}
