package com.inditex.brands.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Price {
    Integer brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer priceListId;
    Integer productId;
    Integer priority;
    Double price;
    String currency;
}
