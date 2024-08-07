package io.codelex.flightplanner.flight;

import io.codelex.flightplanner.airport.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightDbRepository extends JpaRepository<Flight, Long> {
    boolean existsByFromAndToAndDepartureTimeAndArrivalTime(Airport from, Airport to, LocalDateTime departureTime, LocalDateTime arrivalTime);

    List<Flight> findByFromAirportAndToAirportAndDepartureTimeBetween(String from, String to, LocalDateTime start, LocalDateTime end);
}