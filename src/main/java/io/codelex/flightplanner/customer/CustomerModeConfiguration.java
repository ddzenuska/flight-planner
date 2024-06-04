package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.airport.AirportDbRepository;
import io.codelex.flightplanner.airport.AirportInMemRepository;
import io.codelex.flightplanner.flight.FlightDbRepository;
import io.codelex.flightplanner.flight.FlightInMemRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerModeConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "myapp", name = "customer-storage-mode", havingValue = "database")
    public CustomerService createCustomerDbService(AirportDbRepository airportDbRepository,
                                                        FlightDbRepository flightDbRepository) {
        return new CustomerDbService(flightDbRepository, airportDbRepository);
    }

    @Bean
    @ConditionalOnProperty(prefix = "myapp", name = "customer-storage-mode", havingValue = "in-memory")
    public CustomerService createCustomerInMemService(AirportInMemRepository airportInMemRepository,
                                                           FlightInMemRepository flightInMemRepository) {
        return new CustomerInMemService(flightInMemRepository, airportInMemRepository);
    }
}