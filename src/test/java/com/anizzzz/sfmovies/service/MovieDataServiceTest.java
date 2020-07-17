package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.model.MovieData;
import com.anizzzz.sfmovies.repository.MovieDataRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieDataServiceTest {
    @MockBean
    private MovieDataRepository movieDataRepositoryMock;
    @Autowired
    @Qualifier("movieDataService")
    private IMovieDataService movieServiceMock;
    @MockBean
    private RestTemplate restTemplate;

    private Set<MovieData> movieDataSet;

    @Before
    public void setup(){
        movieDataSet = new HashSet<>();
    }

    @Test
    public void checkIfDataIsInitialized_IfNoDataInDB(){
        when(movieDataRepositoryMock.count()).thenReturn(0L);
        Assert.assertFalse(movieServiceMock.isDataInitialized());
    }

    @Test
    public void checkIfDataIsInitialized_IfDataInDB(){
        when(movieDataRepositoryMock.count()).thenReturn(300L);
        Assert.assertTrue(movieServiceMock.initializeDataToDB());
    }
}
