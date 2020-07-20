package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.model.MovieData;

import java.util.List;

public interface IMovieDataService {
    boolean loadSFMovieData();

    boolean isDataInitialized();

    List<MovieData> autocompleteData(DataQuery queryDto);
}
