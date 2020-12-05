package com.codetaylor.mc.artisantools;

import com.codetaylor.mc.artisantools.api.EnumToolType;
import com.codetaylor.mc.artisantools.api.Reference;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArtisanToolsModConfig {

  public static void loadConfig(ForgeConfigSpec spec, Path path) {

    final CommentedFileConfig configData = CommentedFileConfig.builder(path)
        .sync()
        .autosave()
        .writingMode(WritingMode.REPLACE)
        .build();

    configData.load();
    spec.setConfig(configData);
    ArtisanToolsModConfig.bake();
  }

  public static ForgeConfigSpec CONFIG_SPEC;
  public static Config CONFIG;

  static {
    ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    CONFIG = new Config(builder);
    CONFIG_SPEC = builder.build();
  }

  @SubscribeEvent
  public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {

    if (configEvent.getConfig().getSpec() == CONFIG_SPEC) {
      ArtisanToolsModConfig.bake();
    }
  }

  private static void bake() {

    Reference.Config.enableDurabilityTooltip = CONFIG.enableDurabilityTooltip.get();
    Reference.Config.enableToolEnchanting = CONFIG.enableToolEnchanting.get();
    Reference.Config.enableToolRepair = CONFIG.enableToolRepair.get();
    Reference.Config.allowedEnchants = CONFIG.allowedEnchants.get();
  }

  public static class Config {

    public final ForgeConfigSpec.ConfigValue<String> creativeTabItem;
    public final ForgeConfigSpec.BooleanValue enableToolEnchanting;
    public final ForgeConfigSpec.BooleanValue enableToolRepair;
    public final ForgeConfigSpec.BooleanValue enableDurabilityTooltip;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> allowedEnchants;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> enabledToolTypes;
    public final ForgeConfigSpec.BooleanValue disableGeneration;
    public final ForgeConfigSpec.BooleanValue enableCompression;

    public Config(ForgeConfigSpec.Builder builder) {

      this.creativeTabItem = builder
          .comment(
              "This item will be used as the creative tab icon.",
              "Default: " + "artisantools:cutters_iron"
          )
          .define("creativeTabItem", "artisantools:cutters_iron");

      this.enableToolEnchanting = builder
          .comment(
              "Set to false to disable tool enchanting.",
              "Default: " + true
          )
          .define("enableToolEnchanting", true);

      this.enableToolRepair = builder
          .comment(
              "Set to false to disable tool repair.",
              "Default: " + true
          )
          .define("enableToolRepair", true);

      this.enableDurabilityTooltip = builder
          .comment(
              "Set to false to disable tool durability tooltip.",
              "Default: " + true
          )
          .define("enableDurabilityTooltip", true);

      this.allowedEnchants = builder
          .comment(
              "Enchants allowed at the enchanting table."
          )
          .defineList(
              "allowedEnchants",
              Arrays.stream(new String[]{
                  "minecraft:unbreaking",
                  "minecraft:mending"
              }).collect(Collectors.toList()),
              o -> true
          );

      this.enabledToolTypes = builder
          .comment(
              "Enabled tool types."
          )
          .defineList(
              "enabledToolTypes",
              Arrays.stream(EnumToolType.values())
                  .map(EnumToolType::getName)
                  .collect(Collectors.toList()),
              o -> true
          );

      this.disableGeneration = builder
          .comment(
              "This mod will regenerate content on each game load.",
              "Set to true to stop content generation on each game load.",
              "This is something that you will probably want to set to true before distributing your mod pack.",
              "Default: " + false
          )
          .define("disableGeneration", false);

      this.enableCompression = builder
          .comment(
              "Set to true to compress the generated resource and data packs.",
              "This will compress the packs and remove the folders.",
              "This is something that you will probably want to set to true before distributing your mod pack.",
              "Default: " + false
          )
          .define("enableCompression", false);

    }

  }
}
