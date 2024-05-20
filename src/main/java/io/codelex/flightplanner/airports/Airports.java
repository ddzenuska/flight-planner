package io.codelex.flightplanner.airports;

import java.util.Objects;

public class Airports {
    private String country;
    private String city;
    private String airport;

    public Airports() {
    }

    public Airports(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airports airports = (Airports) o;
        return Objects.equals(normalize(country), normalize(airports.country)) &&
                Objects.equals(normalize(city), normalize(airports.city)) &&
                Objects.equals(normalize(airport), normalize(airports.airport));
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalize(country), normalize(city), normalize(airport));
    }

    private String normalize(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }
}