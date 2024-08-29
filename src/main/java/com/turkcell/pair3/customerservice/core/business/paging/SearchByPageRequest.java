package com.turkcell.pair3.customerservice.core.business.paging;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchByPageRequest {
    @NotNull
    @Min(1)
    private Integer pageNo;

    @NotNull
    @Min(0)
    @Max(500)
    private Integer pageSize;
}
