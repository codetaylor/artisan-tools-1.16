package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.lib.GenerationInhibitor;
import com.codetaylor.mc.artisantools.lib.PathCreator;

public class ResourcePackGenerator {

  private final PathCreator modelPathCreator;
  private final PackMetaWriter packMetaWriter;
  private final ModelGenerator modelGenerator;
  private final GenerationInhibitor generationInhibitor;

  public ResourcePackGenerator(
      PathCreator modelPathCreator,
      PackMetaWriter packMetaWriter,
      ModelGenerator modelGenerator,
      GenerationInhibitor generationInhibitor
  ) {

    this.modelPathCreator = modelPathCreator;
    this.packMetaWriter = packMetaWriter;
    this.modelGenerator = modelGenerator;
    this.generationInhibitor = generationInhibitor;
  }

  public void generate() {

    if (this.generationInhibitor.isDisabled()) {
      return;
    }

    if (this.modelPathCreator.create()) {
      this.packMetaWriter.write();
      this.modelGenerator.generate();
    }
  }
}