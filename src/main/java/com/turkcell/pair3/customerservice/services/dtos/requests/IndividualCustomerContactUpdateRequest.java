package com.turkcell.pair3.customerservice.services.dtos.requests;

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
public class IndividualCustomerContactUpdateRequest {

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String customerId;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String email;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String mobilePhone;

    private String homePhone;

    private String fax;
}
