package com.codetaylor.mc.artisantools.material;

import com.codetaylor.mc.artisantools.api.CustomToolMaterial;
import com.codetaylor.mc.artisantools.api.ICustomToolMaterial;

import java.awt.*;

public class CustomMaterialConverter {

  public CustomToolMaterial convert(ICustomToolMaterial data) {

    // Convert color
    Integer colorInt = Integer.decode("0x" + data.getColor());
    int color = new Color(colorInt).getRGB();

    return new CustomToolMaterial(
        data.getName(),
        data.getLangKey(),
        data.getMaxUses(),
        data.getEfficiency(),
        data.getDamage(),
        data.getHarvestLevel(),
        data.getEnchantability(),
        color,
        data.getIngredientString(),
        data.isShiny()
    );
  }

}
