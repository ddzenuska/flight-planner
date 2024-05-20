package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.exceptions.DuplicateFlightException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class FlightRepository {

    private final List<Flight> flightsList = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    protected final AtomicLong flightIdGenerator = new AtomicLong();

    public Flight addFlight(Flight flight) {
        lock.lock();
        try {
            if (flightExists(flight)) {
                throw new DuplicateFlightException("Flight already exists");
            }
            flight.setId(flightIdGenerator.incrementAndGet());
            flightsList.add(flight);
            return flight;
        } finally {
            lock.unlock();
        }
    }

    public void deleteFlight(Long id) {
        lock.lock();
        try {
            flightsList.removeIf(flight -> flight.getId().equals(id));
        } finally {
            lock.unlock();
        }
    }

    public Flight findFlightById(Long id) {
        lock.lock();
        try {
            return flightsList.stream()
                    .filter(flight -> flight.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        } finally {
            lock.unlock();
        }
    }

    public void clearFlights() {
        lock.lock();
        try {
            flightsList.clear();
        } finally {
            lock.unlock();
        }
    }

    public boolean flightExists(Flight flight) {
        return flightsList.stream()
                .anyMatch(f -> f.equals(flight));
    }

    public List<Flight> searchFlights(SearchFlightsRequest request) {
        lock.lock();
        try {
            return flightsList.stream()
                    .filter(flight -> flight.getFrom().getAirport().equalsIgnoreCase(request.getFrom()) &&
                            flight.getTo().getAirport().equalsIgnoreCase(request.getTo()) &&
                            flight.getDepartureTime().toLocalDate().equals(request.getDepartureDate()))
                    .collect(Collectors.toList());
        } finally {
            lock.unlock();
        }
    }
}
