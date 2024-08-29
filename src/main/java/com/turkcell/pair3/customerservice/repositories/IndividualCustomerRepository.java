package com.turkcell.pair3.customerservice.repositories;

import com.turkcell.pair3.customerservice.entities.IndividualCustomer;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerSearchRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.IndividualCustomerSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer> {
    Optional<IndividualCustomer> findByNationalityId(String nationalityId);

    Optional<IndividualCustomer> findByCustomerId(String customerId);

    boolean existsByNationalityId(String nationalityId);

    @Query("Select new com.turkcell.pair3.customerservice.services.dtos.responses." +
            "IndividualCustomerSearchResponse(c.customerId, c.firstName, c.lastName, c.secondName, c.nationalityId)" +
            " from IndividualCustomer c" +
            " where (c.customerId LIKE :#{#request.customerId}% OR :#{#request.customerId} IS NULL)" +
            " AND (c.nationalityId LIKE :#{#request.nationalityId}% OR :#{#request.nationalityId} IS NULL)" +
            " AND (c.firstName LIKE :#{#request.firstName}% OR :#{#request.firstName} IS NULL)" +
            " AND (c.lastName LIKE :#{#request.lastName}% OR :#{#request.lastName} IS NULL)"
    )
    List<IndividualCustomerSearchResponse> search(IndividualCustomerSearchRequest request);


}
