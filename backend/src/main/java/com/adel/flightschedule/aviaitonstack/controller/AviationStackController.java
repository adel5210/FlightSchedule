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
    public ResponseEntity<Object> findFlights(final String additionalPath){
        log.info("Fetching flights");
        return ResponseEntity.ok(aviationStackService.getFlights(additionalPath));
    }

}
