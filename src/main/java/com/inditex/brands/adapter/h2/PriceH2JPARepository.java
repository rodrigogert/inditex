package com.inditex.brands.adapter.h2;

import com.inditex.brands.adapter.h2.model.PriceH2Model;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceH2JPARepository extends JpaRepository<PriceH2Model, Integer> {
    @Query("SELECT price FROM PriceH2Model price "
            + "WHERE price.brand.id = :brandId "
            + "AND price.product.id = :productId "
            + "AND :applicationDate BETWEEN price.startDate AND price.endDate")
    List<PriceH2Model> getByFilters(Integer brandId, Integer productId, LocalDateTime applicationDate);
}
