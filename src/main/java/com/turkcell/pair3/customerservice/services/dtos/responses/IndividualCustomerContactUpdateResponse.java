package com.turkcell.pair3.customerservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerContactUpdateResponse {

    private String email;
    private String mobilePhone;
    private String homePhone;
    private String fax;

}
