package com.anizzzz.sfmovies.service;

import com.anizzzz.sfmovies.service.impl.CoordinatesUtilService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class CoordinatesUtilServiceTest {
    @Mock
    private RestTemplate restTemplateMock;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private UriComponentsBuilder componentsBuilder;
    @InjectMocks
    private CoordinatesUtilService coordinatesUtilService;

}
