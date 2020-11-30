package com.codetaylor.mc.artisantools;

import com.codetaylor.mc.artisantools.api.tool.reference.EnumWorktableToolType;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

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
  }

  public static ForgeConfigSpec CONFIG_SPEC;
  public static Config CONFIG;

  static {
    ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    CONFIG = new Config(builder);
    CONFIG_SPEC = builder.build();
  }

  public static class Config {

    public final ForgeConfigSpec.BooleanValue enableToolEnchanting;
    public final ForgeConfigSpec.BooleanValue enableToolRepair;
    public final ForgeConfigSpec.BooleanValue enableDurabilityTooltip;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> allowedEnchants;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> enabledToolTypes;

    public Config(ForgeConfigSpec.Builder builder) {

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
              Arrays.stream(EnumWorktableToolType.values())
                  .map(EnumWorktableToolType::getName)
                  .collect(Collectors.toList()),
              o -> true
          );
    }

    public boolean allowToolEnchantment(Enchantment enchantment) {

      ResourceLocation resourceLocation = enchantment.getRegistryName();

      if (resourceLocation == null) {
        return false;
      }

      String resourceLocationString = resourceLocation.toString();
      return Arrays.asList(this.allowedEnchants.get()).contains(resourceLocationString);
    }
  }
}
