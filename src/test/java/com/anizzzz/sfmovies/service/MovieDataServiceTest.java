package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.dto.CoordinateData;
import com.anizzzz.sfmovies.dto.CoordinatePosition;
import com.anizzzz.sfmovies.dto.DataQuery;
import com.anizzzz.sfmovies.model.MovieData;
import com.anizzzz.sfmovies.repository.MovieDataRepository;
import com.anizzzz.sfmovies.service.impl.MovieDataService;
import com.anizzzz.sfmovies.spec.MovieDataSpec;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieDataServiceTest {
    @Value("${sf-movie.data-url}")
    private String sfMovieUrl;

    @Mock
    private MovieDataRepository movieDataRepositoryMock;
    @Mock
    private RestTemplate restTemplateMock;
    @Mock
    private ICoordinatesUtilService coordinatesUtilServiceMock;
    @Mock
    private MovieDataSpec movieDataSpec;
    @InjectMocks
    private MovieDataService movieServiceMock;

    private Set<MovieData> movieDatas;

    @Before
    public void setup(){
        movieDatas = new HashSet<>();
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
    public void checkIfDataIsInitialized_IfNoDataInDB_ShouldReturnFalse(){
        when(movieDataRepositoryMock.count()).thenReturn(0L);
        Assert.assertFalse(movieServiceMock.isDataInitialized());
    }

    @Test
    public void checkIfDataIsInitialized_IfDataInDB_ShouldReturnTrue(){
        when(movieDataRepositoryMock.count()).thenReturn(300L);
        Assert.assertTrue(movieServiceMock.isDataInitialized());
    }

    @Test
    public void SFMovieUrlFetching_WhenOtherStatusCodeIsReceived_ShouldReturnFalse(){
        ResponseEntity<Set<MovieData>> mockEntity = new ResponseEntity<>(movieDatas, HttpStatus.BAD_REQUEST);

        when(restTemplateMock.exchange(sfMovieUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<Set<MovieData>>() {}))
                .thenReturn(mockEntity);
        Assert.assertFalse(movieServiceMock.loadSFMovieData());
    }

    @Test
    public void SFMovieUrlFetching_WhenNullValueIsReceived_ShouldReturnFalse(){
        ResponseEntity<Set<MovieData>> mockEntity = new ResponseEntity<>((Set<MovieData>) null, HttpStatus.OK);

        when(restTemplateMock.exchange(sfMovieUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<Set<MovieData>>() {}))
                .thenReturn(mockEntity);
        Assert.assertFalse(movieServiceMock.loadSFMovieData());
    }

    @Test
    public void whenSFMovieUrlFetching_WhenActualDataIsReceived_SaveData_ReturnTrue(){
        ResponseEntity<Set<MovieData>> mockEntity = new ResponseEntity<>(movieDatas, HttpStatus.OK);
        when(restTemplateMock.exchange(sfMovieUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<Set<MovieData>>() {}))
                .thenReturn(mockEntity);

        CoordinateData position = new CoordinateData("37.773972","-122.431297");
        when(coordinatesUtilServiceMock.getCoordinates(Matchers.anyString()))
                .thenReturn(new CoordinatePosition(position));

        Assert.assertTrue(movieServiceMock.loadSFMovieData());
        verify(coordinatesUtilServiceMock, times(3)).getCoordinates(Matchers.anyString());
    }

    @Test
    public void autocompletion_WhenAutocompletionMethodCalled_MustCallMovieDataSpecMethod(){
        DataQuery query = new DataQuery();

        movieServiceMock.autocompleteData(query);
        verify(movieDataSpec, times(1)).findAllByFilters(query);
    }
}
