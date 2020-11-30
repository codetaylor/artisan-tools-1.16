package com.codetaylor.mc.artisantools.api.tool;

import com.codetaylor.mc.artisantools.api.tool.reference.EnumWorktableToolType;

public interface ICustomToolProvider<I extends ItemWorktableToolBase> {

  I get(EnumWorktableToolType type, CustomMaterial customMaterial);
}
