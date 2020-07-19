package com.anizzzz.sfmovies.controller;

import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.service.IMovieDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sf-movie")
public class MovieDataController {
    private final IMovieDataService movieService;

    public MovieDataController(IMovieDataService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/autocomplete")
    public ResponseEntity<?> autocompleteData(@RequestBody DataQuery query){
        return ResponseEntity.ok(movieService.autocompleteData(query));
    }
}
