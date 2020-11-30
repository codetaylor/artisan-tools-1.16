package com.codetaylor.mc.artisantools.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class ParseResult {

  public static final ParseResult NULL = new ParseResult();

  private String domain;
  private String path;
  private int quantity;

  public ParseResult() {
    //
  }

  public ParseResult(String domain, String path) {

    this(domain, path, 1);
  }

  public ParseResult(String domain, String path, int quantity) {

    this.domain = domain;
    this.path = path;
    this.quantity = quantity;
  }

  public void setDomain(String domain) {

    this.domain = domain;
  }

  public void setPath(String path) {

    this.path = path;
  }

  public void setQuantity(int quantity) {

    this.quantity = quantity;
  }

  public String getDomain() {

    return domain;
  }

  public String getPath() {

    return path;
  }

  public int getQuantity() {

    return quantity;
  }

  @Override
  public String toString() {

    if (this == ParseResult.NULL) {
      return "null";
    }

    return this.domain + ":" + this.path + (("ore".equals(this.domain)) ? "" : ((this.quantity != 1) ? " * " + this.quantity : ""));
  }

  public boolean matches(ItemStack itemStack, boolean ignoreQuantity) {

    Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.getDomain(), this.getPath()));

    if (itemStack.getItem() != item) {
      return false;
    }

    return (ignoreQuantity) || (this.getQuantity() == itemStack.getCount());
  }
}
