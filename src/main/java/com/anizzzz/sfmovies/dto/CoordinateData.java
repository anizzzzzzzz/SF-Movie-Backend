package com.anizzzz.sfmovies.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoordinateData implements Serializable {
    private String lat = "";
    private String lng = "";
}
