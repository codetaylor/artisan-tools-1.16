package com.codetaylor.mc.artisantools.item;

import com.codetaylor.mc.artisantools.api.tool.CustomMaterial;
import com.codetaylor.mc.artisantools.api.tool.ItemWorktableToolBase;
import com.codetaylor.mc.artisantools.api.tool.reference.EnumWorktableToolType;
import net.minecraft.item.Item;

import java.util.Collections;

public class ItemWorktableTool
    extends ItemWorktableToolBase {

  public ItemWorktableTool(
      EnumWorktableToolType type,
      CustomMaterial material,
      Item.Properties properties
  ) {

    super(material, Collections.emptySet(), type, properties);
  }
}
