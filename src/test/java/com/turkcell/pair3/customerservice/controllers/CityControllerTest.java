package com.turkcell.pair3.customerservice.controllers;

import com.turkcell.pair3.customerservice.services.abstracts.CityService;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.CityUpdateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CityControllerTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCity() {
        CityAddRequest request = new CityAddRequest(/* add necessary fields */);
        when(cityService.save(any(CityAddRequest.class))).thenReturn(1);

        Integer result = cityController.saveCity(request);
        assertNotNull(result);
        assertEquals(1, result);
    }

    @Test
    void testDeleteCity() {
        Integer cityId = 1;
        doNothing().when(cityService).delete(cityId);

        assertDoesNotThrow(() -> cityController.delete(cityId));
        Mockito.verify(cityService).delete(eq(cityId));
    }

    @Test
    void testUpdateCity() {
        Integer cityId = 1;
        CityUpdateRequest updateRequest = new CityUpdateRequest(/* add necessary fields */);
        CityUpdateResponse expectedResponse = new CityUpdateResponse(/* add necessary fields */);
        when(cityService.update(eq(cityId), any(CityUpdateRequest.class))).thenReturn(expectedResponse);

        CityUpdateResponse response = cityController.update(cityId, updateRequest);
        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }
}
