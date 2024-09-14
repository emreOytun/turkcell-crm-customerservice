package com.turkcell.pair3.customerservice.services.mappers;

import com.turkcell.pair3.customerservice.entities.City;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.CityUpdateResponse;
import com.turkcell.pair3.customerservice.services.mapper.CityMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityMapperTest {

    private final CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

    @Test
    public void testCityFromAddRequest() {
        CityAddRequest request = new CityAddRequest();
        request.setCityName("New City");

        City city = cityMapper.cityFromAddRequest(request);

        assertEquals("New City", city.getCityName());
    }

    @Test
    public void testUpdateCityField() {
        City city = new City();
        city.setCityName("Old City");

        CityUpdateRequest request = new CityUpdateRequest();
        request.setCityName("Updated City");

        cityMapper.updateCityField(city, request);

        assertEquals("Updated City", city.getCityName());
    }

    @Test
    public void testCityUpdateResponseFromCity() {
        City city = new City();
        city.setId(1);
        city.setCityName("Test City");

        CityUpdateResponse response = cityMapper.cityUpdateResponseFromCity(city);

        assertEquals(1, response.getId());
        assertEquals("Test City", response.getCityName());
    }
}
