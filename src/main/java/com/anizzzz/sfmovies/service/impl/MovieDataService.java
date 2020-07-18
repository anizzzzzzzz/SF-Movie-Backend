package com.anizzzz.sfmovies.service.impl;

import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.model.MovieData;
import com.anizzzz.sfmovies.repository.MovieDataRepository;
import com.anizzzz.sfmovies.service.IMovieDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
public class MovieDataService implements IMovieDataService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${sf-movie.data-url}")
    private String sfMovieUrl;

    private final MovieDataRepository movieRepository;
    private final RestTemplate restTemplate;

    public MovieDataService(MovieDataRepository movieRepository, RestTemplate restTemplate) {
        this.movieRepository = movieRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean initializeDataToDB() {
        ResponseEntity<Set<MovieData>> movieDataResponse = restTemplate.exchange(sfMovieUrl,
                            HttpMethod.GET, null, new ParameterizedTypeReference<Set<MovieData>>() {
                        });

        Optional<Set<MovieData>> optionalResponse = Optional.ofNullable(movieDataResponse.getBody());
        if(optionalResponse.isPresent()){
            movieRepository.saveAll(optionalResponse.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean isDataInitialized() {
        return movieRepository.count() > 0;
    }

    @Override
    public List<MovieData> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<MovieData> filterData(DataQuery queryDto) {
        return  movieRepository.findAll((Specification<MovieData>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(!queryDto.getTitle().isEmpty()){
                predicates.add(
                        criteriaBuilder.and(criteriaBuilder
                                .equal(criteriaBuilder.lower(root.get("title")), queryDto.getTitle().toLowerCase())));
            }

            if(!queryDto.getLocations().isEmpty()){
                predicates.add(
                        criteriaBuilder.and(criteriaBuilder.
                                equal(criteriaBuilder.lower(root.get("locations")), queryDto.getLocations().toLowerCase())));
            }
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    public List<MovieData> autocompleteData(DataQuery queryDto) {
        return  movieRepository.findAll((Specification<MovieData>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(!queryDto.getTitle().isEmpty()){
                predicates.add(
                        criteriaBuilder.and(criteriaBuilder
                                .like(criteriaBuilder.lower(root.get("title")),
                                        "%" + queryDto.getTitle().toLowerCase() + "%")));
            }

            if(!queryDto.getLocations().isEmpty()){
                predicates.add(
                        criteriaBuilder.and(criteriaBuilder
                                .like(criteriaBuilder.lower(root.get("locations")),
                                        "%" + queryDto.getLocations().toLowerCase() + "%")));
            }

            if(!queryDto.getProductionCompany().isEmpty()){
                predicates.add(
                        criteriaBuilder.and(criteriaBuilder
                                .like(criteriaBuilder.lower(root.get("productionCompany")),
                                        "%" + queryDto.getProductionCompany().toLowerCase() + "%")));
            }

            if(!queryDto.getWriter().isEmpty()){
                predicates.add(
                        criteriaBuilder.and(criteriaBuilder
                                .like(criteriaBuilder.lower(root.get("writer")),
                                        "%" + queryDto.getWriter().toLowerCase() + "%")));
            }

            if(predicates.isEmpty())
                return criteriaBuilder.disjunction();
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
