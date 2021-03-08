package com.codetaylor.mc.artisantools.common.recipe;

import com.codetaylor.mc.artisantools.api.EnumToolType;

import java.util.HashMap;
import java.util.Map;

public class DataRecipeTemplateMapFactory {

  public DataRecipeTemplateMap create() {

    Map<String, DataRecipeTemplate> map = new HashMap<>();

    map.put(
        EnumToolType.BLACKSMITHS_CUTTERS.getName(),
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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
        new DataRecipeTemplate(
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

    map.put(
        EnumToolType.UNIVERSAL_PAINTBRUSH.getName(),
        new DataRecipeTemplate(
            new String[]{
                " ww",
                ".. ",
                "/  "
            },
            new String[]{
                "/", "item#minecraft:stick",
                "w", "item#minecraft:wheat"
            }
        )
    );

    DataRecipeTemplateMap result = new DataRecipeTemplateMap();
    result.recipes = map;
    return result;
  }
}
