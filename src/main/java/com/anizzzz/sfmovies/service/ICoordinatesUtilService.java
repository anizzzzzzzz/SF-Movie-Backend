package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.dto.CoordinatePosition;

public interface ICoordinatesUtilService {
    CoordinatePosition getCoordinates(String query);
}
