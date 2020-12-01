# Materials

Artisan Tools allows you to add and remove tool materials from the mod through the use of `.json` files in the config folder.

The tool material definitions are located here:

```
config/
  artisantools/
    tool.materials.custom.json
    tool.materials.generated.json
```

The `tool.materials.generated.json` file is regenerated every time the game is loaded. This will always contain the default values that are shipped with the mod. It is meant for reference only and shouldn't be modified, as it will be overwritten anyway.

The `tool.materials.custom.json` is the file you are interested in. This file can be modified to add or remove materials from the mod. Tools are automatically generated from the materials in this file.

## Json Format

```json
{
  "name": "wood",
  "harvestLevel": 0,
  "maxUses": 59,
  "efficiency": 2.0,
  "damage": 0.0,
  "enchantability": 15,
  "color": "73523e",
  "shiny": false,
  "ingredient": "tag#minecraft:planks",
  "langKey": "material.artisantools.wood"
}
```

All the `.json` keys should be fairly self-explanatory. Some that might not be so obvious are explained below.

### shiny

This flag denotes if the highlight should be used in the item's model.

### ingredient

This is the primary ingredient used in a tool's recipe.

The value of this key must be prefixed with either `item#` or `tag#` to denote the type of ingredient.

### langKey

The lang keys point to entries in a `en_us.lang` (or other language) lang file located in a mod's or resource pack's `assets` folder.

For example:

```
"langKey": "material.artisantools.stone",
```

Refers to the entry in the Artisan Tools `assets/artisantools/lang` files:

```
"material.artisantools.stone": "Elementium"
```

You can supply your own lang files using a resource pack.

These lang keys are used when displaying a material's name in-game, like `Artisan's Elementium Hammer` or `Artisan's Iron Athame`.

If the material you're adding comes from a mod in the modpack, it is possible that the mod would already have a lang key for the material name that you could use. You could open up the mod's .jar file and dive into the `assets/[modname]/lang` folder and inspect those files for a key.

As a last resort, you could simply use the name of the material as the lang key and that would be displayed. For example, using `Sand` as the lang key for a sand material would display as `Artisan's Sand Hammer` or `Artisan's Sand Athame`. It works, but won't allow for translations.