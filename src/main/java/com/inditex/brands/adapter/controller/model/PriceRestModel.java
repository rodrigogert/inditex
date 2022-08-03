package com.inditex.brands.adapter.controller.model;

import com.inditex.brands.domain.Price;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PriceRestModel {
    Integer brandId;
    Integer productId;
    Integer priceListId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Double price;
    String currency;

    public static PriceRestModel fromDomain(Price price) {
        return PriceRestModel.builder()
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .priceListId(price.getPriceListId())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice())
                .currency(price.getCurrency())
                .build();
    }
}
