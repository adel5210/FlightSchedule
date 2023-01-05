package com.adel.flightschedule.aviaitonstack.service;

public interface AviationStackService {

    Object getFlights(final String additionalParams);
    Object getCountries(final String additionalParams);
    Object getCities(final String additionalParams);

}
