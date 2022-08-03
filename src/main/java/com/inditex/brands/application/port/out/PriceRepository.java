package com.inditex.brands.application.port.out;

import com.inditex.brands.domain.Price;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> getByFilters(Integer brandId, Integer productId, LocalDateTime date);
}
