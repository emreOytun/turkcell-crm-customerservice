package com.turkcell.pair3.customerservice.clients;

import com.turkcell.pair3.core.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="productservice", configuration = FeignClientConfiguration.class)
public interface ProductClient {

    @PostMapping("/api/carts/{customerId}/createCart")
    Integer createCart(@PathVariable Integer customerId);
}
