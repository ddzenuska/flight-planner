package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.flight.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-api/flights")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public Flight addFlight(@RequestBody Flight request) {
        return adminService.addFlight(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Flight fetchFlight(@PathVariable long id) {
        return adminService.fetchFlight(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable long id) {
        adminService.deleteFlight(id);
    }
}