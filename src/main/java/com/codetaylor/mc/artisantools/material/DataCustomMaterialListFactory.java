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
        "FAF25E",
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

    // Flint
    list.add(new DataCustomMaterial(
        "flint",
        1,
        150,
        3.8f,
        1,
        5,
        "5F5F5F",
        true,
        "item#minecraft:flint",
        "material.artisantools.flint"
    ));

    // Bone
    list.add(new DataCustomMaterial(
        "bone",
        1,
        150,
        3.8f,
        1,
        5,
        "FFF6C8",
        false,
        "tag#forge:bones",
        "material.artisantools.bone"
    ));

    // Bronze
    list.add(new DataCustomMaterial(
        "bronze",
        2,
        500,
        6,
        2,
        15,
        "E8983F",
        true,
        "tag#forge:ingots/bronze",
        "material.artisantools.bronze"
    ));

    // Constantan
    list.add(new DataCustomMaterial(
        "constantan",
        2,
        275,
        6,
        1.5f,
        20,
        "BD8D46",
        true,
        "tag#forge:ingots/constantan",
        "material.artisantools.constantan"
    ));

    // Copper
    list.add(new DataCustomMaterial(
        "copper",
        1,
        175,
        4,
        0.75f,
        6,
        "FFA131",
        true,
        "tag#forge:ingots/copper",
        "material.artisantools.copper"
    ));

    // Electrum
    list.add(new DataCustomMaterial(
        "electrum",
        0,
        100,
        14,
        0.5f,
        30,
        "FFE947",
        true,
        "tag#forge:ingots/electrum",
        "material.artisantools.electrum"
    ));

    // Invar
    list.add(new DataCustomMaterial(
        "invar",
        2,
        450,
        7,
        3,
        16,
        "8E9A95",
        true,
        "tag#forge:ingots/invar",
        "material.artisantools.invar"
    ));

    // Lead
    list.add(new DataCustomMaterial(
        "lead",
        1,
        150,
        5,
        1,
        9,
        "8A93B1",
        true,
        "tag#forge:ingots/lead",
        "material.artisantools.lead"
    ));

    // Nickel
    list.add(new DataCustomMaterial(
        "nickel",
        2,
        300,
        6.5f,
        2.5f,
        18,
        "A2975D",
        true,
        "tag#forge:ingots/nickel",
        "material.artisantools.nickel"
    ));

    // Silver
    list.add(new DataCustomMaterial(
        "silver",
        2,
        200,
        6,
        1.5f,
        20,
        "7B9DA4",
        true,
        "tag#forge:ingots/silver",
        "material.artisantools.silver"
    ));


    // Tin
    list.add(new DataCustomMaterial(
        "tin",
        1,
        200,
        4.5f,
        1,
        7,
        "7C9AB2",
        true,
        "tag#forge:ingots/tin",
        "material.artisantools.tin"
    ));

    return new DataCustomMaterialList(list);
  }
}