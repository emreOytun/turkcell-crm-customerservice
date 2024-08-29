package com.turkcell.pair3.customerservice.services.rules;

import com.turkcell.pair3.core.exception.types.BusinessException;
import com.turkcell.pair3.customerservice.entities.IndividualCustomer;
import com.turkcell.pair3.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.pair3.customerservice.services.messages.CustomerMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class IndividualCustomerBusinessRules
{
    private final IndividualCustomerRepository individualCustomerRepository;


    public void customerWithSameNationalityIdCanNotExist(String nationalityId)
    {
        Optional<IndividualCustomer> customer = individualCustomerRepository.findByNationalityId(nationalityId);

        if(customer.isPresent())
            throw new BusinessException(CustomerMessages.CUSTOMER_WITH_SAME_IDENTITY_EXISTS);
    }

}
