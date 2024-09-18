package com.turkcell.pair3.customerservice.clients;

import com.turkcell.pair3.core.configuration.feign.FeignClientConfiguration;
import com.turkcell.pair3.core.events.RegisterEvent;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="authservice",path = "/api/v1/auth", configuration = FeignClientConfiguration.class)
@Retry(name = "feignclient")
public interface AuthServiceClient {

    @PostMapping("/register")
    Integer register(@RequestBody RegisterEvent request);

    @PutMapping("/email/{id}")
    void updateEmail(@PathVariable Integer id, @RequestParam String email);
}
