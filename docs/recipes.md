# Recipes

Artisan Tools allows you to modify the tool type recipe templates through the use of `.json` files in the config folder.

The template definitions are located here:

```
config/
  artisantools/
    tool.recipes.custom.json
    tool.recipes.generated.json
```

The `tool.recipes.generated.json` file is regenerated every time the game is loaded. This will always contain the default values that are shipped with the mod. It is meant for reference only and shouldn't be modified, as it will be overwritten anyway.

The `tool.recipes.custom.json` is the file you are interested in. This file can be modified. Recipes are automatically generated from the data in this file.

## Json Format

```json
{
  "framing_hammer": {
      "pattern": [
        " ..",
        " /x",
        "/  "
      ],
      "key": [
        "x",
        "tag#forge:string",
        "/",
        "item#minecraft:stick"
      ]
    }
}
```

The `.` character is a reserved placeholder character for the tool's material item. 