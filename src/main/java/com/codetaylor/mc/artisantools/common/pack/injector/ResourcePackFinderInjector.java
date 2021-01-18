package com.codetaylor.mc.artisantools.common.pack.injector;

import com.codetaylor.mc.artisantools.common.pack.SinglePackFinder;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.DistExecutor;

import java.nio.file.Path;

public class ResourcePackFinderInjector {

  public static DistExecutor.SafeRunnable inject(Path dataPath) {

    //noinspection Convert2Lambda
    return new DistExecutor.SafeRunnable() {

      @Override
      public void run() {

        Minecraft.getInstance().getResourcePackList().addPackFinder(new SinglePackFinder(
            dataPath.toFile(),
            nameComp -> nameComp,
            "resourcepack"
        ));
      }
    };
  }
}
