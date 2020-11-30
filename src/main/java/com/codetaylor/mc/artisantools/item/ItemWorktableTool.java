package com.codetaylor.mc.artisantools.item;

import com.codetaylor.mc.artisantools.api.tool.CustomMaterial;
import com.codetaylor.mc.artisantools.api.tool.ItemWorktableToolBase;
import net.minecraft.item.Item;

import java.util.Collections;

public class ItemWorktableTool
    extends ItemWorktableToolBase {

  public ItemWorktableTool(
      String typeName,
      CustomMaterial material,
      Item.Properties properties
  ) {

    super(material, Collections.emptySet(), typeName, properties);
  }
}
