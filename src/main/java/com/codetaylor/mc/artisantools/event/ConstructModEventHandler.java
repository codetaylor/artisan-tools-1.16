package com.codetaylor.mc.artisantools.event;

import com.codetaylor.mc.artisantools.material.CustomMaterialListPopulator;
import com.codetaylor.mc.artisantools.material.MaterialFileGenerator;
import com.codetaylor.mc.artisantools.material.MaterialFileReader;
import com.codetaylor.mc.artisantools.pack.DataPackGenerator;
import com.codetaylor.mc.artisantools.pack.GeneratedPackRemover;
import com.codetaylor.mc.artisantools.pack.ResourcePackGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

public class ConstructModEventHandler {

  private final MaterialFileGenerator materialFileGenerator;
  private final MaterialFileReader materialFileReader;
  private final CustomMaterialListPopulator customMaterialListPopulator;
  private final GeneratedPackRemover generatedPackRemover;
  private final ResourcePackGenerator resourcePackGenerator;
  private final DataPackGenerator dataPackGenerator;

  public ConstructModEventHandler(
      MaterialFileGenerator materialFileGenerator,
      MaterialFileReader materialFileReader,
      CustomMaterialListPopulator customMaterialListPopulator,
      GeneratedPackRemover generatedPackRemover,
      ResourcePackGenerator resourcePackGenerator,
      DataPackGenerator dataPackGenerator
  ) {

    this.materialFileGenerator = materialFileGenerator;
    this.materialFileReader = materialFileReader;
    this.customMaterialListPopulator = customMaterialListPopulator;
    this.generatedPackRemover = generatedPackRemover;
    this.resourcePackGenerator = resourcePackGenerator;
    this.dataPackGenerator = dataPackGenerator;
  }

  @SubscribeEvent
  public void on(FMLConstructModEvent event) {

    this.materialFileGenerator.generate();
    this.materialFileReader.read();
    this.customMaterialListPopulator.populate();
    this.generatedPackRemover.remove();
    this.resourcePackGenerator.generate();
    this.dataPackGenerator.generate();
  }
}