package com.anizzzz.sfmovies.controller;

import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.service.IMovieDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sf-movie")
public class MovieDataController {
    private final IMovieDataService movieService;

    public MovieDataController(IMovieDataService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterMovieData(@RequestBody DataQuery query){
        return ResponseEntity.ok(movieService.filterData(query));
    }

    @PostMapping("/autocomplete")
    public ResponseEntity<?> autocompleteData(@RequestBody DataQuery query){
        return ResponseEntity.ok(movieService.autocompleteData(query));
    }
}
