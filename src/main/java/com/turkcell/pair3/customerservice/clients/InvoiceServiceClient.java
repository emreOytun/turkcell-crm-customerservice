package com.turkcell.pair3.customerservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import com.turkcell.pair3.core.configuration.feign.FeignClientConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="invoiceservice",path = "/api/invoices", configuration = FeignClientConfiguration.class)
public interface InvoiceServiceClient {

    @GetMapping("/{customerId}/getAllInvoiceIds")
    List<Integer> getAllInvoiceIds(@PathVariable("customerId") Integer customerId);
}
