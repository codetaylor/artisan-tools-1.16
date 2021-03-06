package com.codetaylor.mc.artisantools.common.pack.injector;

import com.codetaylor.mc.artisantools.common.pack.SinglePackFinder;
import net.minecraft.resources.ResourcePackList;

import java.nio.file.Path;

public class DataPackFinderInjector {

  public static void inject(ResourcePackList list, Path path) {

    list.addPackFinder(new SinglePackFinder(
        path.toFile(),
        decorator -> decorator,
        "datapack"
    ));
  }
}