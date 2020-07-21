package com.anizzzz.sfmovies.controller;

import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.model.MovieData;
import com.anizzzz.sfmovies.service.IMovieDataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class MovieDataControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IMovieDataService movieDataService;
    @InjectMocks
    private MovieDataController movieDataController;

    private List<MovieData> movieDatas;

    @Before
    public void setup(){
        movieDatas = new ArrayList<>();
        movieDatas.add(new MovieData("Harry Potter", "2002", "USA", "Madan Bahadur",
                "H.P.Prod", "", "JK Rowlings", "Daniel Radcliffe", "Ema Watson", "Rupert Grint"));
        movieDatas.add(new MovieData("Always Be My Maybe", "2019", "Heart of the City Farmers Market at UN Plaza", "",
                "Isla Productions, LLC",  "Netflix",
                "Michael Golamco, Randall Park, Ali Wong", "Ali Wong", "Randall Park", "Keanu Reeves"));
        movieDatas.add(new MovieData("Ant-Man", "2015",  "Grant between Bush and Broadway", "Driving shots",
                "PYM Particles Productions, LLC", "Walt Disney Studios Motion Pictures",
                "Gabriel Ferrari", "Michael Douglas", "Paul Rudd", ""));
    }

    @Test
    public void testAutocomplete_WhenAutocompletionListEmpty() {
        DataQuery query = new DataQuery();
        when(movieDataService.autocompleteData(query)).thenReturn(Collections.emptyList());

        ResponseEntity<List<MovieData>> responseEntity = (ResponseEntity<List<MovieData>>) movieDataController.autocompleteData(query);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertTrue(responseEntity.getBody().isEmpty());
    }

    @Test
    public void testAutocomplete_WhenAutocompletionListNotEmpty(){
        DataQuery query = new DataQuery();
        when(movieDataService.autocompleteData(query)).thenReturn(movieDatas);

        ResponseEntity<List<MovieData>> responseEntity = (ResponseEntity<List<MovieData>>) movieDataController.autocompleteData(query);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertFalse(responseEntity.getBody().isEmpty());

        List<MovieData> responses = responseEntity.getBody();
        Assert.assertEquals(movieDatas.size(), responses.size());
        Assert.assertEquals(movieDatas.get(0).getTitle(), responses.get(0).getTitle());
    }
}
