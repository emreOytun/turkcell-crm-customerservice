package com.turkcell.pair3.customerservice.clients;

import com.turkcell.pair3.core.configuration.feign.FeignClientConfiguration;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@FeignClient(name="orderservice", configuration = FeignClientConfiguration.class)
@Retry(name = "feignclient")
public interface OrderServiceClient
{
    @GetMapping("/api/orders")
    int getCustomerIdByOrderId(@RequestParam("orderId") String orderId);

    @GetMapping("/getOrderIds")
    List<Date> getOrderIdsByBillAccountId(@RequestParam("values") List<Integer> billAccountIdList);
}
