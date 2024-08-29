package com.turkcell.pair3.customerservice.services.dtos.responses;

import com.turkcell.pair3.customerservice.enums.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCustomerAddResponse {
    private Integer id;
    private String customerId;
    private String email;
    private String nationalityId;
    private String gsmNumber;
    private String firstName;
    private String lastName;
    private String secondName;
    private String role;
    private LocalDate birthDate;
    private EnumGender gender;
    private String fatherName;
    private String motherName;
    private String homePhone;
    private String mobilePhone;
    private String fax;

}
