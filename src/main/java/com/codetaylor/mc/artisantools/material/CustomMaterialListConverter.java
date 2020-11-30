package com.codetaylor.mc.artisantools.material;

import com.codetaylor.mc.artisantools.api.tool.CustomMaterial;
import com.codetaylor.mc.artisantools.api.tool.ICustomToolMaterial;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CustomMaterialListConverter {

  private final CustomMaterialValidator customMaterialValidator;
  private final CustomMaterialConverter customMaterialConverter;

  public CustomMaterialListConverter(
      CustomMaterialValidator customMaterialValidator,
      CustomMaterialConverter customMaterialConverter
  ) {

    this.customMaterialValidator = customMaterialValidator;
    this.customMaterialConverter = customMaterialConverter;
  }

  public List<CustomMaterial> convert(DataCustomMaterialList data, Logger logger) {

    List<CustomMaterial> result = new ArrayList<>();
    List<DataCustomMaterial> list = data.getList();

    for (ICustomToolMaterial dataCustomMaterial : list) {

      try {
        this.customMaterialValidator.validate(dataCustomMaterial);

      } catch (CustomMaterialValidationException e) {
        logger.error("", e);
        continue;
      }

      result.add(this.customMaterialConverter.convert(dataCustomMaterial));
    }

    return result;
  }
}
