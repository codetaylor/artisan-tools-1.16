package com.codetaylor.mc.artisantools;

import net.minecraftforge.eventbus.api.IEventBus;

public interface IProxy {

  void initialize();

  void registerModEventHandlers(IEventBus eventBus);

  void registerForgeEventHandlers(IEventBus eventBus);

}
