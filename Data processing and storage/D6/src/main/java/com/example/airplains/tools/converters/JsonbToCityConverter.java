package com.example.airplains.tools.converters;

import com.example.airplains.entities.helpers.City;
import com.google.gson.Gson;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JsonbToCityConverter implements AttributeConverter<City, String> {
  private final static Gson GSON = new Gson();

  @Override
  public String convertToDatabaseColumn(City city) {
    return GSON.toJson(city);
  }

  @Override
  public City convertToEntityAttribute(String dbData) {
    return GSON.fromJson(dbData, City.class);
  }

}
