package com.anizzzz.sfmovies.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
@Setter
public class CoordinatePosition implements Serializable {
    private CoordinateData position;

    public CoordinatePosition(){
        position = new CoordinateData();
    }
}