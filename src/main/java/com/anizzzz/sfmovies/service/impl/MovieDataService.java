package com.anizzzz.sfmovies.service.impl;

import com.anizzzz.sfmovies.dto.CoordinatePosition;
import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.model.MovieData;
import com.anizzzz.sfmovies.repository.MovieDataRepository;
import com.anizzzz.sfmovies.service.ICoordinatesUtilService;
import com.anizzzz.sfmovies.service.IMovieDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpMethod;
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

    @Autowired
    public MovieDataService(MovieDataRepository movieRepository, RestTemplate restTemplate, ICoordinatesUtilService coordinateService) {
        this.movieRepository = movieRepository;
        this.restTemplate = restTemplate;
        this.coordinateService = coordinateService;
    }

    @Override
    public boolean initializeDataToDB() {
        ResponseEntity<Set<MovieData>> movieDataResponse = restTemplate.exchange(sfMovieUrl,
                            HttpMethod.GET, null, new ParameterizedTypeReference<Set<MovieData>>() {
                        });

        Optional<Set<MovieData>> optionalResponse = Optional.ofNullable(movieDataResponse.getBody());
        if(optionalResponse.isPresent()){
//            movieRepository.saveAll(optionalResponse.get());
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
        return false;
    }

    @Override
    public boolean isDataInitialized() {
        return movieRepository.count() > 0;
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

            if(!queryDto.getActor1().isEmpty()){
                predicates.add(
                        criteriaBuilder.and(criteriaBuilder
                                .like(criteriaBuilder.lower(root.get("actor1")),
                                        "%" + queryDto.getActor1().toLowerCase() + "%")));
            }

            if(!queryDto.getActor2().isEmpty()){
                predicates.add(
                        criteriaBuilder.and(criteriaBuilder
                                .like(criteriaBuilder.lower(root.get("actor2")),
                                        "%" + queryDto.getActor2().toLowerCase() + "%")));
            }

            if(!queryDto.getActor3().isEmpty()){
                predicates.add(
                        criteriaBuilder.and(criteriaBuilder
                                .like(criteriaBuilder.lower(root.get("actor3")),
                                        "%" + queryDto.getActor3().toLowerCase() + "%")));
            }

            if(predicates.isEmpty())
                return criteriaBuilder.disjunction();
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
