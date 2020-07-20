package com.anizzzz.sfmovies.service.impl;

import com.anizzzz.sfmovies.dto.CoordinateItems;
import com.anizzzz.sfmovies.dto.CoordinatePosition;
import com.anizzzz.sfmovies.service.ICoordinatesUtilService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class CoordinatesUtilService implements ICoordinatesUtilService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${geocode.here.developer.url}")
    private String geocodeUrl;
    @Value("${geocode.here.developer.apikey}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public CoordinatesUtilService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public CoordinatePosition getCoordinates(String streetname) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> request = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(geocodeUrl)
                .queryParam("q", streetname + " San Francisco United States")
                .queryParam("limit", 1)
                .queryParam("apiKey", apiKey);

        ResponseEntity<String> result = restTemplate.exchange(builder.build().encode().toUri(),
                HttpMethod.GET,request, String.class);

        if(result.getStatusCode() == HttpStatus.OK){
            Optional<String> optionalResponse = Optional.ofNullable(result.getBody());
            if(optionalResponse.isPresent()){
                try {
                    CoordinateItems items =  objectMapper.readValue(optionalResponse.get(), new TypeReference<CoordinateItems>(){});
                    if(!items.getItems().isEmpty()){
                        return items.getItems().get(0);
                    }
                    return null;
                } catch (JsonProcessingException e) {
                    logger.error("Error while fetching the coordinates");
                }
            }
        }
        return null;
    }

}