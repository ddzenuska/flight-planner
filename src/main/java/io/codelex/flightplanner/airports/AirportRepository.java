package io.codelex.flightplanner.airports;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AirportRepository {
    private final List<Airports> airportsList = new ArrayList<>();

    public void addAirport(Airports airport) {
        if (!airportsList.contains(airport)) {
            airportsList.add(airport);
        }
    }

    public List<Airports> searchAirports(String phrase) {
        String normalizedPhrase = phrase.trim().toLowerCase();
        List<Airports> list = new ArrayList<>();
        for (Airports airport : airportsList) {
            if (matchesPhrase(airport, normalizedPhrase)) {
                list.add(airport);
            }
        }
        return list;
    }

    private boolean matchesPhrase(Airports airport, String phrase) {
        return airport.getAirport().toLowerCase().contains(phrase) ||
                airport.getCity().toLowerCase().contains(phrase) ||
                airport.getCountry().toLowerCase().contains(phrase);
    }
}
