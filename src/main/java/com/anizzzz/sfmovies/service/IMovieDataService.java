package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.model.MovieData;

import java.util.List;

public interface IMovieDataService {
    /**
     * This method will be called only once when application first starts.
     * This function will fetch the SF-Movie data from the external url and will save it in the database.
     * Since, the data doesn't consist of the coordinates of the location, it will internally geocode the location by calling the external api.
     *
     * @return boolean
     *       - True if successfully saves the data
     *       - False if exception occurs
     */
    boolean loadSFMovieData();

    /**
     * This method will be called each time when application restarts.
     * It is used to check whether data has been already fetched and stored in database.
     *
     * @return boolean
     *       - True if already fetched and stored
     *       - False if application is run for the very first time.
     */
    boolean isDataInitialized();

    /**
    * This method is for search/filter the MovieData based on DataQuery.
    * It uses Spring Boot's JPA specification for dynamic filtering of the data.
    *
    * @param queryDto: DataQuery
     *                - query of different fields like title, locations, writer, actors to perform the filtering of the data.
     *
     * @return List<MovieData> :
     *                - List of filtered MovieData
    * */
    List<MovieData> autocompleteData(DataQuery queryDto);
}
