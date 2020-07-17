package com.anizzzz.sfmovies.repository;

import com.anizzzz.sfmovies.model.MovieData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieDataRepository extends JpaRepository<MovieData, Integer>, JpaSpecificationExecutor<MovieData> {
}
