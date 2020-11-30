package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.lib.FolderCompressor;
import com.codetaylor.mc.artisantools.lib.GenerationInhibitor;
import com.codetaylor.mc.artisantools.lib.PathCreator;
import com.codetaylor.mc.artisantools.lib.PathRemover;

import java.util.function.BooleanSupplier;

public class ResourcePackGenerator {

  private final PathCreator modelPathCreator;
  private final PackMetaWriter packMetaWriter;
  private final ModelGenerator modelGenerator;
  private final GenerationInhibitor generationInhibitor;
  private final BooleanSupplier enableFolderCompression;
  private final FolderCompressor folderCompressor;
  private final PathRemover pathRemover;

  public ResourcePackGenerator(
      PathCreator modelPathCreator,
      PackMetaWriter packMetaWriter,
      ModelGenerator modelGenerator,
      GenerationInhibitor generationInhibitor,
      BooleanSupplier enableFolderCompression,
      FolderCompressor folderCompressor,
      PathRemover pathRemover
  ) {

    this.modelPathCreator = modelPathCreator;
    this.packMetaWriter = packMetaWriter;
    this.modelGenerator = modelGenerator;
    this.generationInhibitor = generationInhibitor;
    this.enableFolderCompression = enableFolderCompression;
    this.folderCompressor = folderCompressor;
    this.pathRemover = pathRemover;
  }

  public void generate() {

    if (this.generationInhibitor.isDisabled()) {
      return;
    }

    if (this.modelPathCreator.create()) {
      this.packMetaWriter.write();
      this.modelGenerator.generate();

      if (this.enableFolderCompression.getAsBoolean()
          && this.folderCompressor.compress()) {
        this.pathRemover.remove();
      }
    }
  }
}