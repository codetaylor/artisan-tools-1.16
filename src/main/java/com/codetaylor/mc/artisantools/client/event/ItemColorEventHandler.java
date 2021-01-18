package com.codetaylor.mc.artisantools.client.event;

import com.codetaylor.mc.artisantools.api.ItemCustomToolBase;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class ItemColorEventHandler {

  private final List<ItemCustomToolBase> registeredToolList;

  public ItemColorEventHandler(List<ItemCustomToolBase> registeredToolList) {

    this.registeredToolList = registeredToolList;
  }

  @SubscribeEvent
  public void on(ColorHandlerEvent.Item event) {

    if (this.registeredToolList.isEmpty()) {
      // No tools were registered.
      return;
    }

    // Register item color handlers to colorize layer 1 of each item model
    // using the color stored in the material enum.

    ItemColors itemColors = event.getItemColors();

    itemColors.register(
        (itemStack, tintIndex) -> (tintIndex == 1)
            ? ((ItemCustomToolBase) itemStack.getItem()).getMaterial().getColor()
            : 0xFFFFFF,
        this.registeredToolList.stream()
            .map(itemWorktableToolBase -> (IItemProvider) () -> itemWorktableToolBase)
            .toArray(IItemProvider[]::new)
    );
  }
}
