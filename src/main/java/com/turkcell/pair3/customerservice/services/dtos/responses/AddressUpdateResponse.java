package com.turkcell.pair3.customerservice.services.dtos.responses;

import lombok.*;

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
