package com.adel.flightschedule.aviaitonstack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
@AllArgsConstructor
public enum AviationStackPath {

    FLIGHTS("flights", HttpMethod.GET),
    ROUTES("routes", HttpMethod.GET),
    AIRPORTS("airports", HttpMethod.GET),
    AIRLINES("airlines", HttpMethod.GET),
    CITIES("cities", HttpMethod.GET),
    COUNTRIES("countries", HttpMethod.GET),
    ;

    private final String path;
    private final HttpMethod requestType;

}
