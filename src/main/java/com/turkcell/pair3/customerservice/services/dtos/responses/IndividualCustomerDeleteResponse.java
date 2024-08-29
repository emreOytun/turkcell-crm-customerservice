package com.turkcell.pair3.customerservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCustomerDeleteResponse {

    private String accountNumber;
    private String email;
    private String nationalityId;
    private String gsmNumber;
    private String firstName;
    private String lastName;
    private String secondName;

}
