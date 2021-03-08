package com.codetaylor.mc.artisantools.common;

import com.codetaylor.mc.artisantools.ArtisanToolsMod;
import com.codetaylor.mc.artisantools.ArtisanToolsModConfig;
import com.codetaylor.mc.artisantools.IProxy;
import com.codetaylor.mc.artisantools.api.CustomToolMaterial;
import com.codetaylor.mc.artisantools.api.CustomToolMaterialRegistrationEntry;
import com.codetaylor.mc.artisantools.api.EnumToolType;
import com.codetaylor.mc.artisantools.api.ItemCustomToolBase;
import com.codetaylor.mc.artisantools.common.event.ConstructModEventHandler;
import com.codetaylor.mc.artisantools.common.event.ItemRegistrationEventHandler;
import com.codetaylor.mc.artisantools.common.util.GenerationInhibitor;
import com.codetaylor.mc.artisantools.common.util.MultiPathCreator;
import com.codetaylor.mc.artisantools.common.util.PathCreator;
import com.codetaylor.mc.artisantools.common.util.PathRemover;
import com.codetaylor.mc.artisantools.common.material.*;
import com.codetaylor.mc.artisantools.common.pack.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonProxy
    implements IProxy {

  protected List<ItemCustomToolBase> registeredToolList;

  @Override
  public void initialize() {

    try {
      Files.createDirectories(ArtisanToolsMod.GENERATED_PATH);

    } catch (IOException e) {
      ArtisanToolsMod.LOGGER.error("Error creating folder: " + ArtisanToolsMod.GENERATED_PATH, e);
    }

    ModLoadingContext modLoadingContext = ModLoadingContext.get();
    modLoadingContext.registerConfig(ModConfig.Type.COMMON, ArtisanToolsModConfig.CONFIG_SPEC, ArtisanToolsMod.MOD_ID + "/artisantools.toml");
    ArtisanToolsModConfig.loadConfig(ArtisanToolsModConfig.CONFIG_SPEC, ArtisanToolsMod.MOD_CONFIG_PATH.resolve("artisantools.toml"));
  }

  @Override
  public void registerModEventHandlers(IEventBus eventBus) {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Path configPath = FMLPaths.CONFIGDIR.get().resolve(ArtisanToolsMod.MOD_ID);
    ConfigFilePathSupplier customMaterialPathSupplier = new ConfigFilePathSupplier(configPath, ArtisanToolsMod.TOOL_MATERIALS_CUSTOM_JSON);
    List<CustomToolMaterial> materialList = new ArrayList<>();
    List<CustomToolMaterialRegistrationEntry> customMaterialList = new ArrayList<>();
    this.registeredToolList = new ArrayList<>();

    List<? extends String> allowedToolTypeList = ArtisanToolsModConfig.CONFIG.enabledToolTypes.get();
    boolean disableGeneration = ArtisanToolsModConfig.CONFIG.disableGeneration.get();
    boolean enableCompression = ArtisanToolsModConfig.CONFIG.enableCompression.get();

    GenerationInhibitor generationInhibitor = new GenerationInhibitor(
        disableGeneration,
        materialList,
        allowedToolTypeList
    );

    List<String> enabledToolTypeList = Arrays.stream(EnumToolType.values())
        .map(EnumToolType::getName)
        .filter(allowedToolTypeList::contains)
        .collect(Collectors.toList());

    eventBus.register(new ConstructModEventHandler(
        new FileGenerator<>(
            gson,
            new ConfigFilePathSupplier(
                configPath,
                ArtisanToolsMod.TOOL_MATERIALS_GENERATED_JSON
            ),
            customMaterialPathSupplier,
            () -> new DataCustomMaterialListFactory().create(),
            ArtisanToolsMod.LOGGER
        ),
        new MaterialFileReader(
            gson,
            customMaterialPathSupplier,
            materialList,
            ArtisanToolsMod.LOGGER
        ),
        new CustomMaterialListPopulator(
            customMaterialList
        ),
        new GeneratedPackRemover(
            generationInhibitor,
            new PathRemover(
                ArtisanToolsMod.GENERATED_PATH,
                ArtisanToolsMod.LOGGER
            )
        ),
        new PackGenerator(
            new PathCreator(
                ArtisanToolsMod.GENERATED_RESOURCE_PACK_MODEL_PATH,
                ArtisanToolsMod.LOGGER
            ),
            new PackMetaWriter(
                gson,
                ArtisanToolsMod.GENERATED_RESOURCE_PACK_PATH,
                "Resource pack generated for Artisan Tools.",
                ArtisanToolsMod.LOGGER
            ),
            new ModelGenerator(
                gson,
                ArtisanToolsMod.GENERATED_RESOURCE_PACK_MODEL_PATH,
                materialList,
                customMaterialList,
                enabledToolTypeList,
                ArtisanToolsMod.LOGGER
            ),
            generationInhibitor,
            enableCompression,
            new FolderCompressor(
                ArtisanToolsMod.GENERATED_RESOURCE_PACK_PATH,
                ArtisanToolsMod.GENERATED_RESOURCE_PACK_ZIPPED_PATH,
                ArtisanToolsMod.LOGGER
            ),
            new PathRemover(
                ArtisanToolsMod.GENERATED_RESOURCE_PACK_PATH,
                ArtisanToolsMod.LOGGER
            )
        ),
        new PackGenerator(
            new MultiPathCreator(
                new PathCreator(
                    ArtisanToolsMod.GENERATED_DATA_PACK_RECIPE_PATH,
                    ArtisanToolsMod.LOGGER
                ),
                new PathCreator(
                    ArtisanToolsMod.GENERATED_DATA_PACK_TAG_ITEM_TYPE_PATH,
                    ArtisanToolsMod.LOGGER
                ),
                new PathCreator(
                    ArtisanToolsMod.GENERATED_DATA_PACK_TAG_ITEM_MATERIAL_PATH,
                    ArtisanToolsMod.LOGGER
                )
            ),
            new PackMetaWriter(
                gson,
                ArtisanToolsMod.GENERATED_DATA_PACK_PATH,
                "Data pack generated for Artisan Tools.",
                ArtisanToolsMod.LOGGER
            ),
            new MultiPackContentGenerator(
                new RecipeGenerator(
                    gson,
                    ArtisanToolsMod.GENERATED_DATA_PACK_RECIPE_PATH,
                    materialList,
                    customMaterialList,
                    enabledToolTypeList,
                    new RecipeTemplateFactory().create(),
                    ArtisanToolsMod.LOGGER
                ),
                new TagGenerator(
                    gson,
                    ArtisanToolsMod.GENERATED_DATA_PACK_TAG_ITEM_PATH,
                    materialList,
                    customMaterialList,
                    enabledToolTypeList,
                    ArtisanToolsMod.LOGGER
                )
            ),
            generationInhibitor,
            enableCompression,
            new FolderCompressor(
                ArtisanToolsMod.GENERATED_DATA_PACK_PATH,
                ArtisanToolsMod.GENERATED_DATA_PACK_ZIPPED_PATH,
                ArtisanToolsMod.LOGGER
            ),
            new PathRemover(
                ArtisanToolsMod.GENERATED_DATA_PACK_PATH,
                ArtisanToolsMod.LOGGER
            )
        )
    ));

    eventBus.register(new ItemRegistrationEventHandler(
        materialList,
        customMaterialList,
        generationInhibitor,
        enabledToolTypeList,
        this.registeredToolList,
        new CustomMaterialConverter()
    ));
  }

  @Override
  public void registerForgeEventHandlers(IEventBus eventBus) {
    //
  }
}
