package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.api.tool.reference.EnumToolType;

import java.util.HashMap;
import java.util.Map;

public class RecipeTemplateFactory {

  public Map<String, RecipeTemplate> create() {

    Map<String, RecipeTemplate> map = new HashMap<>();

    map.put(
        EnumToolType.BLACKSMITHS_CUTTERS.getName(),
        new RecipeTemplate(
            new String[]{
                ". .",
                " x ",
                "s s"
            },
            new String[]{
                "x", "tag#forge:string",
                "s", "item#minecraft:stick"
            }
        )
    );

    return map;
  }
}
