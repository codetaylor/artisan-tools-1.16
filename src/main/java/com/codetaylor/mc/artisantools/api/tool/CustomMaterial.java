package com.codetaylor.mc.artisantools.api.tool;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class CustomMaterial
    implements IItemTier {

  private final String name;
  private final String langKey;
  private final int maxUses;
  private final float efficiency;
  private final float attackDamage;
  private final int harvestLevel;
  private final int enchantability;
  private final int color;
  private final String ingredientString;
  private final boolean isShiny;

  public CustomMaterial(
      String name, String langKey,
      int maxUses,
      float efficiency,
      float attackDamage,
      int harvestLevel,
      int enchantability,
      int color,
      String ingredientString,
      boolean isShiny
  ) {

    this.name = name;
    this.langKey = langKey;
    this.maxUses = maxUses;
    this.efficiency = efficiency;
    this.attackDamage = attackDamage;
    this.harvestLevel = harvestLevel;
    this.enchantability = enchantability;
    this.color = color;
    this.ingredientString = ingredientString;
    this.isShiny = isShiny;
  }

  public String getName() {

    return this.name;
  }

  public String getLangKey() {

    return this.langKey;
  }

  public int getColor() {

    return this.color;
  }

  public String getIngredientString() {

    return this.ingredientString;
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
  public float getAttackDamage() {

    return this.attackDamage;
  }

  @Override
  public int getHarvestLevel() {

    return this.harvestLevel;
  }

  @Override
  public int getEnchantability() {

    return this.enchantability;
  }

  @Override
  public Ingredient getRepairMaterial() {

    return null;
  }

  public boolean isShiny() {

    return this.isShiny;
  }
}
