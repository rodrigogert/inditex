package com.inditex.brands.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.brands.adapter.controller.model.PriceRestModel;
import com.inditex.brands.application.exception.PriceNotFoundException;
import com.inditex.brands.application.port.in.GetPriceQuery;
import com.inditex.brands.domain.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetPriceQuery getPriceQuery;

    @Test
    @DisplayName("when request is correct")
    void getResponse200() throws Exception {

        Price price = Price.builder().brandId(1).productId(2).build();

        when(getPriceQuery.execute(any(), any(), any())).thenReturn(price);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", "2020-06-14T18:30:01.000-00:00");

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/brands/1/products/2/prices")
                .queryParams(params)
                .headers(new HttpHeaders()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(PriceRestModel.fromDomain(price))));

        verify(getPriceQuery, times(1)).execute(any(), any(), any());
    }

    @Test
    @DisplayName("when request is not correct")
    void getResponse404() throws Exception {

        when(getPriceQuery.execute(any(), any(), any())).thenThrow(new PriceNotFoundException("test"));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", "2020-06-14T18:30:01.000-00:00");

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/brands/1/products/2/prices")
                .queryParams(params)
                .headers(new HttpHeaders()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(getPriceQuery, times(1)).execute(any(), any(), any());
    }
}
