package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.core.exception.types.BusinessException;
import com.turkcell.pair3.customerservice.entities.Address;
import com.turkcell.pair3.customerservice.repositories.AddressRepository;
import com.turkcell.pair3.customerservice.services.abstracts.AddressService;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.AddressUpdateResponse;
import com.turkcell.pair3.customerservice.services.mapper.AddressMapper;
import com.turkcell.pair3.customerservice.services.messages.AddressMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Integer save(AddressAddRequest request) {
        Address address = AddressMapper.INSTANCE.addressFromAddRequest(request);
        boolean exist = addressRepository.existsPrimaryAddressByCustomerId(address.getCustomer().getId());
        if(!exist) address.setPrimary(true);

        address = addressRepository.save(address);

        return address.getId();
    }

    @Override
    public void delete(Integer id){

        Optional<Address> address = addressRepository.findById(id);

        if(address.isEmpty()){
            throw new BusinessException(AddressMessages.NO_ADDRESS_FOUND);
        }

        addressRepository.delete(address.get());
    }

    @Override
    public AddressUpdateResponse update(int id, AddressUpdateRequest request){

        Optional<Address> address = addressRepository.findById(id);

        if(address.isEmpty()){
            throw new BusinessException(AddressMessages.NO_ADDRESS_FOUND);
        }

        Address addressUpdated = address.get();

        AddressMapper.INSTANCE.updateAddressField(addressUpdated, request);

        addressUpdated = addressRepository.save(addressUpdated);

        return AddressMapper.INSTANCE.addressUpdateResponseFromAddress(addressUpdated);
    }

    @Override
    public void makePrimary(Integer id){
        Optional<Address> address = addressRepository.findById(id);
        if(address.isEmpty()){
            throw new BusinessException(AddressMessages.NO_ADDRESS_FOUND);
        }

        List<Address> addressList = addressRepository.findByCustomerId(address.get().getCustomer().getId());
        for (Address a : addressList) {
            if(a.isPrimary()==true){
                a.setPrimary(false);
                addressRepository.save(a);
                break;
            }
        }

        Address addressUpdated = address.get();
        addressUpdated.setPrimary(true);
        addressRepository.save(addressUpdated);
    }


}
