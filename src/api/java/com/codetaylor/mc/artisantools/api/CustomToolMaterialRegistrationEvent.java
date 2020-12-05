package com.codetaylor.mc.artisantools.api;

import net.minecraftforge.eventbus.api.Event;

import java.util.ArrayList;
import java.util.List;

public class CustomToolMaterialRegistrationEvent
    extends Event {

  private final List<CustomToolMaterialRegistrationEntry> materialList = new ArrayList<>();

  public List<CustomToolMaterialRegistrationEntry> getMaterialList() {

    return this.materialList;
  }

  @Override
  public boolean isCancelable() {

    return false;
  }
}
