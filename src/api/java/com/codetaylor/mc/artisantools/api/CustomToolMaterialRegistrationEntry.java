package com.codetaylor.mc.artisantools.api;

public class CustomToolMaterialRegistrationEntry {

  private final ICustomToolMaterial material;
  private final ICustomToolProvider<? extends ItemCustomToolBase> provider;

  public CustomToolMaterialRegistrationEntry(ICustomToolMaterial material, ICustomToolProvider<? extends ItemCustomToolBase> provider) {

    this.material = material;
    this.provider = provider;
  }

  public ICustomToolMaterial getMaterial() {

    return this.material;
  }

  public ICustomToolProvider<? extends ItemCustomToolBase> getProvider() {

    return this.provider;
  }
}
