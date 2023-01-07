package com.adel.flightschedule.aviaitonstack.service;

import com.adel.flightschedule.aviaitonstack.config.AviationStackConfig;
import com.adel.flightschedule.aviaitonstack.model.AviationStackPath;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AviationStackServiceImpl implements AviationStackService {

    private final AviationStackConfig aviationStackConfig;

    private String rootUrl;
    private HttpHeaders defaultHeaders;
    private String accessKey;
    private WebClient webClient;

    @PostConstruct
    public void init() {
        this.rootUrl = aviationStackConfig.getUrl();
        this.defaultHeaders = new HttpHeaders();
        this.defaultHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        this.accessKey = ("access_key=" + aviationStackConfig.getApikey());
        this.webClient = WebClient.builder()
//                .baseUrl(this.rootUrl)
                .build();
    }

    private String fullPath(final String path) {
        return rootUrl + "/" + path;
    }

    private String fullUrl(final String path, final String... additionalPath) {
        final StringBuilder urlBuilder = new StringBuilder(fullPath(path));

        if (null != additionalPath && additionalPath.length > 0) {
            urlBuilder
                    .append("?")
                    .append(Arrays.stream(additionalPath)
                            .filter(f -> !Objects.isNull(f))
                            .collect(Collectors.joining("&"))
                    )
            ;
        }

        return urlBuilder.toString();
    }

    @Override
    public Object getFlights(String additionalParams) {
        final String url = fullUrl(AviationStackPath.FLIGHTS.getPath(), this.accessKey, additionalParams);
        return processGetRequest(url);
    }

    @Override
    public Object getCountries(String additionalParams) {
        final String url = fullUrl(AviationStackPath.COUNTRIES.getPath(), this.accessKey, additionalParams);
        return processGetRequest(url);
    }

    @Override
    public Object getCities(String additionalParams) {
        final String url = fullUrl(AviationStackPath.CITIES.getPath(), this.accessKey, additionalParams);
        return processGetRequest(url);
    }

    @Override
    public Object getRoutes(String additionalParams) {
        final String url = fullUrl(AviationStackPath.ROUTES.getPath(), this.accessKey, additionalParams);
        return processGetRequest(url);
    }

    @Override
    public Object getAirports(String additionalParams) {
        final String url = fullUrl(AviationStackPath.AIRPORTS.getPath(), this.accessKey, additionalParams);
        return processGetRequest(url);
    }

    @Override
    public Object getAirlines(String additionalParams) {
        final String url = fullUrl(AviationStackPath.AIRLINES.getPath(), this.accessKey, additionalParams);
        return processGetRequest(url);
    }

    private Object processGetRequest(final String url){
        return this.webClient.get()
                .uri(url)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Object.class))
                .block(Duration.ofMinutes(3));
    }

}
