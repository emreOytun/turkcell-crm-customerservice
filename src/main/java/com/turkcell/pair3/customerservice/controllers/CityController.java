package com.turkcell.pair3.customerservice.controllers;

import com.turkcell.pair3.customerservice.services.abstracts.CityService;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityAddRequest;
import com.turkcell.pair3.customerservice.services.dtos.requests.CityUpdateRequest;
import com.turkcell.pair3.customerservice.services.dtos.responses.CityUpdateResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
@Validated
public class CityController {
    private final CityService cityService;

    @PostMapping
    public Integer saveCity(@RequestBody @Valid CityAddRequest request){
        return cityService.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Integer id){
        cityService.delete(id);
    }

    @PutMapping("/{id}")
    public CityUpdateResponse update(@PathVariable @NotNull Integer id, @RequestBody @Valid CityUpdateRequest request){
        return cityService.update(id, request);
    }
}
