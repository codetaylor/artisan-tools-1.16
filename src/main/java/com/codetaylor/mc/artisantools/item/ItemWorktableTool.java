package com.codetaylor.mc.artisantools.item;

import com.codetaylor.mc.artisantools.api.CustomToolMaterial;
import com.codetaylor.mc.artisantools.api.ItemCustomToolBase;
import net.minecraft.item.Item;

import java.util.Collections;

public class ItemWorktableTool
    extends ItemCustomToolBase {

  public ItemWorktableTool(
      String typeName,
      CustomToolMaterial material,
      Item.Properties properties
  ) {

    super(material, Collections.emptySet(), typeName, properties);
  }
}
