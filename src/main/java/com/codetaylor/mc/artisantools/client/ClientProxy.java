package com.codetaylor.mc.artisantools.client;

import com.codetaylor.mc.artisantools.ArtisanToolsMod;
import com.codetaylor.mc.artisantools.client.event.ItemColorEventHandler;
import com.codetaylor.mc.artisantools.common.CommonProxy;
import com.codetaylor.mc.artisantools.common.pack.injector.ResourcePackFinderInjector;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;

public class ClientProxy
    extends CommonProxy {

  @Override
  public void registerModEventHandlers(IEventBus eventBus) {

    super.registerModEventHandlers(eventBus);

    eventBus.register(new ItemColorEventHandler(
        this.registeredToolList
    ));

    DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ResourcePackFinderInjector.inject(ArtisanToolsMod.GENERATED_PATH));
  }
}
