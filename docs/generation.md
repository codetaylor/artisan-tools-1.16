# Materials

During each game load, Artisan Tools will generate and load a data pack and a resource pack in the following location:

```
config/
  artisantools/
    generated/
      datapack/
      resourcepack/
```

You can edit or delete these files as you see fit. The game will look in this location for a `datapack/`, `datapack.zip`, `resourcepack`, or `resourcepack.zip`.

The packs will be deleted and regenerated on each game load. Deletion and generation can be disabled in the mod's config file.

!!! note
    It is recommended that you disable generation before distributing your modpack.

## Data Pack

The generated data pack contains recipes for each tool as well as tags grouping the tools by type and material.

For example, the tag `artisantools:material/stone` will contain all the tools generated for the `stone` material and the tag `artisantools:type/hammer` will contain all the tools generated for the `hammer` type.

## Resource Pack

The generated resource pack contains all the model files for each tool.

## Compression

If enabled in the mod's config file, the generated packs will be compressed into zip files after they are generated.

!!! note
    It is recommended that you enable compression before distributing your modpack.