package pl.kurs.currencyprovider.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class CurrencyRateDto {

    private String currency;
    private String code;
    private BigDecimal bid;
    private BigDecimal ask;
}
