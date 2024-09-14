package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.customerservice.repositories.CityRepository;
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

class CityServiceImplTest {
    @Mock
    CityRepository cityRepository;
    @InjectMocks
    CityServiceImpl cityServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Integer result = cityServiceImpl.save(new CityAddRequest("cityName"));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testDelete() {
        cityServiceImpl.delete(Integer.valueOf(0));
    }

    @Test
    void testUpdate() {
        CityUpdateResponse result = cityServiceImpl.update(0, new CityUpdateRequest(Integer.valueOf(0), "cityName"));
        Assertions.assertEquals(new CityUpdateResponse(Integer.valueOf(0), "cityName"), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme