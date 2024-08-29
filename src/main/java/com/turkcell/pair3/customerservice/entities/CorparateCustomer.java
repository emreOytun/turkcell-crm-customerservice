package com.turkcell.pair3.customerservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "corparate_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CorparateCustomer extends Customer{

    private String companyName;

}
