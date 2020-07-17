package com.anizzzz.sfmovies.dto;

import com.anizzzz.sfmovies.model.MovieData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class MovieDataResponse extends MovieData {
    private String latitude;
    private String longitude;

    public MovieDataResponse(MovieData movieData, String longitude, String latitude){
        super(movieData.getTitle(), movieData.getReleaseYear(), movieData.getLocations(), movieData.getFunFacts(),
                movieData.getProductionCompany(), movieData.getDistributor(), movieData.getWriter(), movieData.getActor1(),
                movieData.getActor2(), movieData.getActor3());
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
