package com.codetaylor.mc.artisantools.lib;

import java.util.List;
import java.util.function.Predicate;

public class EnabledToolTypePredicate
    implements Predicate<String> {

  private final List<? extends String> allowedToolTypes;

  public EnabledToolTypePredicate(List<? extends String> allowedToolTypes) {

    this.allowedToolTypes = allowedToolTypes;
  }

  @Override
  public boolean test(String s) {

    return this.allowedToolTypes.contains(s);
  }
}
