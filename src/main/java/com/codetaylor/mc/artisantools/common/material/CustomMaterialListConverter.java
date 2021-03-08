package com.codetaylor.mc.artisantools.common.material;

import com.codetaylor.mc.artisantools.api.CustomToolMaterial;
import com.codetaylor.mc.artisantools.api.ICustomToolMaterial;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CustomMaterialListConverter {

  private final CustomMaterialValidator validator;
  private final CustomMaterialConverter converter;

  public CustomMaterialListConverter(
      CustomMaterialValidator validator,
      CustomMaterialConverter converter
  ) {

    this.validator = validator;
    this.converter = converter;
  }

  public List<CustomToolMaterial> convert(DataCustomMaterialList data, Logger logger) {

    List<CustomToolMaterial> result = new ArrayList<>();
    List<DataCustomMaterial> list = data.getList();

    for (ICustomToolMaterial dataCustomMaterial : list) {

      try {
        this.validator.validate(dataCustomMaterial);

      } catch (CustomMaterialValidationException e) {
        logger.error("", e);
        continue;
      }

      result.add(this.converter.convert(dataCustomMaterial));
    }

    return result;
  }
}
