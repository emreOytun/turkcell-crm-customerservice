package com.turkcell.pair3.customerservice.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCustomerSearchRequest
{
    private String nationalityId;
    private String customerId;
    private String gsmNumber;
    private String firstName;
    private String lastName;
}

