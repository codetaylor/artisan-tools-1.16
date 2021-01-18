package com.codetaylor.mc.artisantools.pack;

import com.codetaylor.mc.artisantools.api.EnumToolType;

import java.util.HashMap;
import java.util.Map;

public class RecipeTemplateFactory {

  public Map<String, RecipeTemplate> create() {

    Map<String, RecipeTemplate> map = new HashMap<>();

    map.put(
        EnumToolType.BLACKSMITHS_CUTTERS.getName(),
        new RecipeTemplate(
            new String[]{
                ". .",
                " x ",
                "/ /"
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.BLACKSMITHS_HAMMER.getName(),
        new RecipeTemplate(
            new String[]{
                " .x",
                " /.",
                "/  "
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.CARPENTERS_HAMMER.getName(),
        new RecipeTemplate(
            new String[]{
                " ..",
                " /x",
                "/  "
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.CARPENTERS_HANDSAW.getName(),
        new RecipeTemplate(
            new String[]{
                " ./",
                "./ ",
                "/  "
            },
            new String[]{
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.JEWELERS_GEMCUTTER.getName(),
        new RecipeTemplate(
            new String[]{
                "  x",
                " ..",
                "/  "
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.JEWELERS_PLIERS.getName(),
        new RecipeTemplate(
            new String[]{
                ". .",
                "/x/",
                "/ /"
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.MASONS_CHISEL.getName(),
        new RecipeTemplate(
            new String[]{
                "  .",
                " . ",
                "/  "
            },
            new String[]{
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.MASONS_TROWEL.getName(),
        new RecipeTemplate(
            new String[]{
                "  .",
                " /.",
                "/  "
            },
            new String[]{
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.TAILORS_NEEDLE.getName(),
        new RecipeTemplate(
            new String[]{
                "  .",
                " .x",
                "/  "
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.TAILORS_SHEARS.getName(),
        new RecipeTemplate(
            new String[]{
                " . ",
                "/x.",
                " / "
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.ENGINEERS_DRIVER.getName(),
        new RecipeTemplate(
            new String[]{
                "  .",
                " . ",
                "/x "
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.ENGINEERS_SPANNER.getName(),
        new RecipeTemplate(
            new String[]{
                " . ",
                " /.",
                "/  "
            },
            new String[]{
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.MAGES_ATHAME.getName(),
        new RecipeTemplate(
            new String[]{
                "  .",
                "x. ",
                "/l "
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick",
                "l", "tag#forge:gems/lapis"
            }
        )
    );

    map.put(
        EnumToolType.MAGES_GRIMOIRE.getName(),
        new RecipeTemplate(
            new String[]{
                " . ",
                "xbx",
                " . "
            },
            new String[]{
                "x", "tag#forge:string",
                "b", "item#minecraft:book"
            }
        )
    );

    map.put(
        EnumToolType.SCRIBES_COMPASS.getName(),
        new RecipeTemplate(
            new String[]{
                " / ",
                "/x/",
                ". ."
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.SCRIBES_QUILL.getName(),
        new RecipeTemplate(
            new String[]{
                "  f",
                " .x",
                ".  "
            },
            new String[]{
                "x", "tag#forge:string",
                "f", "tag#forge:feathers"
            }
        )
    );

    map.put(
        EnumToolType.CHEMISTS_BURNER.getName(),
        new RecipeTemplate(
            new String[]{
                " . ",
                " . ",
                "ppp"
            },
            new String[]{
                "p", "tag#minecraft:planks"
            }
        )
    );

    map.put(
        EnumToolType.CHEMISTS_BEAKER.getName(),
        new RecipeTemplate(
            new String[]{
                ".g.",
                " g "
            },
            new String[]{
                "g", "tag#forge:glass"
            }
        )
    );

    map.put(
        EnumToolType.FARMERS_LENS.getName(),
        new RecipeTemplate(
            new String[]{
                ".g.",
                "/  "
            },
            new String[]{
                "g", "tag#forge:glass_panes/colorless",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.FARMERS_SIFTER.getName(),
        new RecipeTemplate(
            new String[]{
                "/./",
                "/x/",
                "/./"
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.CHEFS_CUTTING_BOARD.getName(),
        new RecipeTemplate(
            new String[]{
                "  /",
                " . ",
                ".bb"
            },
            new String[]{
                "b", "tag#minecraft:wooden_slabs",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.CHEFS_PAN.getName(),
        new RecipeTemplate(
            new String[]{
                "/  ",
                "c.."
            },
            new String[]{
                "c", "item#minecraft:clay_ball",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.UNIVERSAL_MORTAR.getName(),
        new RecipeTemplate(
            new String[]{
                "  /",
                ".f ",
                " . "
            },
            new String[]{
                "f", "item#minecraft:flint",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.UNIVERSAL_KNIFE.getName(),
        new RecipeTemplate(
            new String[]{
                "  .",
                "x. ",
                "/x "
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.TANNERS_PUNCH.getName(),
        new RecipeTemplate(
            new String[]{
                "  .",
                " /x",
                ".x "
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.TANNERS_GROOVER.getName(),
        new RecipeTemplate(
            new String[]{
                ". ",
                "/.",
                "w "
            },
            new String[]{
                "w", "tag#minecraft:planks",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.POTTERS_CARVER.getName(),
        new RecipeTemplate(
            new String[]{
                "  .",
                "x/x",
                ".  "
            },
            new String[]{
                "x", "tag#forge:string",
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.POTTERS_RAZOR.getName(),
        new RecipeTemplate(
            new String[]{
                " . ",
                "x. ",
                "w  "
            },
            new String[]{
                "x", "tag#forge:string",
                "w", "tag#minecraft:planks"
            }
        )
    );

    map.put(
        EnumToolType.UNIVERSAL_HATCHET.getName(),
        new RecipeTemplate(
            new String[]{
                " . ",
                "./ ",
                "/  "
            },
            new String[]{
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.UNIVERSAL_FILE.getName(),
        new RecipeTemplate(
            new String[]{
                " /.",
                "/. ",
                "/  "
            },
            new String[]{
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.DESIGNERS_PENCIL.getName(),
        new RecipeTemplate(
            new String[]{
                "  c",
                "./.",
                "/  "
            },
            new String[]{
                "/", "item#minecraft:stick",
                "c", "item#minecraft:coal"
            }
        )
    );

    map.put(
        EnumToolType.DESIGNERS_TSQUARE.getName(),
        new RecipeTemplate(
            new String[]{
                "///",
                " . ",
                " . "
            },
            new String[]{
                "/", "item#minecraft:stick"
            }
        )
    );

    map.put(
        EnumToolType.UNIVERSAL_SOLDERER.getName(),
        new RecipeTemplate(
            new String[]{
                "  .",
                " . ",
                "c  "
            },
            new String[]{
                "c", "item#minecraft:clay_ball"
            }
        )
    );

    return map;
  }
}
