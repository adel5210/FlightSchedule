package com.adel.flightschedule.aviaitonstack.controller;

import com.adel.flightschedule.aviaitonstack.service.AviationStackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/aviation")
@Slf4j
public class AviationStackController {

    private final AviationStackService aviationStackService;

    @GetMapping("/flights")
    public ResponseEntity<Object> findFlights(final String additionalPath) {
        log.info("Fetching flights "+additionalPath);
        return ResponseEntity.ok(aviationStackService.getFlights(additionalPath));
    }

    @GetMapping("/countries")
    public ResponseEntity<Object> findCountries(final String additionalPath) {
        log.info("Find countries "+additionalPath);
        return ResponseEntity.ok(aviationStackService.getCountries(additionalPath));
    }

    @GetMapping("/cities")
    public ResponseEntity<Object> findCities(final String additionalPath) {
        log.info("Find cities "+additionalPath);
        return ResponseEntity.ok(aviationStackService.getCities(additionalPath));
    }

    @GetMapping("/routes")
    public ResponseEntity<Object> findRoutes(final String additionalPath) {
        log.info("Find routes "+additionalPath);
        return ResponseEntity.ok(aviationStackService.getRoutes(additionalPath));
    }

    @GetMapping("/airports")
    public ResponseEntity<Object> findAirports(final String additionalPath) {
        log.info("Find airports "+additionalPath);
        return ResponseEntity.ok(aviationStackService.getAirports(additionalPath));
    }
}



