package com.anizzzz.sfmovies.data_initializer;

import com.anizzzz.sfmovies.service.IAutocompleteService;
import com.anizzzz.sfmovies.service.IMovieDataService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {
    private final IMovieDataService movieService;
    private final IAutocompleteService autocompleteService;

    public DataInitializer(IMovieDataService movieService, IAutocompleteService autocompleteService) {
        this.movieService = movieService;
        this.autocompleteService = autocompleteService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*Will load all the data from CSV file to DB*/
        if(!movieService.isDataInitialized()){
            movieService.initializeDataToDB();
        }

        /*Will initialize the title to Trie*/
        autocompleteService.init();
    }
}
