package com.inditex.brands.application.usecase;

import com.inditex.brands.application.exception.PriceNotFoundException;
import com.inditex.brands.application.port.in.GetPriceQuery;
import com.inditex.brands.application.port.out.PriceRepository;
import com.inditex.brands.domain.Price;
import java.time.LocalDateTime;
import java.util.Comparator;
import org.springframework.stereotype.Component;

@Component
public class GetPriceUseCase implements GetPriceQuery {

    private final PriceRepository priceRepository;

    public GetPriceUseCase(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price execute(Integer brandId, Integer productId, LocalDateTime date) {
        return priceRepository.getByFilters(brandId, productId, date)
                .stream().max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }
}
