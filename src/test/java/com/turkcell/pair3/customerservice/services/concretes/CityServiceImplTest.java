package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.core.exception.types.BusinessException;
import com.turkcell.pair3.core.exception.types.BusinessExceptionFactory;
import com.turkcell.pair3.customerservice.entities.City;
import com.turkcell.pair3.customerservice.repositories.CityRepository;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.CityUpdateResponse;
import com.turkcell.pair3.customerservice.services.mapper.CityMapper;
import com.turkcell.pair3.customerservice.services.messages.CityMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        CityAddRequest request = new CityAddRequest();
        City city = new City();
        city.setId(1);

        when(cityMapper.cityFromAddRequest(any(CityAddRequest.class))).thenReturn(city);
        when(cityRepository.save(any(City.class))).thenReturn(city);

        Integer savedId = cityService.save(request);

        assertNotNull(savedId);
        assertEquals(city.getId(), savedId);
        verify(cityRepository).save(city);
    }

    @Test
    void testDelete_whenCityExists() {
        Integer id = 1;

        when(cityRepository.existsById(eq(id))).thenReturn(true);

        cityService.delete(id);

        verify(cityRepository).deleteById(eq(id));
    }

    @Test
    void testDelete_whenCityDoesNotExist() {
        Integer id = 1;

        when(cityRepository.existsById(eq(id))).thenReturn(false);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> cityService.delete(id)
        );

        assertEquals(CityMessages.NO_CITY_FOUND, exception.getMessage());
        verify(cityRepository, never()).deleteById(eq(id));
    }

    @Test
    void testUpdate_whenCityExists() {
        Integer id = 1;
        CityUpdateRequest request = new CityUpdateRequest();
        City city = new City();
        City updatedCity = new City(); // Assuming the updated city is different
        CityUpdateResponse response = new CityUpdateResponse();

        when(cityRepository.findById(eq(id))).thenReturn(Optional.of(city));
        // Mocking the void method updateCityField does not require when setup
        when(cityRepository.save(any(City.class))).thenReturn(updatedCity);
        when(cityMapper.cityUpdateResponseFromCity(any(City.class))).thenReturn(response);

        CityUpdateResponse updatedResponse = cityService.update(id, request);

        assertNotNull(updatedResponse);
        assertEquals(response, updatedResponse);
        verify(cityMapper).updateCityField(eq(city), eq(request)); // Verify method call
        verify(cityRepository).save(updatedCity);
    }

    @Test
    void testUpdate_whenCityDoesNotExist() {
        Integer id = 1;
        CityUpdateRequest request = new CityUpdateRequest();

        when(cityRepository.findById(eq(id))).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> cityService.update(id, request)
        );

        assertEquals(CityMessages.NO_CITY_FOUND, exception.getMessage());
        verify(cityRepository, never()).save(any(City.class));
    }
}
