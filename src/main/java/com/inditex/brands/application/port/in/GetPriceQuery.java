package com.inditex.brands.application.port.in;

import com.inditex.brands.domain.Price;
import java.time.LocalDateTime;

public interface GetPriceQuery {
    Price execute(Integer brandId, Integer productId, LocalDateTime date);
}
