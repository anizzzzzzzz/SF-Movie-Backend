/*
package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.repository.MovieDataRepository;
import com.anizzzz.sfmovies.service.impl.MovieDataService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieDataServiceTest {
    @MockBean
    private MovieDataRepository movieDataRepositoryMock;
    @Autowired
    private MovieDataService movieServiceMock;

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
*/
