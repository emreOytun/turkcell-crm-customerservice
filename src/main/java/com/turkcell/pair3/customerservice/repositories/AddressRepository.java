package com.turkcell.pair3.customerservice.repositories;

import com.turkcell.pair3.customerservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByCustomerId(Integer customerId);

    @Query("SELECT COUNT(a) > 0 FROM Address a WHERE a.customer.id = :customerId AND a.isPrimary = true")
    boolean existsPrimaryAddressByCustomerId(@Param("customerId") Integer customerId);


}
