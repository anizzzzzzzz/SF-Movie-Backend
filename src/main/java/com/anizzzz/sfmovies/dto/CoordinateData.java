package com.anizzzz.sfmovies.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(value = { "place_id", "licence", "osm_type", "osm_id", "boundingbox", "display_name",
        "class", "type", "importance", "address"}, ignoreUnknown = true)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class CoordinateData implements Serializable {
    @JsonAlias(value = "lat")
    private String latitude;
    @JsonAlias(value = "lon")
    private String longitude;
}
