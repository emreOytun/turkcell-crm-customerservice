package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.core.exception.types.BusinessExceptionFactory;
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

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public Integer save(CityAddRequest request) {
        return cityRepository.save(cityMapper.cityFromAddRequest(request)).getId();
    }

    @Override
    public void delete(Integer id) {
        checkIfCityExistsOrThrowException(id);
        cityRepository.deleteById(id);
    }

    private void checkIfCityExistsOrThrowException(Integer id) {
        if (!cityRepository.existsById(id)) {
            throw BusinessExceptionFactory.createWithMessage(CityMessages.NO_CITY_FOUND);
        }
    }

    private City searchCityByIdOrThrowExceptionIfNotFound(Integer id) {
        return cityRepository.findById(id).orElseThrow(
                () -> BusinessExceptionFactory.createWithMessage(CityMessages.NO_CITY_FOUND));
    }

    @Override
    public CityUpdateResponse update(int id, CityUpdateRequest request) {
        City updatedCity = searchCityByIdOrThrowExceptionIfNotFound(id);
        cityMapper.updateCityField(updatedCity, request);
        updatedCity = cityRepository.save(updatedCity);
        return cityMapper.cityUpdateResponseFromCity(updatedCity);
    }
}
