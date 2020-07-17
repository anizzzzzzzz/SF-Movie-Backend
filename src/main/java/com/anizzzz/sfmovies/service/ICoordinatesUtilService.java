package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.dto.CoordinateData;

import java.util.List;

public interface ICoordinatesUtilService {
    List<CoordinateData> getCoordinates(String query);
}
