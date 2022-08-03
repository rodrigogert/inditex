package com.inditex.brands.application.usecase;

import com.inditex.brands.application.port.out.PriceRepository;
import com.inditex.brands.domain.Price;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPriceUseCaseTest {

    @Mock
    PriceRepository priceRepository;

    @InjectMocks
    GetPriceUseCase getPriceUseCase;

    @Test
    @DisplayName("Cuando obtenemos las coordenadas OK")
    void cuandoObtenemosLasCoordenadasCorrectamente() {

        Price price1 = Price.builder().brandId(1).productId(2).priority(1).build();
        Price price2 = Price.builder().brandId(1).productId(2).priority(2).build();
        when(priceRepository.getByFilters(1, 2, LocalDate.now().atStartOfDay()))
                .thenReturn(List.of(price1, price2));

        Price result = getPriceUseCase.execute(1, 2, LocalDate.now().atStartOfDay());
        Assertions.assertEquals(price2, result);

    }
}
