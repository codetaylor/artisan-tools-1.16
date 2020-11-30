package com.codetaylor.mc.artisantools.event;

import com.codetaylor.mc.artisantools.ArtisanToolsMod;
import com.codetaylor.mc.artisantools.api.tool.*;
import com.codetaylor.mc.artisantools.item.ItemWorktableTool;
import com.codetaylor.mc.artisantools.lib.GenerationInhibitor;
import com.codetaylor.mc.artisantools.material.CustomMaterialConverter;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class ItemRegistrationEventHandler {

  private final List<CustomMaterial> materialList;
  private final List<CustomToolMaterialRegistrationEntry> customMaterialList;
  private final GenerationInhibitor generationInhibitor;
  private final List<String> enabledToolTypeList;
  private final List<ItemWorktableToolBase> registeredToolList;
  private final CustomMaterialConverter customMaterialConverter;

  public ItemRegistrationEventHandler(
      List<CustomMaterial> materialList,
      List<CustomToolMaterialRegistrationEntry> customMaterialList,
      GenerationInhibitor generationInhibitor,
      List<String> enabledToolTypeList,
      List<ItemWorktableToolBase> registeredToolList,
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

    for (String typeName : this.enabledToolTypeList) {

      for (CustomMaterial material : this.materialList) {
        String materialName = material.getName();
        Item.Properties properties = new Item.Properties().maxStackSize(1);
        ItemWorktableToolBase item = new ItemWorktableTool(typeName, material, properties);
        this.registerTool(event.getRegistry(), typeName, materialName, item);
      }

      for (CustomToolMaterialRegistrationEntry entry : customMaterialList) {
        ICustomToolMaterial material = entry.getMaterial();
        ICustomToolProvider<?> provider = entry.getProvider();
        CustomMaterial customMaterial = this.customMaterialConverter.convert(material);
        ItemWorktableToolBase item = provider.get(typeName, customMaterial);
        String materialName = customMaterial.getName();
        this.registerTool(event.getRegistry(), typeName, materialName, item);
      }
    }
  }

  private void registerTool(IForgeRegistry<Item> registry, String typeName, String materialName, ItemWorktableToolBase item) {

    this.registeredToolList.add(item);
    ResourceLocation registryName = new ResourceLocation(ArtisanToolsMod.MOD_ID, typeName + "_" + materialName);
    item.setRegistryName(registryName);
    registry.register(item);
  }
}
