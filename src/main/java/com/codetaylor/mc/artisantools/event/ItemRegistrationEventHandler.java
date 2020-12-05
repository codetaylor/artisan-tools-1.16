package com.codetaylor.mc.artisantools.event;

import com.codetaylor.mc.artisantools.ArtisanToolsMod;
import com.codetaylor.mc.artisantools.ArtisanToolsModConfig;
import com.codetaylor.mc.artisantools.api.*;
import com.codetaylor.mc.artisantools.item.ItemWorktableTool;
import com.codetaylor.mc.artisantools.lib.GenerationInhibitor;
import com.codetaylor.mc.artisantools.material.CustomMaterialConverter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class ItemRegistrationEventHandler {

  private final List<CustomToolMaterial> materialList;
  private final List<CustomToolMaterialRegistrationEntry> customMaterialList;
  private final GenerationInhibitor generationInhibitor;
  private final List<String> enabledToolTypeList;
  private final List<ItemCustomToolBase> registeredToolList;
  private final CustomMaterialConverter customMaterialConverter;

  public ItemRegistrationEventHandler(
      List<CustomToolMaterial> materialList,
      List<CustomToolMaterialRegistrationEntry> customMaterialList,
      GenerationInhibitor generationInhibitor,
      List<String> enabledToolTypeList,
      List<ItemCustomToolBase> registeredToolList,
      CustomMaterialConverter customMaterialConverter
  ) {

    this.materialList = materialList;
    this.customMaterialList = customMaterialList;
    this.generationInhibitor = generationInhibitor;
    this.enabledToolTypeList = enabledToolTypeList;
    this.registeredToolList = registeredToolList;
    this.customMaterialConverter = customMaterialConverter;
  }

  @SubscribeEvent
  public void onItemRegistrationEvent(RegistryEvent.Register<Item> event) {

    if (this.generationInhibitor.isDisabled()) {
      return;
    }

    ItemGroup itemGroup = new ItemGroup(ArtisanToolsMod.MOD_ID) {

      @Override
      public ItemStack createIcon() {

        return new ItemStack(
            ForgeRegistries.ITEMS.getValue(
                new ResourceLocation(
                    ArtisanToolsModConfig.CONFIG.creativeTabItem.get()
                )
            )
        );
      }
    };

    for (String typeName : this.enabledToolTypeList) {

      for (CustomToolMaterial material : this.materialList) {
        String materialName = material.getName();
        Item.Properties properties = new Item.Properties().maxStackSize(1).group(itemGroup);
        ItemCustomToolBase item = new ItemWorktableTool(typeName, material, properties);
        this.registerTool(event.getRegistry(), typeName, materialName, item);
      }

      for (CustomToolMaterialRegistrationEntry entry : customMaterialList) {
        ICustomToolMaterial material = entry.getMaterial();
        ICustomToolProvider<?> provider = entry.getProvider();
        CustomToolMaterial customToolMaterial = this.customMaterialConverter.convert(material);
        Item.Properties properties = new Item.Properties().maxStackSize(1).group(itemGroup);
        ItemCustomToolBase item = provider.get(typeName, customToolMaterial, properties);
        String materialName = customToolMaterial.getName();
        this.registerTool(event.getRegistry(), typeName, materialName, item);
      }
    }
  }

  private void registerTool(IForgeRegistry<Item> registry, String typeName, String materialName, ItemCustomToolBase item) {

    this.registeredToolList.add(item);
    ResourceLocation registryName = new ResourceLocation(ArtisanToolsMod.MOD_ID, typeName + "_" + materialName);
    item.setRegistryName(registryName);
    registry.register(item);
  }
}
