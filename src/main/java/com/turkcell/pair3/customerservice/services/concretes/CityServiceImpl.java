package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.core.exception.types.BusinessException;
import com.turkcell.pair3.customerservice.entities.City;
import com.turkcell.pair3.customerservice.repositories.CityRepository;
import com.turkcell.pair3.customerservice.services.abstracts.CityService;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.CityUpdateResponse;
import com.turkcell.pair3.customerservice.services.mapper.CityMapper;
import com.turkcell.pair3.customerservice.services.messages.CityMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public Integer save(CityAddRequest request) {

        City city = CityMapper.INSTANCE.cityFromAddRequest(request);

        city = cityRepository.save(city);

        return city.getId();
    }

    @Override
    public void delete(Integer id) {
        Optional<City> city = cityRepository.findById(id);

        if(city.isEmpty())
            throw new BusinessException(CityMessages.NO_CITY_FOUND);

        cityRepository.delete(city.get());
    }

    @Override
    public CityUpdateResponse update(int id, CityUpdateRequest request) {
        Optional<City> city = cityRepository.findById(id);

        if(city.isEmpty())
            throw new BusinessException(CityMessages.NO_CITY_FOUND);

        City updatedCity = city.get();

        CityMapper.INSTANCE.updateCityField(updatedCity, request);

        updatedCity = cityRepository.save(updatedCity);

        return CityMapper.INSTANCE.cityUpdateResponseFromCity(updatedCity);
    }
}
