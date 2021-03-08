package com.codetaylor.mc.artisantools.common.event;

import com.codetaylor.mc.artisantools.common.material.CustomMaterialListPopulator;
import com.codetaylor.mc.artisantools.common.material.DataCustomMaterialList;
import com.codetaylor.mc.artisantools.common.material.JsonConfigFileGenerator;
import com.codetaylor.mc.artisantools.common.material.JsonConfigFileReader;
import com.codetaylor.mc.artisantools.common.pack.GeneratedPackRemover;
import com.codetaylor.mc.artisantools.common.pack.PackGenerator;
import com.codetaylor.mc.artisantools.common.recipe.DataRecipeTemplateMap;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

public class ConstructModEventHandler {

  private final JsonConfigFileGenerator<DataCustomMaterialList> materialFileGenerator;
  private final JsonConfigFileReader<DataCustomMaterialList> materialFileReader;
  private final CustomMaterialListPopulator customMaterialListPopulator;
  private final JsonConfigFileGenerator<DataRecipeTemplateMap> recipeFileGenerator;
  private final JsonConfigFileReader<DataRecipeTemplateMap> recipeFileReader;
  private final GeneratedPackRemover generatedPackRemover;
  private final PackGenerator resourcePackGenerator;
  private final PackGenerator dataPackGenerator;

  public ConstructModEventHandler(
      JsonConfigFileGenerator<DataCustomMaterialList> materialFileGenerator,
      JsonConfigFileReader<DataCustomMaterialList> materialFileReader,
      CustomMaterialListPopulator customMaterialListPopulator,
      JsonConfigFileGenerator<DataRecipeTemplateMap> recipeFileGenerator,
      JsonConfigFileReader<DataRecipeTemplateMap> recipeFileReader,
      GeneratedPackRemover generatedPackRemover,
      PackGenerator resourcePackGenerator,
      PackGenerator dataPackGenerator
  ) {

    this.materialFileGenerator = materialFileGenerator;
    this.materialFileReader = materialFileReader;
    this.customMaterialListPopulator = customMaterialListPopulator;
    this.recipeFileGenerator = recipeFileGenerator;
    this.recipeFileReader = recipeFileReader;
    this.generatedPackRemover = generatedPackRemover;
    this.resourcePackGenerator = resourcePackGenerator;
    this.dataPackGenerator = dataPackGenerator;
  }

  @SubscribeEvent
  public void on(FMLConstructModEvent event) {

    this.materialFileGenerator.generate();
    this.materialFileReader.read();
    this.customMaterialListPopulator.populate();
    this.recipeFileGenerator.generate();
    this.recipeFileReader.read();
    this.generatedPackRemover.remove();
    this.resourcePackGenerator.generate();
    this.dataPackGenerator.generate();
  }
}