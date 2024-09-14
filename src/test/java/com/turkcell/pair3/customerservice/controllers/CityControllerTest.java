package com.turkcell.pair3.customerservice.controllers;

import com.turkcell.pair3.customerservice.services.abstracts.CityService;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.CityUpdateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class CityControllerTest {
    @Mock
    CityService cityService;
    @InjectMocks
    CityController cityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCity() {
        when(cityService.save(any())).thenReturn(Integer.valueOf(0));

        Integer result = cityController.saveCity(new CityAddRequest("cityName"));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testDelete() {
        cityController.delete(Integer.valueOf(0));
    }

    @Test
    void testUpdate() {
        when(cityService.update(anyInt(), any())).thenReturn(new CityUpdateResponse(Integer.valueOf(0), "cityName"));

        CityUpdateResponse result = cityController.update(Integer.valueOf(0), new CityUpdateRequest(Integer.valueOf(0), "cityName"));
        Assertions.assertEquals(new CityUpdateResponse(Integer.valueOf(0), "cityName"), result);
    }
}

