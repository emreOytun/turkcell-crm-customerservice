package com.turkcell.pair3.customerservice.services.abstracts;

import com.turkcell.pair3.customerservice.services.dtos.requests.AddressAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.AddressUpdateResponse;

public interface AddressService {
    Integer save(AddressAddRequest request);
    void delete(Integer id);

    AddressUpdateResponse update(int id, AddressUpdateRequest request);

    void makePrimary(Integer id);
}
