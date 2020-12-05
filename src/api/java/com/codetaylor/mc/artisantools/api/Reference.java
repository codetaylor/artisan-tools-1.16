package com.codetaylor.mc.artisantools.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public final class Reference {

  public static final String MOD_ID = "artisantools";

  /**
   * These class fields will be populated when the mod's config is loaded.
   * They are read only -- DO NOT MODIFY.
   */
  public static final class Config {

    public static boolean enableDurabilityTooltip;
    public static boolean enableToolEnchanting;
    public static boolean enableToolRepair;
    public static List<? extends String> allowedEnchants = Collections.emptyList();

    public static boolean allowEnchantment(Enchantment enchantment) {

      ResourceLocation resourceLocation = enchantment.getRegistryName();

      if (resourceLocation == null) {
        return false;
      }

      String resourceLocationString = resourceLocation.toString();
      return Config.allowedEnchants.contains(resourceLocationString);
    }
  }

  private Reference() {
    //
  }
}
