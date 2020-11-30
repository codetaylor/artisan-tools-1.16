package com.codetaylor.mc.artisantools.material;

import com.codetaylor.mc.artisantools.api.tool.ICustomToolMaterial;

public class CustomMaterialValidator {

  public void validate(ICustomToolMaterial data) throws CustomMaterialValidationException {

    if (data.getHarvestLevel() < 0) {
      throw new CustomMaterialValidationException("Material: [" + data.getLangKey() + "], key: [harvestLevel] harvest level can't be < 0");
    }

    if (data.getMaxUses() < 1) {
      throw new CustomMaterialValidationException("Material: [" + data.getLangKey() + "], key: [maxUses] max uses can't be < 1");
    }

    if (data.getEfficiency() < 0) {
      throw new CustomMaterialValidationException("Material: [" + data.getLangKey() + "], key: [efficiency] efficiency can't be < 0");
    }

    if (data.getDamage() < 0) {
      throw new CustomMaterialValidationException("Material: [" + data.getLangKey() + "], key: [damage] damage can't be < 0");
    }

    if (data.getEnchantability() < 0) {
      throw new CustomMaterialValidationException("Material: [" + data.getLangKey() + "], key: [enchantability] enchantability can't be < 0");
    }

    if (data.getColor() == null || data.getColor().isEmpty() || data.getColor().length() != 6) {
      throw new CustomMaterialValidationException("Material: [" + data.getLangKey() + "], key: [color] invalid color, must be 6 characters");
    }

    try {
      Integer.decode("0x" + data.getColor());

    } catch (Exception e) {
      throw new CustomMaterialValidationException("Material: [" + data.getLangKey() + "], key: [shiny] invalid color string: " + data
          .getColor());
    }

    if (data.getIngredientString() == null || data.getIngredientString().isEmpty()) {
      throw new CustomMaterialValidationException("Material: [" + data.getLangKey() + "], key: [ingredient] missing or empty ingredient");
    }

    if (data.getLangKey() == null || data.getLangKey().isEmpty()) {
      throw new CustomMaterialValidationException("Material: [" + data.getLangKey() + "], key: [langKey] missing or empty lang key");
    }
  }

}
