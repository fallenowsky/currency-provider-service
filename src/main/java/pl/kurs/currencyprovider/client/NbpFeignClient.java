package pl.kurs.currencyprovider.client;

import feign.RequestLine;
import pl.kurs.currencyprovider.model.CurrencyRatesTable;

import java.util.List;

public interface NbpFeignClient {

    @RequestLine("GET")
    List<CurrencyRatesTable> getCurrencyRates();

}