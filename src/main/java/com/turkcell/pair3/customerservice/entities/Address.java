package com.turkcell.pair3.customerservice.entities;

import com.turkcell.pair3.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "house_number", nullable = false)
    private Integer houseNumber;

    @Column(name = "description", nullable = false)
    private String description;

    @JoinColumn(name = "customer_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Customer customer;

    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary;
}
