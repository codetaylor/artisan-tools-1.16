package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.lib.GenerationInhibitor;
import com.codetaylor.mc.artisantools.lib.PathRemover;

public class GeneratedPackRemover {

  private final GenerationInhibitor generationInhibitor;
  private final PathRemover pathRemover;

  public GeneratedPackRemover(GenerationInhibitor generationInhibitor, PathRemover pathRemover) {

    this.generationInhibitor = generationInhibitor;
    this.pathRemover = pathRemover;
  }

  public void remove() {

    if (this.generationInhibitor.isDisabled()) {
      return;
    }

    this.pathRemover.remove();
  }
}
