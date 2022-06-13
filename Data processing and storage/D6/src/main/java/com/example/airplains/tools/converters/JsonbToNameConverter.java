package com.example.airplains.tools.converters;

import com.example.airplains.entities.helpers.Name;
import com.google.gson.Gson;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JsonbToNameConverter implements AttributeConverter<Name, String> {
  private final static Gson GSON = new Gson();

  @Override
  public String convertToDatabaseColumn(Name name) {
    return GSON.toJson(name);
  }

  @Override
  public Name convertToEntityAttribute(String dbData) {
    return GSON.fromJson(dbData, Name.class);
  }
}
