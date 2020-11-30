package com.codetaylor.mc.artisantools.api.tool;

public interface ICustomToolProvider<I extends ItemWorktableToolBase> {

  I get(String typeName, CustomMaterial customMaterial);
}
