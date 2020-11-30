package com.codetaylor.mc.artisantools.event;

import com.codetaylor.mc.artisantools.api.tool.ItemWorktableToolBase;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class ItemColorEventHandler {

  private final List<ItemWorktableToolBase> registeredToolList;

  public ItemColorEventHandler(List<ItemWorktableToolBase> registeredToolList) {

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
            ? ((ItemWorktableToolBase) itemStack.getItem()).getMaterial().getColor()
            : 0xFFFFFF,
        this.registeredToolList.stream()
            .map(itemWorktableToolBase -> (IItemProvider) () -> itemWorktableToolBase)
            .toArray(IItemProvider[]::new)
    );
  }
}
