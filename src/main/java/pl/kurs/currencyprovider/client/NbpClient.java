package pl.kurs.currencyprovider.client;

import pl.kurs.currencyprovider.model.CurrencyRatesTable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(value = "pl.kurs.currencyprovider.client.NbpClient", url = "http://api.nbp.pl/api/exchangerates")
public interface NbpClient {

    @GetMapping("/tables/C/")
    List<CurrencyRatesTable> getCurrencyRateData();

}