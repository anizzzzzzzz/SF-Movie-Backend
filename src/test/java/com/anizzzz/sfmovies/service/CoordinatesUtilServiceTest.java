/*
package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.config.GeocodeCredential;
import com.anizzzz.sfmovies.service.impl.CoordinatesUtilService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CoordinatesUtilServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RestTemplate restTemplateMock;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private GeocodeCredential geocodeCredential;
    @Mock
    private UriComponentsBuilder componentsBuilder;
    @InjectMocks
    private CoordinatesUtilService coordinatesUtilService;

    @Test
    public void CoordinateFetching_WhenOtherStatusCodeIsReceived_ShouldReturnFalse(){
        String url = "https://geocode.search.hereapi.com/v1/geocode?q=abc&apiKey=apiKey";

        when(geocodeCredential.getGeocodeUrl()).thenReturn(url);
        when(geocodeCredential.getApiKey()).thenReturn("key");

        ArgumentCaptor<HttpEntity> httpEntityCapture = ArgumentCaptor.forClass(HttpEntity.class);

        ResponseEntity<String> mockEntity = new ResponseEntity<>("Response Data", HttpStatus.BAD_REQUEST);
        when(restTemplateMock.exchange(Mockito.eq(url), Mockito.eq(HttpMethod.GET), httpEntityCapture.capture(), Mockito.eq(String.class)))
                .thenReturn(mockEntity);
        when()

        coordinatesUtilService.getCoordinates("ANy Street");
//        Assert.isNull(coordinatesUtilService.getCoordinates("ANy Street"));
        verify(restTemplateMock).exchange(Mockito.eq(url), Mockito.eq(HttpMethod.GET), httpEntityCapture.capture(), Mockito.eq(String.class));
    }

}
*/
