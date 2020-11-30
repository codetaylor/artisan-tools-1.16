package com.codetaylor.mc.artisantools;

import com.codetaylor.mc.artisantools.api.tool.CustomMaterial;
import com.codetaylor.mc.artisantools.api.tool.CustomToolMaterialRegistrationEntry;
import com.codetaylor.mc.artisantools.api.tool.ItemWorktableToolBase;
import com.codetaylor.mc.artisantools.api.tool.reference.EnumWorktableToolType;
import com.codetaylor.mc.artisantools.event.ConstructModEventHandler;
import com.codetaylor.mc.artisantools.event.ItemColorEventHandler;
import com.codetaylor.mc.artisantools.event.ItemRegistrationEventHandler;
import com.codetaylor.mc.artisantools.lib.*;
import com.codetaylor.mc.artisantools.material.*;
import com.codetaylor.mc.artisantools.pack.*;
import com.codetaylor.mc.artisantools.pack.injector.DataPackFinderInjector;
import com.codetaylor.mc.artisantools.pack.injector.ResourcePackFinderInjector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.resources.ResourcePackList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mod(ArtisanToolsMod.MOD_ID)
public class ArtisanToolsMod {

  public static final String MOD_ID = "artisantools";
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

  private static final String TOOL_MATERIALS_CUSTOM_JSON = "tool.materials.custom.json";
  private static final String TOOL_MATERIALS_GENERATED_JSON = "tool.materials.generated.json";

  public ArtisanToolsMod() {

    try {
      Files.createDirectories(GENERATED_PATH);

    } catch (IOException e) {
      LOGGER.error("Error creating folder: " + GENERATED_PATH, e);
    }

    ModLoadingContext modLoadingContext = ModLoadingContext.get();
    modLoadingContext.registerConfig(ModConfig.Type.COMMON, ArtisanToolsModConfig.CONFIG_SPEC, MOD_ID + "/artisantools.toml");
    ArtisanToolsModConfig.loadConfig(ArtisanToolsModConfig.CONFIG_SPEC, MOD_CONFIG_PATH.resolve("artisantools.toml"));

    List<CustomMaterial> materialList = new ArrayList<>();
    List<CustomToolMaterialRegistrationEntry> customMaterialList = new ArrayList<>();
    List<ItemWorktableToolBase> registeredToolList = new ArrayList<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Path configPath = FMLPaths.CONFIGDIR.get().resolve(MOD_ID);
    ConfigFilePathSupplier customMaterialPathSupplier = new ConfigFilePathSupplier(configPath, TOOL_MATERIALS_CUSTOM_JSON);

    List<? extends String> allowedToolTypeList = ArtisanToolsModConfig.CONFIG.enabledToolTypes.get();
    boolean disableGeneration = ArtisanToolsModConfig.CONFIG.disableGeneration.get();
    boolean enableCompression = ArtisanToolsModConfig.CONFIG.enableCompression.get();

    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    GenerationInhibitor generationInhibitor = new GenerationInhibitor(
        disableGeneration,
        materialList,
        allowedToolTypeList
    );

    List<String> enabledToolTypeList = Arrays.stream(EnumWorktableToolType.values())
        .map(EnumWorktableToolType::getName)
        .filter(allowedToolTypeList::contains)
        .collect(Collectors.toList());

    modEventBus.register(new ConstructModEventHandler(
        new MaterialFileGenerator(
            gson,
            new ConfigFilePathSupplier(
                configPath,
                TOOL_MATERIALS_GENERATED_JSON
            ),
            customMaterialPathSupplier,
            LOGGER
        ),
        new MaterialFileReader(
            gson,
            customMaterialPathSupplier,
            materialList,
            LOGGER
        ),
        new CustomMaterialListPopulator(
            customMaterialList
        ),
        new GeneratedPackRemover(
            generationInhibitor,
            new PathRemover(
                GENERATED_PATH,
                LOGGER
            )
        ),
        new PackGenerator(
            new PathCreator(
                GENERATED_RESOURCE_PACK_MODEL_PATH,
                LOGGER
            ),
            new PackMetaWriter(
                gson,
                GENERATED_RESOURCE_PACK_PATH,
                "Resource pack generated for Artisan Tools.",
                LOGGER
            ),
            new ModelGenerator(
                gson,
                GENERATED_RESOURCE_PACK_MODEL_PATH,
                materialList,
                customMaterialList,
                enabledToolTypeList,
                LOGGER
            ),
            generationInhibitor,
            enableCompression,
            new FolderCompressor(
                GENERATED_RESOURCE_PACK_PATH,
                GENERATED_RESOURCE_PACK_ZIPPED_PATH,
                LOGGER
            ),
            new PathRemover(
                GENERATED_RESOURCE_PACK_PATH,
                LOGGER
            )
        ),
        new PackGenerator(
            new MultiPathCreator(
                new PathCreator(
                    GENERATED_DATA_PACK_RECIPE_PATH,
                    LOGGER
                ),
                new PathCreator(
                    GENERATED_DATA_PACK_TAG_ITEM_TYPE_PATH,
                    LOGGER
                ),
                new PathCreator(
                    GENERATED_DATA_PACK_TAG_ITEM_MATERIAL_PATH,
                    LOGGER
                )
            ),
            new PackMetaWriter(
                gson,
                GENERATED_DATA_PACK_PATH,
                "Data pack generated for Artisan Tools.",
                LOGGER
            ),
            new MultiPackContentGenerator(
                new RecipeGenerator(
                    gson,
                    GENERATED_DATA_PACK_RECIPE_PATH,
                    materialList,
                    customMaterialList,
                    enabledToolTypeList,
                    LOGGER
                ),
                new TagGenerator(
                    gson,
                    GENERATED_DATA_PACK_TAG_ITEM_PATH,
                    materialList,
                    customMaterialList,
                    enabledToolTypeList,
                    LOGGER
                )
            ),
            generationInhibitor,
            enableCompression,
            new FolderCompressor(
                GENERATED_DATA_PACK_PATH,
                GENERATED_DATA_PACK_ZIPPED_PATH,
                LOGGER
            ),
            new PathRemover(
                GENERATED_DATA_PACK_PATH,
                LOGGER
            )
        )
    ));

    modEventBus.register(new ItemColorEventHandler(
        registeredToolList
    ));

    modEventBus.register(new ItemRegistrationEventHandler(
        materialList,
        customMaterialList,
        generationInhibitor,
        enabledToolTypeList,
        registeredToolList,
        new CustomMaterialConverter()
    ));

    DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ResourcePackFinderInjector.inject(GENERATED_PATH));
  }

  @SuppressWarnings("unused")
  public static void onMinecraftServer$func_240772_a_(ResourcePackList list) {

    DataPackFinderInjector.inject(list, GENERATED_PATH);
  }
}