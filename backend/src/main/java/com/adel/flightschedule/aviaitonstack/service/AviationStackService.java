package com.adel.flightschedule.aviaitonstack.service;

public interface AviationStackService {

    Object getFlights(final String additionalParams);
    Object getCountries(final String additionalParams);
    Object getCities(final String additionalParams);
    Object getRoutes(final String additionalParams);
    Object getAirports(final String additionalParams);
    Object getAirlines(final String additionalParams);

}
