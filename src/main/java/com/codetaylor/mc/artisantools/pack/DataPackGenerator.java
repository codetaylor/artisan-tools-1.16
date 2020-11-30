package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.lib.GenerationInhibitor;
import com.codetaylor.mc.artisantools.lib.PathCreator;

public class DataPackGenerator {

  private final PathCreator recipePathCreator;
  private final PackMetaWriter packMetaWriter;
  private final RecipeGenerator recipeGenerator;
  private final GenerationInhibitor generationInhibitor;

  public DataPackGenerator(
      PathCreator recipePathCreator,
      PackMetaWriter packMetaWriter,
      RecipeGenerator recipeGenerator,
      GenerationInhibitor generationInhibitor
  ) {

    this.packMetaWriter = packMetaWriter;
    this.recipeGenerator = recipeGenerator;
    this.generationInhibitor = generationInhibitor;
    this.recipePathCreator = recipePathCreator;
  }

  public void generate() {

    if (this.generationInhibitor.isDisabled()) {
      return;
    }

    if (this.recipePathCreator.create()) {
      this.packMetaWriter.write();
      this.recipeGenerator.generate();
    }
  }
}