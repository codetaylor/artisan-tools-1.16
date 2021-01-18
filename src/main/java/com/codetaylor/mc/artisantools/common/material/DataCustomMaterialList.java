package com.codetaylor.mc.artisantools.common.material;

import java.util.List;

public class DataCustomMaterialList {

  private String[] __comments = {
      "WARNING: All changes should be made to the file with the name `custom`",
      "in the title. Changes made to the `generated` file will be overwritten."
  };

  private List<DataCustomMaterial> list;

  public DataCustomMaterialList(List<DataCustomMaterial> list) {

    this.list = list;
  }

  public List<DataCustomMaterial> getList() {

    return this.list;
  }
}
