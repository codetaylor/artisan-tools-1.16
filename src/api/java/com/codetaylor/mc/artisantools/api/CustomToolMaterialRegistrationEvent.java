package com.codetaylor.mc.artisantools.api;

import com.codetaylor.mc.artisantools.api.CustomToolMaterialRegistrationEntry;
import net.minecraftforge.eventbus.api.Event;

import java.util.ArrayList;
import java.util.List;

public class CustomToolMaterialRegistrationEvent
    extends Event {

  private List<CustomToolMaterialRegistrationEntry> materialList = new ArrayList<>();

  public List<CustomToolMaterialRegistrationEntry> getMaterialList() {

    return this.materialList;
  }

  @Override
  public boolean isCancelable() {

    return false;
  }
}
