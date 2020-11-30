package com.codetaylor.mc.artisantools.lib;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RecipeItemParser {

  public static final RecipeItemParser INSTANCE = new RecipeItemParser();

  @Nonnull
  public ParseResult parse(@Nullable String data) throws MalformedRecipeItemException {

    if (data == null || "null".equals(data.trim())) {
      return ParseResult.NULL;
    }

    ParseResult result = new ParseResult();
    result.setQuantity(1);

    String[] split = data.split(":");

    if (split.length != 2) {
      throw new MalformedRecipeItemException(String.format(
          "[PARSE] Too many segments in [%s], must be two segments: <domain:path>",
          data
      ));
    }

    result.setDomain(split[0].trim());

    String[] pathSplit = split[1].split("\\*");

    result.setPath(pathSplit[0].trim());

    if (pathSplit.length > 1) {

      try {
        result.setQuantity(Integer.parseInt(pathSplit[1].trim()));

      } catch (NumberFormatException e) {
        throw new MalformedRecipeItemException(String.format("[PARSE] Expected integer, got [%s]", split[1].trim()));
      }
    }

    return result;
  }
}