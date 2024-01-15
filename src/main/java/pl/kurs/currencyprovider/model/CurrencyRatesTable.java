package pl.kurs.currencyprovider.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CurrencyRatesTable {

    private String table;
    private String no;
    private String tradingDate;
    private String effectiveDate;
    private List<CurrencyRate> rates = new ArrayList<>();
}
