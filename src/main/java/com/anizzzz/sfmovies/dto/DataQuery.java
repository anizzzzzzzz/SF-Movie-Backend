package com.anizzzz.sfmovies.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DataQuery {
    private String title = "";
    private String locations = "";
    private String productionCompany = "";
    private String distributor = "";
    private String writer = "";
    private String actor1 = "";
    private String actor2 = "";
    private String actor3 = "";
}
