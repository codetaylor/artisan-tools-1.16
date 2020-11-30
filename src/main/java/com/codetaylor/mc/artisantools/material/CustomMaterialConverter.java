package com.codetaylor.mc.artisantools.material;

import com.codetaylor.mc.artisantools.api.tool.CustomMaterial;
import com.codetaylor.mc.artisantools.api.tool.ICustomToolMaterial;

import java.awt.*;

public class CustomMaterialConverter {

  public CustomMaterial convert(ICustomToolMaterial data) {

    // Convert color
    Integer colorInt = Integer.decode("0x" + data.getColor());
    int color = new Color(colorInt).getRGB();

    return new CustomMaterial(
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
