package io.codelex.flightplanner.configuration;

import io.codelex.flightplanner.airport.AirportDbRepository;
import io.codelex.flightplanner.airport.AirportInMemRepository;
import io.codelex.flightplanner.flight.FlightDbRepository;
import io.codelex.flightplanner.flight.FlightInMemRepository;
import io.codelex.flightplanner.service.DbService;
import io.codelex.flightplanner.service.InMemService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModeConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "myapp", name = "db-storage-mode", havingValue = "database")
    public DbService createDbService(FlightDbRepository flightDbRepository, AirportDbRepository airportDbRepository) {
        return new DbService(flightDbRepository, airportDbRepository);
    }

    @Bean
    @ConditionalOnProperty(prefix = "myapp", name = "in-memory-storage-mode", havingValue = "in-memory")
    public InMemService createInMemService(FlightInMemRepository flightInMemRepository, AirportInMemRepository airportInMemRepository) {
        return new InMemService(flightInMemRepository, airportInMemRepository);
    }
}