package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.dto.CoordinatePosition;

public interface ICoordinatesUtilService {
    /**
     * It will response the coordinates ie; longitude and latitude of the location.
     * This method will fetch the third party API for getting the coordinates of the location.
     *
     * @param query : String
     *              - Locations where movie is filmed
     *
     * @return CoordinatePosition :
     *              - Longitude and Latitude of the query location.*/
    CoordinatePosition getCoordinates(String query);
}
