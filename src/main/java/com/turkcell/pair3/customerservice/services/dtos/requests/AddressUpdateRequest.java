package com.turkcell.pair3.customerservice.services.dtos.requests;

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
public class AddressUpdateRequest {
    @NotNull(message = Messages.ValidationErrors.NOT_NULL)
    private Integer id;

    @NotNull(message = Messages.ValidationErrors.NOT_NULL)
    private Integer cityId;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private Integer houseNumber;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String description;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private Integer customerId;

}
