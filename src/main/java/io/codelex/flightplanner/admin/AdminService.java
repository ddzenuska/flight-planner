package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.flight.Flight;

public interface AdminService {
    Flight addFlight(Flight request);

    Flight fetchFlight(long id);

    void deleteFlight(long id);
}