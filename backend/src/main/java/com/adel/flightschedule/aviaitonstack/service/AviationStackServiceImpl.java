package com.adel.flightschedule.aviaitonstack.service;

import com.adel.flightschedule.aviaitonstack.config.AviationStackConfig;
import com.adel.flightschedule.aviaitonstack.model.AviationStackPath;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AviationStackServiceImpl implements AviationStackService{

    private final RestTemplate restTemplate;
    private final AviationStackConfig aviationStackConfig;

    private String rootUrl;
    private HttpHeaders defaultHeaders;
    private String accessKey;

    @PostConstruct
    public void init(){
        this.rootUrl = aviationStackConfig.getUrl();
        this.defaultHeaders = new HttpHeaders();
        this.defaultHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        this.accessKey = ("access_key="+aviationStackConfig.getApikey());
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
                            .filter(f-> !Objects.isNull(f))
                            .collect(Collectors.joining("&"))
                    )
            ;
        }

        return urlBuilder.toString();
    }

    @Override
    public Object getFlights(String additionalParams) {
        final HttpEntity<Object> entity = new HttpEntity<>(null, this.defaultHeaders);
        final String url = fullUrl(AviationStackPath.FLIGHTS.getPath(),this.accessKey, additionalParams);
        final ResponseEntity<Object> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);

        return responseEntity.getBody();
    }

}