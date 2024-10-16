package com.turkcell.pair3.customerservice.clients;

import com.turkcell.pair3.core.configuration.feign.FeignClientConfiguration;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="invoiceservice",path = "/api/invoices", configuration = FeignClientConfiguration.class)
@Retry(name = "feignclient")
public interface InvoiceServiceClient {

    @GetMapping("/{customerId}/getAllInvoiceIds")
    List<Integer> getAllInvoiceIds(@PathVariable("customerId") Integer customerId);
}
