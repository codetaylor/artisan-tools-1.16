package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.lib.*;

public class PackGenerator {

  private final IPathCreator pathCreator;
  private final PackMetaWriter packMetaWriter;
  private final IPackContentGenerator contentGenerator;
  private final GenerationInhibitor generationInhibitor;
  private final boolean enableFolderCompression;
  private final FolderCompressor folderCompressor;
  private final PathRemover pathRemover;

  public PackGenerator(
      IPathCreator pathCreator,
      PackMetaWriter packMetaWriter,
      IPackContentGenerator contentGenerator,
      GenerationInhibitor generationInhibitor,
      boolean enableFolderCompression,
      FolderCompressor folderCompressor,
      PathRemover pathRemover
  ) {

    this.packMetaWriter = packMetaWriter;
    this.contentGenerator = contentGenerator;
    this.generationInhibitor = generationInhibitor;
    this.pathCreator = pathCreator;
    this.enableFolderCompression = enableFolderCompression;
    this.folderCompressor = folderCompressor;
    this.pathRemover = pathRemover;
  }

  public void generate() {

    if (this.generationInhibitor.isDisabled()) {
      return;
    }

    if (this.pathCreator.create()) {
      this.packMetaWriter.write();
      this.contentGenerator.generate();

      if (this.enableFolderCompression
          && this.folderCompressor.compress()) {
        this.pathRemover.remove();
      }
    }
  }
}