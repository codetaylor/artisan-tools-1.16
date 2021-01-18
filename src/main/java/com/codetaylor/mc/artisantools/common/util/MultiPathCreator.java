package com.codetaylor.mc.artisantools.common.util;

public class MultiPathCreator
    implements IPathCreator {

  private final IPathCreator[] pathCreators;

  public MultiPathCreator(IPathCreator... pathCreators) {

    this.pathCreators = pathCreators;
  }

  @Override
  public boolean create() {

    for (IPathCreator pathCreator : this.pathCreators) {

      if (!pathCreator.create()) {
        return false;
      }
    }

    return true;
  }
}
