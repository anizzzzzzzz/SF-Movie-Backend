package com.anizzzz.sfmovies.controller;

import com.anizzzz.sfmovies.service.ICoordinatesUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class CoordinatesController {
    private final ICoordinatesUtilService coordinatesUtilService;

    @Autowired
    public CoordinatesController(ICoordinatesUtilService coordinatesUtilService) {
        this.coordinatesUtilService = coordinatesUtilService;
    }

    @GetMapping
    public ResponseEntity<?> searchLocation(@RequestParam(name = "q") String query){
        return ResponseEntity.ok(coordinatesUtilService.getCoordinates(query));
    }
}
