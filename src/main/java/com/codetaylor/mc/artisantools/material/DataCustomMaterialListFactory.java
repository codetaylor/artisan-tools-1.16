package com.codetaylor.mc.artisantools.material;

import net.minecraft.item.ItemTier;

import java.util.ArrayList;
import java.util.List;

public class DataCustomMaterialListFactory {

  public DataCustomMaterialList create() {

    List<DataCustomMaterial> list = new ArrayList<>();

    // Wood
    list.add(new DataCustomMaterial(
        "wood",
        ItemTier.WOOD.getHarvestLevel(),
        ItemTier.WOOD.getMaxUses(),
        ItemTier.WOOD.getEfficiency(),
        ItemTier.WOOD.getAttackDamage(),
        ItemTier.WOOD.getEnchantability(),
        "73523E",
        false,
        "tag#minecraft:planks",
        "material.artisantools.wood"
    ));

    // Stone
    list.add(new DataCustomMaterial(
        "stone",
        ItemTier.STONE.getHarvestLevel(),
        ItemTier.STONE.getMaxUses(),
        ItemTier.STONE.getEfficiency(),
        ItemTier.STONE.getAttackDamage(),
        ItemTier.STONE.getEnchantability(),
        "969696",
        false,
        "tag#forge:stone",
        "material.artisantools.stone"
    ));

    // Iron
    list.add(new DataCustomMaterial(
        "iron",
        ItemTier.IRON.getHarvestLevel(),
        ItemTier.IRON.getMaxUses(),
        ItemTier.IRON.getEfficiency(),
        ItemTier.IRON.getAttackDamage(),
        ItemTier.IRON.getEnchantability(),
        "D4D4D4",
        true,
        "tag#forge:ingots/iron",
        "material.artisantools.iron"
    ));

    // Gold
    list.add(new DataCustomMaterial(
        "gold",
        ItemTier.GOLD.getHarvestLevel(),
        ItemTier.GOLD.getMaxUses(),
        ItemTier.GOLD.getEfficiency(),
        ItemTier.GOLD.getAttackDamage(),
        ItemTier.GOLD.getEnchantability(),
        "FFE947",
        false,
        "tag#forge:ingots/gold",
        "material.artisantools.gold"
    ));

    // Diamond
    list.add(new DataCustomMaterial(
        "diamond",
        ItemTier.DIAMOND.getHarvestLevel(),
        ItemTier.DIAMOND.getMaxUses(),
        ItemTier.DIAMOND.getEfficiency(),
        ItemTier.DIAMOND.getAttackDamage(),
        ItemTier.DIAMOND.getEnchantability(),
        "33EBCB",
        false,
        "tag#forge:gems/diamond",
        "material.artisantools.diamond"
    ));

    // Netherite
    list.add(new DataCustomMaterial(
        "netherite",
        ItemTier.NETHERITE.getHarvestLevel(),
        ItemTier.NETHERITE.getMaxUses(),
        ItemTier.NETHERITE.getEfficiency(),
        ItemTier.NETHERITE.getAttackDamage(),
        ItemTier.NETHERITE.getEnchantability(),
        "756975",
        false,
        "tag#forge:ingots/netherite",
        "material.artisantools.netherite"
    ));

    return new DataCustomMaterialList(list);
  }
}