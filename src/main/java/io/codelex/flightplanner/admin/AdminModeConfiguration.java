package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.airport.AirportDbRepository;
import io.codelex.flightplanner.airport.AirportInMemRepository;
import io.codelex.flightplanner.flight.FlightDbRepository;
import io.codelex.flightplanner.flight.FlightInMemRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminModeConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "myapp", name = "admin-storage-mode", havingValue = "database")
    public AdminService createAdminDbService(AirportDbRepository airportDbRepository,
                                             FlightDbRepository flightDbRepository) {
        return new AdminDbService(flightDbRepository, airportDbRepository);
    }

    @Bean
    @ConditionalOnProperty(prefix = "myapp", name = "admin-storage-mode", havingValue = "in-memory")
    public AdminService createAdminInMemService(AirportInMemRepository airportInMemRepository,
                                                FlightInMemRepository flightInMemRepository) {
        return new AdminInMemService(flightInMemRepository, airportInMemRepository);
    }
}
