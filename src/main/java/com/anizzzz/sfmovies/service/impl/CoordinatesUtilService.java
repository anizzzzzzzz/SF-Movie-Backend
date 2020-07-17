package com.anizzzz.sfmovies.service.impl;

import com.anizzzz.sfmovies.dto.CoordinateData;
import com.anizzzz.sfmovies.service.ICoordinatesUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class CoordinatesUtilService implements ICoordinatesUtilService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${openstreetmap.url}")
    private String openStreetUrl;

    private final RestTemplate restTemplate;

    public CoordinatesUtilService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /*@Override
    public List<CoordinateData> getCoordinates(String streetname) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openStreetUrl)
                .queryParam("format", "json")
                .queryParam("addressdetails", 1)
                .queryParam("state", "San Francisco")
                .queryParam("street", streetname)
                .queryParam("limit",1);

        System.out.println(builder.toUriString());
        String result = new RestTemplate().getForObject(builder.toUriString(), String.class);

        System.out.println(result);
        return null;
    }*/

    @Override
    public List<CoordinateData> getCoordinates(String streetname) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openStreetUrl)
                .queryParam("format", "json")
                .queryParam("q", streetname + " San Francisco");
//                .queryParam("addressdetails", 1)
//                .queryParam("state", "San Francisco")
//                .queryParam("street", streetname)
//                .queryParam("limit",1);

        ResponseEntity<List<CoordinateData>> coordinateReponse = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<CoordinateData>>() {
                });
        if(coordinateReponse.getStatusCode() == HttpStatus.OK){
            Optional<List<CoordinateData>> optionalResponse = Optional.ofNullable(coordinateReponse.getBody());
            System.out.println(coordinateReponse.toString());
            return optionalResponse.orElse(null);
        }
        else{logger.error("Cannot Fetch Coordinates");}
        return null;
    }
}
