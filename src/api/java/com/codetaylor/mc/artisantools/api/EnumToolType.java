package com.codetaylor.mc.artisantools.api;

public enum EnumToolType {

  // Blacksmith
  BLACKSMITHS_CUTTERS("cutters"),
  BLACKSMITHS_HAMMER("hammer"),

  // Carpenter
  CARPENTERS_HAMMER("framing_hammer"),
  CARPENTERS_HANDSAW("handsaw"),

  // Chef
  CHEFS_CUTTING_BOARD("cutting_board"),
  CHEFS_PAN("pan"),

  // Chemist
  CHEMISTS_BEAKER("beaker"),
  CHEMISTS_BURNER("burner"),

  // Designer
  DESIGNERS_PENCIL("pencil"),
  DESIGNERS_TSQUARE("tsquare"),

  // Engineer
  ENGINEERS_DRIVER("driver"),
  ENGINEERS_SPANNER("spanner"),

  // Farmer
  FARMERS_LENS("lens"),
  FARMERS_SIFTER("sifter"),

  // Jeweler
  JEWELERS_GEMCUTTER("gemcutter"),
  JEWELERS_PLIERS("pliers"),

  // Mage
  MAGES_ATHAME("athame"),
  MAGES_GRIMOIRE("grimoire"),

  // Mason
  MASONS_CHISEL("chisel"),
  MASONS_TROWEL("trowel"),

  // Potter
  POTTERS_CARVER("carver"),
  POTTERS_RAZOR("razor"),

  // Scribe
  SCRIBES_COMPASS("compass"),
  SCRIBES_QUILL("quill"),

  // Tailor
  TAILORS_NEEDLE("needle"),
  TAILORS_SHEARS("shears"),

  // Tanner
  TANNERS_PUNCH("punch"),
  TANNERS_GROOVER("groover"),

  // Universal
  UNIVERSAL_MORTAR("mortar"),
  UNIVERSAL_KNIFE("knife"),
  UNIVERSAL_HATCHET("hatchet"),
  UNIVERSAL_FILE("file"),
  UNIVERSAL_SOLDERER("solderer");

  private final String name;

  EnumToolType(String name) {

    this.name = name;
  }

  public String getName() {

    return this.name;
  }
}
