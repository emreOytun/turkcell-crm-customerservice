package com.turkcell.pair3.customerservice.services.dtos.responses;

import com.turkcell.pair3.customerservice.services.constants.Messages;
import jakarta.validation.constraints.NotBlank;
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
