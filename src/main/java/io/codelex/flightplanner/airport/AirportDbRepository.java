package io.codelex.flightplanner.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AirportDbRepository extends JpaRepository<Airport, Long> {
    List<Airport> findByAirportContainingIgnoreCaseOrCityContainingIgnoreCaseOrCountryContainingIgnoreCase(String airport, String city, String country);
}