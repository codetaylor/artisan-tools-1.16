package com.codetaylor.mc.artisantools.common.event;

import com.codetaylor.mc.artisantools.common.material.CustomMaterialListPopulator;
import com.codetaylor.mc.artisantools.common.material.DataCustomMaterialList;
import com.codetaylor.mc.artisantools.common.material.FileGenerator;
import com.codetaylor.mc.artisantools.common.material.MaterialFileReader;
import com.codetaylor.mc.artisantools.common.pack.GeneratedPackRemover;
import com.codetaylor.mc.artisantools.common.pack.PackGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

public class ConstructModEventHandler {

  private final FileGenerator<DataCustomMaterialList> materialFileGenerator;
  private final MaterialFileReader materialFileReader;
  private final CustomMaterialListPopulator customMaterialListPopulator;
  private final GeneratedPackRemover generatedPackRemover;
  private final PackGenerator resourcePackGenerator;
  private final PackGenerator dataPackGenerator;

  public ConstructModEventHandler(
      FileGenerator<DataCustomMaterialList> materialFileGenerator,
      MaterialFileReader materialFileReader,
      CustomMaterialListPopulator customMaterialListPopulator,
      GeneratedPackRemover generatedPackRemover,
      PackGenerator resourcePackGenerator,
      PackGenerator dataPackGenerator
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