/*
package com.anizzzz.sfmovies.controller;

import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.service.IAutocompleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sf-movie/autocompletes")
public class AutocompleteController {
    private final IAutocompleteService autocompleteService;

    public AutocompleteController(IAutocompleteService autocompleteService) {
        this.autocompleteService = autocompleteService;
    }

    @PostMapping
    public ResponseEntity<?> autocomplete(@RequestBody DataQuery query){
        return ResponseEntity.ok(autocompleteService.autoComplete(query.getTitle()));
    }
}
*/
