package com.codetaylor.mc.artisantools.lib;

import com.codetaylor.mc.artisantools.api.CustomToolMaterial;

import java.util.List;

public class GenerationInhibitor {

  private final boolean disableGeneration;
  private final List<CustomToolMaterial> materialList;
  private final List<? extends String> allowedToolTypeList;

  public GenerationInhibitor(
      boolean disableGeneration,
      List<CustomToolMaterial> materialList,
      List<? extends String> allowedToolTypeList
  ) {

    this.disableGeneration = disableGeneration;
    this.materialList = materialList;
    this.allowedToolTypeList = allowedToolTypeList;
  }

  public boolean isDisabled() {

    if (this.disableGeneration) {
      return true;
    }

    if (this.allowedToolTypeList.isEmpty()) {
      // User has disabled all tool types.
      return true;
    }

    if (this.materialList.isEmpty()) {
      // User has disabled all tool materials.
      return true;
    }

    return false;
  }
}
