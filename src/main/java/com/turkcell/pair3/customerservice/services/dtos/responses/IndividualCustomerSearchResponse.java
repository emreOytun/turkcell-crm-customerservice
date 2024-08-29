package com.turkcell.pair3.customerservice.services.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCustomerSearchResponse {
    private String customerId;
    private String firstName;
    private String lastName;
    private String secondName;
    private String nationalityId;
}