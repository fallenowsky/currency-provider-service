package pl.kurs.currencyprovider.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CurrencyRatesTableDto {

    private String table;
    private String no;
    private String tradingDate;
    private String effectiveDate;
    private List<CurrencyRateDto> rates;
}
