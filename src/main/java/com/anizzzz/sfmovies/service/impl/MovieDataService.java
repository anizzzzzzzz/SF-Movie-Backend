package com.anizzzz.sfmovies.service.impl;

import com.anizzzz.sfmovies.dto.CoordinatePosition;
import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.model.MovieData;
import com.anizzzz.sfmovies.repository.MovieDataRepository;
import com.anizzzz.sfmovies.service.ICoordinatesUtilService;
import com.anizzzz.sfmovies.service.IMovieDataService;
import com.anizzzz.sfmovies.spec.MovieDataSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieDataService implements IMovieDataService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${sf-movie.data-url}")
    private String sfMovieUrl;

    private final MovieDataRepository movieRepository;
    private final RestTemplate restTemplate;
    private final ICoordinatesUtilService coordinateService;
    private final MovieDataSpec movieDataSpec;

    @Autowired
    public MovieDataService(MovieDataRepository movieRepository, RestTemplate restTemplate, ICoordinatesUtilService coordinateService, MovieDataSpec movieDataSpec) {
        this.movieRepository = movieRepository;
        this.restTemplate = restTemplate;
        this.coordinateService = coordinateService;
        this.movieDataSpec = movieDataSpec;
    }

    @Override
    public boolean loadSFMovieData() {
        ResponseEntity<Set<MovieData>> movieDataResponse = restTemplate.exchange(sfMovieUrl,
                            HttpMethod.GET, null, new ParameterizedTypeReference<Set<MovieData>>() {});

        if(movieDataResponse.getStatusCode() == HttpStatus.OK){
            Optional<Set<MovieData>> optionalResponse = Optional.ofNullable(movieDataResponse.getBody());
            if(optionalResponse.isPresent()){
                optionalResponse.get().parallelStream().forEach(movieData -> {
                    if(!movieData.getLocations().isEmpty()){
                        CoordinatePosition coordinatePosition = coordinateService.getCoordinates(movieData.getLocations());
                        if(coordinatePosition != null){
                            movieData.setLongitude(coordinatePosition.getPosition().getLng());
                            movieData.setLatitude(coordinatePosition.getPosition().getLat());
                        }
                    }
                    movieRepository.save(movieData.trimAll());
                });
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isDataInitialized() {
        return movieRepository.count() > 0;
    }

    @Override
    public List<MovieData> autocompleteData(DataQuery queryDto) {
        return  movieRepository.findAll(movieDataSpec.findAllByFilters(queryDto));
    }


}
