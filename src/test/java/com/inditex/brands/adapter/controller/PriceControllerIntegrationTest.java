package com.inditex.brands.adapter.controller;

import com.inditex.brands.adapter.controller.model.PriceRestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String createURLWithParams(Integer brandId, Integer productId, String dateStr) {
        StringBuilder builder = new StringBuilder("http://localhost:").append(port)
                .append("/api/v1/brands/").append(brandId).append("/products/")
                .append(productId).append("/prices?date=").append(dateStr);
        return builder.toString();
    }

    /**
     *
     * Data test:
     * BRAND_ID      START_DATE              END_DATE         PRICE_LIST  PRODUCT_ID    PRIORITY  PRICE    CURR
     * --------------------------------------------------------------------------------------------------------
     * 1         2020-06-14-00.00.00     2020-12-31-23.59.59    1             35455           0   35.50     EUR
     * 1         2020-06-14-15.00.00     2020-06-14-18.30.00    2             35455           1   25.45     EUR
     * 1         2020-06-15-00.00.00     2020-06-15-11.00.00    3             35455           1   30.50     EUR
     * 1         2020-06-15-16.00.00     2020-12-31-23.59.59    4             35455           1   38.95     EUR
     */

    /**
     * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void integrationTestOne() {
        String url = this.createURLWithParams(1, 35455, "2020-06-14T10:00:00.000-00:00");
        PriceRestModel response = this.restTemplate.getForObject(url, PriceRestModel.class);
        Double expectedPrice = 35.50;
        Integer priceListId = 1;
        Assertions.assertEquals(response.getPrice(), expectedPrice);
        Assertions.assertEquals(response.getPriceListId(), priceListId);
    }

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA).
     * Se espera un precio final 25.45 con la tarifa con ID 2.
     */
    @Test
    public void integrationTestTwo() {
        String url = this.createURLWithParams(1, 35455, "2020-06-14T16:00:00.000-00:00");
        PriceRestModel response = this.restTemplate.getForObject(url, PriceRestModel.class);
        Double expectedPrice = 25.45;
        Integer priceListId = 2;
        Assertions.assertEquals(response.getPrice(), expectedPrice);
        Assertions.assertEquals(response.getPriceListId(), priceListId);
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA).
     * Se espera un precio final 35.50 con la tarifa con ID 1.
     */
    @Test
    public void integrationTestThree() {
        String url = this.createURLWithParams(1, 35455, "2020-06-14T21:00:00.000-00:00");
        PriceRestModel response = this.restTemplate.getForObject(url, PriceRestModel.class);
        Double expectedPrice = 35.50;
        Integer priceListId = 1;
        Assertions.assertEquals(response.getPrice(), expectedPrice);
        Assertions.assertEquals(response.getPriceListId(), priceListId);
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA).
     * Se espera un precio final 30.50 con la tarifa con ID 3.
     */
    @Test
    public void integrationTestFour() {
        String url = this.createURLWithParams(1, 35455, "2020-06-15T10:00:00.000-00:00");
        PriceRestModel response = this.restTemplate.getForObject(url, PriceRestModel.class);
        Double expectedPrice = 30.50;
        Integer priceListId = 3;
        Assertions.assertEquals(response.getPrice(), expectedPrice);
        Assertions.assertEquals(response.getPriceListId(), priceListId);
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA).
     * Se espera un precio final 38.95 con la tarifa con ID 4.
     */
    @Test
    public void integrationTestFive() {
        String url = this.createURLWithParams(1, 35455, "2020-06-16T21:00:00.000-00:00");
        PriceRestModel response = this.restTemplate.getForObject(url, PriceRestModel.class);
        Double expectedPrice = 38.95;
        Integer priceListId = 4;
        Assertions.assertEquals(response.getPrice(), expectedPrice);
        Assertions.assertEquals(response.getPriceListId(), priceListId);
    }

}
