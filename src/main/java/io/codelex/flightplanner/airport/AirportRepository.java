package io.codelex.flightplanner.airport;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AirportRepository {
    private final List<Airport> airportsList = new ArrayList<>();

    public void addAirport(Airport airport) {
        if (!airportsList.contains(airport)) {
            airportsList.add(airport);
        }
    }

    public List<Airport> searchAirports(String phrase) {
        String normalizedPhrase = phrase.trim().toLowerCase();
        List<Airport> list = new ArrayList<>();
        for (Airport airport : airportsList) {
            if (matchesPhrase(airport, normalizedPhrase)) {
                list.add(airport);
            }
        }
        return list;
    }

    private boolean matchesPhrase(Airport airport, String phrase) {
        return airport.getAirport().toLowerCase().contains(phrase) ||
                airport.getCity().toLowerCase().contains(phrase) ||
                airport.getCountry().toLowerCase().contains(phrase);
    }
}