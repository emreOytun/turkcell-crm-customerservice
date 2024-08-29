package com.turkcell.pair3.customerservice.services.dtos.requests;

import com.turkcell.pair3.customerservice.services.constants.Messages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerAddRequest {

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String email;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String nationalityId;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String gsmNumber;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String firstName;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String lastName;

    private String secondName;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String role;

    //@NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private LocalDate birthDate;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String gender;

    private String fatherName;

    private String motherName;

    private String homePhone;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String mobilePhone;

    private String fax;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String password;

    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String username;

}
