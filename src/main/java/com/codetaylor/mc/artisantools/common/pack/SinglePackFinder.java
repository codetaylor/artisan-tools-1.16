package com.codetaylor.mc.artisantools.common.pack;

import net.minecraft.resources.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.FileFilter;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SinglePackFinder
    implements IPackFinder {

  private final File folder;
  private final IPackNameDecorator decorator;
  private final String packName;

  public SinglePackFinder(File folder, IPackNameDecorator decorator, String packName) {

    this.folder = folder;
    this.decorator = decorator;
    this.packName = packName;
  }

  @ParametersAreNonnullByDefault
  @Override
  public void findPacks(Consumer<ResourcePackInfo> infoConsumer, ResourcePackInfo.IFactory infoFactory) {

    FileFilter fileFilter = (file) -> {
      String name = file.getName();
      boolean isZip = file.isFile() && name.equals(this.packName + ".zip");
      boolean isFolder = file.isDirectory() && (new File(file, "pack.mcmeta")).isFile() && name.equals(this.packName);
      return isZip || isFolder;
    };

    File[] files = this.folder.listFiles(fileFilter);

    if (files != null) {

      for (File file : files) {
        String name = "file/" + file.getName();
        ResourcePackInfo resourcepackinfo = ResourcePackInfo.createResourcePack(
            name,
            false,
            this.makePackSupplier(file),
            infoFactory,
            ResourcePackInfo.Priority.TOP, this.decorator
        );

        if (resourcepackinfo != null) {
          resourcepackinfo.alwaysEnabled = true;
          infoConsumer.accept(resourcepackinfo);
        }
      }

    }
  }

  private Supplier<IResourcePack> makePackSupplier(File fileIn) {

    return fileIn.isDirectory() ? () -> new FolderPack(fileIn) : () -> new FilePack(fileIn);
  }
}
