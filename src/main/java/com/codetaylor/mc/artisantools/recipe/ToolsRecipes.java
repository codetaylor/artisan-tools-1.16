package com.codetaylor.mc.artisantools.recipe;

import com.codetaylor.mc.artisantools.api.tool.ItemWorktableToolBase;
import com.codetaylor.mc.artisantools.api.tool.reference.EnumToolType;
import com.codetaylor.mc.artisantools.lib.MalformedRecipeItemException;
import com.codetaylor.mc.artisantools.lib.ParseResult;
import com.codetaylor.mc.artisantools.lib.RecipeItemParser;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class ToolsRecipes {

  /**
   * Contains the recipe definitions for each tool type.
   */
  private static final EnumMap<EnumToolType, Object[]> RECIPE_MAP;

  /**
   * The alias used for ingredient substitution. This is replaced before a recipe is registered.
   */
  private static final String MATERIAL_ALIAS = "#material_alias";

  static {
    RECIPE_MAP = new EnumMap<>(EnumToolType.class);

    RECIPE_MAP.put(
        EnumToolType.BLACKSMITHS_CUTTERS,
        new Object[]{
            ". .",
            " x ",
            "s s",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.BLACKSMITHS_HAMMER,
        new Object[]{
            " .x",
            " s.",
            "s  ",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.CARPENTERS_HAMMER,
        new Object[]{
            " ..",
            " sx",
            "s  ",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.CARPENTERS_HANDSAW,
        new Object[]{
            " .s",
            ".s ",
            "s  ",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.JEWELERS_GEMCUTTER,
        new Object[]{
            "  x",
            " ..",
            "s  ",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.JEWELERS_PLIERS,
        new Object[]{
            ". .",
            "sxs",
            "s s",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.MASONS_CHISEL,
        new Object[]{
            "  .",
            " . ",
            "s  ",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.MASONS_TROWEL,
        new Object[]{
            "  .",
            " s.",
            "s  ",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.TAILORS_NEEDLE,
        new Object[]{
            "  .",
            " .x",
            "s  ",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.TAILORS_SHEARS,
        new Object[]{
            " . ",
            "sx.",
            " s ",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.ENGINEERS_DRIVER,
        new Object[]{
            "  .",
            " . ",
            "sx ",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.ENGINEERS_SPANNER,
        new Object[]{
            " . ",
            " s.",
            "s  ",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.MAGES_ATHAME,
        new Object[]{
            "  .",
            "x. ",
            "sl ",
            'l', "forge:gems/lapis",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.MAGES_GRIMOIRE,
        new Object[]{
            " . ",
            "xbx",
            " . ",
            'b', Items.BOOK,
            'x', "forge:string",
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.SCRIBES_COMPASS,
        new Object[]{
            " s ",
            "sxs",
            ". .",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.SCRIBES_QUILL,
        new Object[]{
            "  f",
            " .x",
            ".  ",
            'x', "forge:string",
            'f', Items.FEATHER,
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.CHEMISTS_BURNER,
        new Object[]{
            " . ",
            " . ",
            "ppp",
            'p', "minecraft:planks",
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.CHEMISTS_BEAKER,
        new Object[]{
            ".g.",
            " g ",
            'g', "forge:glass",
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.FARMERS_LENS,
        new Object[]{
            "   ",
            ".g.",
            "s  ",
            'g', "forge:glass_panes/colorless",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.FARMERS_SIFTER,
        new Object[]{
            "s.s",
            "sxs",
            "s.s",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.CHEFS_CUTTING_BOARD,
        new Object[]{
            "  s",
            " . ",
            ".bb",
            'b', "minecraft:wooden_slabs",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.CHEFS_PAN,
        new Object[]{
            "s  ",
            "c..",
            'c', Items.CLAY_BALL,
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.UNIVERSAL_MORTAR,
        new Object[]{
            "  s",
            ".f ",
            " . ",
            'f', Items.FLINT,
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.UNIVERSAL_KNIFE,
        new Object[]{
            "  .",
            "x. ",
            "sx ",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.TANNERS_PUNCH,
        new Object[]{
            "  .",
            " sx",
            ".x ",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.TANNERS_GROOVER,
        new Object[]{
            ". ",
            "s.",
            "w ",
            'w', "minecraft:planks",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.POTTERS_CARVER,
        new Object[]{
            "  .",
            "xsx",
            ".  ",
            'x', "forge:string",
            's', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.POTTERS_RAZOR,
        new Object[]{
            " . ",
            "x. ",
            "w  ",
            'x', "forge:string",
            'w', "minecraft:planks",
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.UNIVERSAL_HATCHET,
        new Object[]{
            " . ",
            "./ ",
            "/  ",
            '/', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.UNIVERSAL_FILE,
        new Object[]{
            " /.",
            "/. ",
            "/  ",
            '/', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.DESIGNERS_PENCIL,
        new Object[]{
            "  c",
            "./.",
            "/  ",
            'c', new ItemStack(Items.COAL, 1),
            '/', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.DESIGNERS_TSQUARE,
        new Object[]{
            "///",
            " . ",
            " . ",
            '/', new ItemStack(Items.STICK),
            '.', MATERIAL_ALIAS
        }
    );

    RECIPE_MAP.put(
        EnumToolType.UNIVERSAL_SOLDERER,
        new Object[]{
            "  .",
            " . ",
            "c  ",
            'c', Items.CLAY_BALL,
            '.', MATERIAL_ALIAS
        }
    );
  }

  /**
   * Iterates through all given items in the tool list and registers a recipe for each.
   *
   * @param registry the recipe registry
   * @param modId    the mod id
   * @param toolList the tool list
   */
  public static void register(Map<ResourceLocation, IRecipe<?>> registry, String modId, List<ItemWorktableToolBase> toolList) {

    RecipeItemParser recipeItemParser = RecipeItemParser.INSTANCE;

    for (ItemWorktableToolBase item : toolList) {

      try {
        // Convert ingredient
        String ingredientString = item.getMaterial().getIngredientString();

        ParseResult parseResult = recipeItemParser.parse(ingredientString);

        if (parseResult == ParseResult.NULL) {
          throw new MalformedRecipeItemException("Unable to parse ingredient [" + item.getMaterial()
              .getIngredientString() + "] for material [" + item.getMaterial() + "]");
        }

        Object ingredient;

        if ("ore".equals(parseResult.getDomain())) {
          ingredient = parseResult.getPath();

        } else {

          Item parsedItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(parseResult.getDomain(), parseResult.getPath()));

          if (parsedItem == null) {
            throw new MalformedRecipeItemException("Unable to find registered item: " + parseResult.toString());
          }

          ingredient = new ItemStack(parsedItem, 1);
        }

        /*Object[] recipeDefinition = ToolsRecipes.getRecipeDefinition(
            item.getType(),
            ingredient
        );

        if (recipeDefinition == null) {
          throw new RuntimeException("Missing recipe definition for tool type: " + item.getType().getName());
        }

        ResourceLocation resourceLocation = new ResourceLocation(
            modId,
            "recipe." + item.getType().getName() + "." + item.getMaterial().getName()
        );*/
//        ShapedPrimer shapedPrimer = ToolsRecipes.parseShaped(recipeDefinition);
//        ShapedRecipe shapedRecipe = new ShapedRecipe(resourceLocation, "", shapedPrimer.width, shapedPrimer.height, shapedPrimer.input, new ItemStack(item));
//        registry.put(resourceLocation, shapedRecipe);

      } catch (Exception e) {
        throw new RuntimeException("Error registering recipe", e);
      }
    }
  }

  /**
   * Returns an object array containing the recipe shape and ingredient composition. Replaces
   * the material alias token with the given substitution.
   *
   * @param type         the tool type
   * @param substitution the substitution
   * @return object array containing the recipe shape and ingredient composition
   */
  private static Object[] getRecipeDefinition(EnumToolType type, Object substitution) {

    Object[] objects = RECIPE_MAP.get(type);

    if (objects == null) {
      throw new RuntimeException("Missing recipe definition for: " + type);
    }

    Object[] result = new Object[objects.length];
    System.arraycopy(objects, 0, result, 0, objects.length);

    for (int i = 0; i < result.length; i++) {

      if (result[i] == MATERIAL_ALIAS) {
        result[i] = substitution;
      }
    }

    return result;
  }


  private static class ShapedPrimer {

    public int height, width;
    public boolean mirrored = true;
    public NonNullList<Ingredient> input;
  }
}
