package com.codetaylor.mc.artisantools.api.tool;

import com.codetaylor.mc.artisantools.ArtisanToolsMod;
import com.codetaylor.mc.artisantools.ArtisanToolsModConfig;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Set;

public abstract class ItemWorktableToolBase
    extends ToolItem {

  public static final String TOOLTIP_DURABILITY = "tooltip.artisantools.durability";

  protected String typeName;
  protected CustomMaterial material;
  protected Ingredient repairIngredient;
  protected String translationKey;

  private static final Logger LOGGER = LogManager.getLogger(ItemWorktableToolBase.class);

  public ItemWorktableToolBase(CustomMaterial material, Set<Block> effectiveBlocks, String typeName, Item.Properties properties) {

    super(0, 0, material, effectiveBlocks, properties);
    this.typeName = typeName;
    this.material = material;
  }

  @Override
  public boolean isEnchantable(@Nonnull ItemStack stack) {

    return ArtisanToolsModConfig.CONFIG.enableToolEnchanting.get();
  }

  @Override
  public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

    return ArtisanToolsModConfig.CONFIG.enableToolRepair.get()
        && ArtisanToolsModConfig.CONFIG.allowToolEnchantment(enchantment)
        && super.canApplyAtEnchantingTable(stack, enchantment);
  }

  @Override
  public int getItemEnchantability() {

    return this.material.getEnchantability();
  }

  @Nullable
  private Ingredient getRepairIngredient(String ingredientString) {

    String[] split = ingredientString.split("#");

    if (split.length != 2) {
      LOGGER.error("Ingredient string missing type: " + ingredientString);
      return null;
    }

    ResourceLocation resourceLocation = new ResourceLocation(split[1]);

    switch (split[0]) {
      case "item": {
        Item item = ForgeRegistries.ITEMS.getValue(resourceLocation);
        return Ingredient.fromStacks(new ItemStack(item, 1));
      }

      case "tag": {
        ITag<Item> tag = ItemTags.getCollection().get(resourceLocation);

        if (tag != null) {
          return Ingredient.fromTag(tag);

        } else {
          LOGGER.error("Unknown repair ingredient: " + ingredientString);
          return null;
        }
      }

      default:
        LOGGER.error("Unknown ingredient type: " + split[0]);
        return null;
    }
  }

  public CustomMaterial getMaterial() {

    return this.material;
  }

  @Override
  public boolean getIsRepairable(ItemStack toRepair, @Nonnull ItemStack repair) {

    if (!ArtisanToolsModConfig.CONFIG.enableToolRepair.get()) {
      return false;
    }

    if (this.repairIngredient == null) {
      this.repairIngredient = this.getRepairIngredient(this.getMaterial().getIngredientString());
    }

    return this.repairIngredient != null && this.repairIngredient.test(repair);
  }

  @Nonnull
  @Override
  public ITextComponent getDisplayName(ItemStack stack) {

    // This is called on the server so we can't use net/minecraft/client/resources/I18n here.

    Item item = stack.getItem();

    if (item instanceof ItemWorktableToolBase) {
      CustomMaterial material = ((ItemWorktableToolBase) item).getMaterial();
      TranslationTextComponent translationTextComponent = new TranslationTextComponent(material.getLangKey());
      return new TranslationTextComponent(this.getTranslationKey(stack), translationTextComponent);
    }

    return super.getDisplayName(stack);
  }

  @Nonnull
  @Override
  protected String getDefaultTranslationKey() {

    // item.artisantools.artisans.cutters.name=Artisan's %s Cutters

    if (this.translationKey == null) {
      this.translationKey = "item." + ArtisanToolsMod.MOD_ID + "." + this.typeName.replaceAll("_", ".") + ".name";
    }

    return this.translationKey;
  }

  @ParametersAreNonnullByDefault
  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {

    super.addInformation(stack, worldIn, tooltip, flag);

    if (ArtisanToolsModConfig.CONFIG.enableDurabilityTooltip.get()) {
      tooltip.add(new StringTextComponent(TextFormatting.GRAY + net.minecraft.client.resources.I18n.format(
          ItemWorktableToolBase.TOOLTIP_DURABILITY,
          stack.getMaxDamage() - stack.getDamage(),
          stack.getMaxDamage()
      )));
    }
  }
}
