package com.turkcell.pair3.customerservice.services.dtos.responses;

import com.turkcell.pair3.customerservice.services.constants.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateResponse {
    private Integer cityId;

    private Integer houseNumber;

    private String description;

    private Integer customerId;
}
