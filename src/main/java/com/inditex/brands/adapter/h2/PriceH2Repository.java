package com.inditex.brands.adapter.h2;

import com.inditex.brands.adapter.h2.model.PriceH2Model;
import com.inditex.brands.application.port.out.PriceRepository;
import com.inditex.brands.domain.Price;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class PriceH2Repository implements PriceRepository {

    private final PriceH2JPARepository priceH2JPARepository;

    public PriceH2Repository(PriceH2JPARepository priceH2JPARepository) {
        this.priceH2JPARepository = priceH2JPARepository;
    }

    @Override
    public List<Price> getByFilters(Integer brandId, Integer productId, LocalDateTime date) {
        return priceH2JPARepository.getByFilters(brandId, productId, date).stream()
                .map(PriceH2Model::toDomain)
                .collect(Collectors.toList());
    }
}
