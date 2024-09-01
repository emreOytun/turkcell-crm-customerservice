package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.core.exception.types.BusinessExceptionFactory;
import com.turkcell.pair3.customerservice.entities.Address;
import com.turkcell.pair3.customerservice.repositories.AddressRepository;
import com.turkcell.pair3.customerservice.services.abstracts.AddressService;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.AddressUpdateResponse;
import com.turkcell.pair3.customerservice.services.mapper.AddressMapper;
import com.turkcell.pair3.customerservice.services.messages.AddressMessages;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Integer save(AddressAddRequest request) {
        Address address = AddressMapper.INSTANCE.addressFromAddRequest(request);
        if (!addressRepository.existsPrimaryAddressByCustomerId(address.getCustomer().getId())) {
            address.setPrimary(true);
        }
        return addressRepository.save(address).getId();
    }

    @Override
    public void delete(Integer id) {
        checkIfAddressExistsOrThrowException(id);
        addressRepository.deleteById(id);
    }

    @Override
    public AddressUpdateResponse update(int id, AddressUpdateRequest request) {
        Address addressUpdated = searchAddressByIdOrThrowExceptionIfNotFound(id);
        AddressMapper.INSTANCE.updateAddressField(addressUpdated, request);
        addressUpdated = addressRepository.save(addressUpdated);
        return AddressMapper.INSTANCE.addressUpdateResponseFromAddress(addressUpdated);
    }

    @Override
    public void makePrimary(Integer newPrimaryAddressId) {
        dropOldPrimaryAddressAndMarkNewPrimaryAddress(
                addressRepository.findFirstCustomerIdByAddressId(newPrimaryAddressId).get(), newPrimaryAddressId);
    }

    @Transactional
    private void dropOldPrimaryAddressAndMarkNewPrimaryAddress(Integer customerId, Integer newPrimaryAddressId) {
        addressRepository.removeAllPrimaryAddressesByCustomerId(customerId);
        addressRepository.markAsPrimary(newPrimaryAddressId);
    }

    private Address searchAddressByIdOrThrowExceptionIfNotFound(Integer id) {
        return addressRepository.findById(id).orElseThrow(
                () -> BusinessExceptionFactory.createWithMessage(AddressMessages.NO_ADDRESS_FOUND));
    }

    private void checkIfAddressExistsOrThrowException(Integer id) {
        if (!addressRepository.existsById(id)) {
            throw BusinessExceptionFactory.createWithMessage(AddressMessages.NO_ADDRESS_FOUND);
        }
    }
}
