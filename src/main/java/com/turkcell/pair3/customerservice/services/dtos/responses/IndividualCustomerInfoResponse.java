package com.turkcell.pair3.customerservice.services.dtos.responses;

import com.turkcell.pair3.customerservice.enums.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerInfoResponse {
    private String firstName;
    private String lastName;
    private EnumGender gender;
    private String motherName;
    private String fatherName;
    private String secondName;
    private LocalDate birthDate;
    private String nationalityId;
}
