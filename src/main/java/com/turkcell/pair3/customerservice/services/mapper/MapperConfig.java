package com.turkcell.pair3.customerservice.services.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public AddressMapper addressMapper() {
        return AddressMapper.INSTANCE;
    }

    @Bean
    public CityMapper cityMapper() {
        return CityMapper.INSTANCE;
    }

    @Bean
    public IndividualCustomerMapper individualCustomerMapper() {
        return IndividualCustomerMapper.INSTANCE;
    }
}
