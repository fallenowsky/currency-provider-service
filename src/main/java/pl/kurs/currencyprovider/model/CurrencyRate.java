package pl.kurs.currencyprovider.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyRate {

    private String currency;
    private String code;
    private BigDecimal bid;
    private BigDecimal ask;
}
