package com.example.airplains.entities;

import com.example.airplains.entities.helpers.City;
import com.example.airplains.entities.helpers.Name;
import com.example.airplains.tools.converters.JsonbToCityConverter;
import com.example.airplains.tools.converters.JsonbToNameConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "airports_data")
public class Airport {
    @Id
    @Column(name = "airport_code")
    private String airportCode;

    @Convert(converter = JsonbToNameConverter.class)
    @Column(name = "airport_name", columnDefinition = "jsonb")
    private Name name;

    @Convert(converter = JsonbToCityConverter.class)
    @Column(name = "city", columnDefinition = "jsonb")
    private City city;

    @Column(name = "coordinates")
    private String coordinates;

    @Column(name = "timezone")
    private String timezone;
}
