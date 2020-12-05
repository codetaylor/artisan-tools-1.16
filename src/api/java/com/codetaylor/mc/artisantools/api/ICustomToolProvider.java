package com.codetaylor.mc.artisantools.api;

import net.minecraft.item.Item;

public interface ICustomToolProvider<I extends ItemCustomToolBase> {

  I get(String typeName, CustomToolMaterial customToolMaterial, Item.Properties properties);
}
