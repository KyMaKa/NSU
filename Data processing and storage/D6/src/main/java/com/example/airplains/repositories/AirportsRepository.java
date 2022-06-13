package com.example.airplains.repositories;

import com.example.airplains.entities.Airport;
import com.example.airplains.entities.helpers.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.repository.query.Param;

public interface AirportsRepository extends JpaRepository<Airport, String>,
    JpaSpecificationExecutor<Airport> {
    @Query(
        "SELECT distinct city from Airport")
    List<City> findAllCities();

    @Query(nativeQuery = true
    , value = "select * from airports_data where city ->> 'ru' = :city or city ->> 'en' = :city"
    )
    List<Airport> findAllByCity(@Param("city") String city);

    Airport getFirstByAirportCode(String code);

    @Query(nativeQuery = true
        , value = "select * from airports_data where city ->> 'ru' = :city or city ->> 'en' = :city"
    )
    Airport findFirstByCity(String city);
}
