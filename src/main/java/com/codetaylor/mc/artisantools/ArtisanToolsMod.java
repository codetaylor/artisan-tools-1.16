package com.codetaylor.mc.artisantools;

import com.codetaylor.mc.artisantools.api.Reference;
import com.codetaylor.mc.artisantools.client.ClientProxy;
import com.codetaylor.mc.artisantools.common.CommonProxy;
import com.codetaylor.mc.artisantools.common.pack.injector.DataPackFinderInjector;
import net.minecraft.resources.ResourcePackList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

@Mod(ArtisanToolsMod.MOD_ID)
public class ArtisanToolsMod {

  public static final String MOD_ID = Reference.MOD_ID;
  public static final Logger LOGGER = LogManager.getLogger();

  public static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get();
  public static final Path MOD_CONFIG_PATH = CONFIG_PATH.resolve(MOD_ID);
  public static final Path GENERATED_PATH = MOD_CONFIG_PATH.resolve("generated");

  public static final Path GENERATED_RESOURCE_PACK_PATH = GENERATED_PATH.resolve("resourcepack");
  public static final Path GENERATED_RESOURCE_PACK_ZIPPED_PATH = GENERATED_PATH.resolve("resourcepack.zip");
  public static final Path GENERATED_RESOURCE_PACK_MODEL_PATH = GENERATED_RESOURCE_PACK_PATH.resolve("assets/artisantools/models/item");

  public static final Path GENERATED_DATA_PACK_PATH = GENERATED_PATH.resolve("datapack");
  public static final Path GENERATED_DATA_PACK_ZIPPED_PATH = GENERATED_PATH.resolve("datapack.zip");
  public static final Path GENERATED_DATA_PACK_RECIPE_PATH = GENERATED_DATA_PACK_PATH.resolve("data/artisantools/recipes");
  public static final Path GENERATED_DATA_PACK_TAG_ITEM_PATH = GENERATED_DATA_PACK_PATH.resolve("data/artisantools/tags/items");
  public static final Path GENERATED_DATA_PACK_TAG_ITEM_TYPE_PATH = GENERATED_DATA_PACK_PATH.resolve("data/artisantools/tags/items/type");
  public static final Path GENERATED_DATA_PACK_TAG_ITEM_MATERIAL_PATH = GENERATED_DATA_PACK_PATH.resolve("data/artisantools/tags/items/material");

  public static final String TOOL_MATERIALS_CUSTOM_JSON = "tool.materials.custom.json";
  public static final String TOOL_MATERIALS_GENERATED_JSON = "tool.materials.generated.json";

  public ArtisanToolsMod() {

    IProxy proxy = DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    proxy.initialize();
    proxy.registerModEventHandlers(FMLJavaModLoadingContext.get().getModEventBus());
    proxy.registerForgeEventHandlers(MinecraftForge.EVENT_BUS);
  }

  @SuppressWarnings("unused")
  public static void onMinecraftServer$func_240772_a_(ResourcePackList list) {

    DataPackFinderInjector.inject(list, GENERATED_PATH);
  }
}