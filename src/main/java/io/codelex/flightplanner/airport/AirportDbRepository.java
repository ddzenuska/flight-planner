package io.codelex.flightplanner.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AirportDbRepository extends JpaRepository<Airport, Long> {
    @Query("SELECT a FROM Airport a WHERE LOWER(a.airport) LIKE LOWER('%' || :search || '%') " +
            "OR LOWER(a.city) LIKE LOWER('%' || :search || '%') " +
            "OR LOWER(a.country) LIKE LOWER('%' || :search || '%')")
    List<Airport> searchAirports(@Param("search") String search);
}