package com.turkcell.pair3.customerservice.services.rules;

import com.turkcell.pair3.customerservice.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AddressBusinessRules {
    private final AddressRepository addressRepository;


}
