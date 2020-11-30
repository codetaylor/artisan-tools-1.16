package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.lib.FolderCompressor;
import com.codetaylor.mc.artisantools.lib.GenerationInhibitor;
import com.codetaylor.mc.artisantools.lib.PathCreator;
import com.codetaylor.mc.artisantools.lib.PathRemover;

import java.util.function.BooleanSupplier;

public class DataPackGenerator {

  private final PathCreator recipePathCreator;
  private final PackMetaWriter packMetaWriter;
  private final RecipeGenerator recipeGenerator;
  private final GenerationInhibitor generationInhibitor;
  private final BooleanSupplier enableFolderCompression;
  private final FolderCompressor folderCompressor;
  private final PathRemover pathRemover;

  public DataPackGenerator(
      PathCreator recipePathCreator,
      PackMetaWriter packMetaWriter,
      RecipeGenerator recipeGenerator,
      GenerationInhibitor generationInhibitor,
      BooleanSupplier enableFolderCompression,
      FolderCompressor folderCompressor,
      PathRemover pathRemover
  ) {

    this.packMetaWriter = packMetaWriter;
    this.recipeGenerator = recipeGenerator;
    this.generationInhibitor = generationInhibitor;
    this.recipePathCreator = recipePathCreator;
    this.enableFolderCompression = enableFolderCompression;
    this.folderCompressor = folderCompressor;
    this.pathRemover = pathRemover;
  }

  public void generate() {

    if (this.generationInhibitor.isDisabled()) {
      return;
    }

    if (this.recipePathCreator.create()) {
      this.packMetaWriter.write();
      this.recipeGenerator.generate();

      if (this.enableFolderCompression.getAsBoolean()
          && this.folderCompressor.compress()) {
        this.pathRemover.remove();
      }
    }
  }
}