package com.turkcell.pair3.customerservice.services.concretes;

import com.turkcell.pair3.core.dtos.requests.SearchByPageRequest;
import com.turkcell.pair3.customerservice.clients.AuthServiceClient;
import com.turkcell.pair3.customerservice.clients.ProductClient;
import com.turkcell.pair3.customerservice.entities.IndividualCustomer;
import com.turkcell.pair3.customerservice.repositories.IndividualCustomerRepository;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.IndividualCustomerUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.IndividualCustomerAddResponse;
import com.turkcell.pair3.customerservice.services.dtos.responses.IndividualCustomerInfoResponse;
import com.turkcell.pair3.customerservice.services.mapper.IndividualCustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndividualCustomerServiceImplTest {

    @Mock
    private IndividualCustomerRepository individualCustomerRepository;

    @Mock
    private AuthServiceClient authServiceClient;

    @Mock
    private ProductClient productClient;

    @Mock
    private IndividualCustomerMapper individualCustomerMapper;

    @InjectMocks
    private IndividualCustomerServiceImpl individualCustomerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCustomer() {
        IndividualCustomerAddRequest request = new IndividualCustomerAddRequest();
        IndividualCustomer customer = new IndividualCustomer();
        customer.setUserId(12345); // Setting userId as Integer

        when(individualCustomerMapper.individualCustomerFromAddRequest(request)).thenReturn(customer);
        when(authServiceClient.register(any())).thenReturn(1); // Mocking the register call
        when(individualCustomerRepository.save(customer)).thenReturn(customer);
        when(productClient.createCart(anyInt())).thenReturn(null); // Mocking createCart

        IndividualCustomerAddResponse response = individualCustomerService.saveCustomer(request);

        verify(individualCustomerRepository).save(customer);
        verify(productClient).createCart(eq(customer.getId())); // Verifying createCart is called
    }

    @Test
    public void testUpdateCustomer() {
        Integer id = 1;
        IndividualCustomerUpdateRequest request = new IndividualCustomerUpdateRequest();
        IndividualCustomer customer = new IndividualCustomer();
        customer.setUserId(12345); // Setting userId as Integer

        when(individualCustomerRepository.findById(id)).thenReturn(Optional.of(customer));
        doNothing().when(individualCustomerMapper).updateIndividualCustomerField(any(IndividualCustomer.class), any(IndividualCustomerUpdateRequest.class));
        when(individualCustomerRepository.save(customer)).thenReturn(customer);
        when(individualCustomerMapper.individualCustomerInfoResponseFromCustomer(customer)).thenReturn(new IndividualCustomerInfoResponse());

        IndividualCustomerInfoResponse response = individualCustomerService.updateCustomer(id, request);

        verify(individualCustomerMapper).updateIndividualCustomerField(any(IndividualCustomer.class), any(IndividualCustomerUpdateRequest.class));
        verify(individualCustomerRepository).save(customer);
    }

    @Test
    public void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        List<IndividualCustomer> customers = List.of(new IndividualCustomer());
        Page<IndividualCustomer> page = new PageImpl<>(customers);

        when(individualCustomerRepository.findAll(pageable)).thenReturn(page);

        List<IndividualCustomerInfoResponse> response = individualCustomerService.getAll(new SearchByPageRequest(0, 10));

        verify(individualCustomerRepository).findAll(pageable);
    }
}
