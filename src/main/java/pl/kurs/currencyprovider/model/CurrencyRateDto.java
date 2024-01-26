package pl.kurs.currencyprovider.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CurrencyRateDto {

    private String currency;
    private String code;
    private BigDecimal bid;
    private BigDecimal ask;
}
