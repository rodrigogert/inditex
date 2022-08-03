package com.inditex.brands.adapter.controller;

import com.inditex.brands.adapter.controller.model.PriceRestModel;
import com.inditex.brands.application.port.in.GetPriceQuery;
import com.inditex.brands.domain.Price;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PriceController {

    private final GetPriceQuery getPriceQuery;

    public PriceController(GetPriceQuery getPriceQuery) {
        this.getPriceQuery = getPriceQuery;
    }

    @GetMapping("/brands/{brandId}/products/{productId}/prices")
    public ResponseEntity<PriceRestModel> get(
            @PathVariable("brandId") Integer brandId,
            @PathVariable("productId") Integer productId,
            @RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        Price price = this.getPriceQuery.execute(brandId, productId, date);
        return new ResponseEntity<>(PriceRestModel.fromDomain(price), HttpStatus.OK);
    }
}
