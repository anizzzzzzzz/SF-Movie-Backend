package com.anizzzz.sfmovies.controller;

import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.service.ICoordinatesUtilService;
import com.anizzzz.sfmovies.service.IMovieDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sf-movie")
public class MovieDataController {
    private final IMovieDataService movieService;
    @Autowired
    private ICoordinatesUtilService coordinatesUtilService;

    public MovieDataController(IMovieDataService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterMovieData(@RequestBody DataQuery query){
        return ResponseEntity.ok(movieService.getCoordinatesOfSearchData(query));
    }

    @GetMapping("/map")
    public ResponseEntity<?> getCoordinates(@RequestParam(name = "city") String city){
        return ResponseEntity.ok(coordinatesUtilService.getCoordinates(city));
    }
}
