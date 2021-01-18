package com.codetaylor.mc.artisantools.common.pack;

public class PackMeta {

  private final Pack pack;

  public PackMeta(String description) {

    this.pack = new Pack(description);
  }

  public static class Pack {

    private final int pack_format = 6;
    private final String description;

    public Pack(String description) {

      this.description = description;
    }
  }
}


