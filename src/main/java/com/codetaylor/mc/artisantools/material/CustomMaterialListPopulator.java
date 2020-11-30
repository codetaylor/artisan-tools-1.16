package com.codetaylor.mc.artisantools.material;

import com.codetaylor.mc.artisantools.api.tool.CustomToolMaterialRegistrationEntry;
import com.codetaylor.mc.artisantools.api.tool.event.ArtisanCustomToolMaterialRegistrationEvent;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;

public class CustomMaterialListPopulator {

  private final List<CustomToolMaterialRegistrationEntry> customMaterialList;

  public CustomMaterialListPopulator(List<CustomToolMaterialRegistrationEntry> customMaterialList) {

    this.customMaterialList = customMaterialList;
  }

  public void populate() {

    ArtisanCustomToolMaterialRegistrationEvent artisanEvent = new ArtisanCustomToolMaterialRegistrationEvent();
    MinecraftForge.EVENT_BUS.post(artisanEvent);
    this.customMaterialList.addAll(artisanEvent.getMaterialList());
  }
}