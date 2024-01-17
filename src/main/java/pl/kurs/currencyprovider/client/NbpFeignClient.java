package pl.kurs.currencyprovider.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kurs.currencyprovider.model.CurrencyRatesTable;

import java.util.List;

@FeignClient(value = "nbp-client", url = "https://api.nbp.pl/api/exchangerates")
public interface NbpFeignClient {

    @GetMapping("/tables/C")
    List<CurrencyRatesTable> getCurrencyRates();

}