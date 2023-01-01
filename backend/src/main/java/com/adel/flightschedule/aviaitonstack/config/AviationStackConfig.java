package com.adel.flightschedule.aviaitonstack.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "aviationstack")
public class AviationStackConfig {

    private String apikey;
    private String url;

}
