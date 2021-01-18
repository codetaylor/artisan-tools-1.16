package com.codetaylor.mc.artisantools.pack;

public class MultiPackContentGenerator
    implements IPackContentGenerator {

  private final IPackContentGenerator[] generators;

  public MultiPackContentGenerator(IPackContentGenerator... generators) {

    this.generators = generators;
  }

  @Override
  public void generate() {

    for (IPackContentGenerator generator : this.generators) {
      generator.generate();
    }
  }
}
