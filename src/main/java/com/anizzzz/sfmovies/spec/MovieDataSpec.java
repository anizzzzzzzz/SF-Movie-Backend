package com.anizzzz.sfmovies.spec;

import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.model.MovieData;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDataSpec {
    public Specification<MovieData> findAllByFilters(DataQuery queryDto){
        return (root, query, criteriaBuilder) -> {
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
        };
    }
}
