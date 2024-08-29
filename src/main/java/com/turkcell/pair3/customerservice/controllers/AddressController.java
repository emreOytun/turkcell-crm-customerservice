package com.turkcell.pair3.customerservice.controllers;

import com.turkcell.pair3.customerservice.services.abstracts.AddressService;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.AddressUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.AddressUpdateResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public Integer save(@RequestBody @Valid AddressAddRequest request) {
        return addressService.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Integer id){
        addressService.delete(id);
    }

    @PutMapping("/{id}")
    public AddressUpdateResponse update(@PathVariable @NotNull Integer id, @RequestBody @Valid AddressUpdateRequest request){
        return addressService.update(id, request);
    }

    @PatchMapping("/{id}")
    public void makeAddressPrimary(@PathVariable Integer id) {
        addressService.makePrimary(id);
    }
}
