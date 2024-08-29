package com.turkcell.pair3.customerservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.turkcell.pair3.core.configuration.feign.FeignClientConfiguration;
import java.util.Date;
import java.util.List;

@FeignClient(name="orderservice", configuration = FeignClientConfiguration.class)
public interface OrderServiceClient
{
    @GetMapping("/api/orders")
    int getCustomerIdByOrderId(@RequestParam("orderId") String orderId);

    @GetMapping("/getOrderIds")
    List<Date> getOrderIdsByBillAccountId(@RequestParam("values") List<Integer> billAccountIdList);
}
