package com.anizzzz.sfmovies.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application-test.properties")
@NoArgsConstructor
@Getter
@Setter
@Configuration
public class GeocodeCredential {
    @Value("${geocode.here.developer.url}")
    private String geocodeUrl;
    @Value("${geocode.here.developer.apikey}")
    private String apiKey;
}
