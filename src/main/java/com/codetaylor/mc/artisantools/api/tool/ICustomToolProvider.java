package com.codetaylor.mc.artisantools.api.tool;

import net.minecraft.item.Item;

public interface ICustomToolProvider<I extends ItemWorktableToolBase> {

  I get(String typeName, CustomMaterial customMaterial, Item.Properties properties);
}
