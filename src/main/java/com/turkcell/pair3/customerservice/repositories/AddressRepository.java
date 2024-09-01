package com.turkcell.pair3.customerservice.repositories;

import com.turkcell.pair3.customerservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<List<Address>> findByCustomerId(Integer customerId);

    @Query("SELECT EXISTS (SELECT 1 FROM Address a WHERE a.customer.id = :customerId AND a.isPrimary = true)")
    boolean existsPrimaryAddressByCustomerId(@Param("customerId") Integer customerId);

    @Query("SELECT a.customer.id FROM Address a WHERE a.id = :addressId")
    Optional<Integer> findFirstCustomerIdByAddressId(@Param("addressId") Integer addressId);

    @Query("SELECT a.id FROM Address a WHERE a.customer.id = :customerId AND a.isPrimary = true")
    Optional<Integer> findFirstPrimaryAddressIdByCustomerId(@Param("customerId") Integer customerId);

    @Query("UPDATE Address a SET a.isPrimary = false WHERE a.isPrimary = true AND a.customer.id = :customerId")
    @Modifying
    void removeAllPrimaryAddressesByCustomerId(@Param("customerId") Integer customerId);

    @Query("UPDATE Address a SET a.isPrimary = false WHERE a.id = :id")
    @Modifying
    void markAsNotPrimary(@Param("id") Integer id);

    @Query("UPDATE Address a SET a.isPrimary = true WHERE a.id = :id")
    @Modifying
    void markAsPrimary(@Param("id") Integer id);
}
