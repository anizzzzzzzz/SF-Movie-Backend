package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.model.MovieData;

import java.util.List;

public interface IMovieDataService {
    boolean initializeDataToDB();

    boolean isDataInitialized();

    List<MovieData> getCoordinatesOfSearchData(DataQuery queryDto);
}
