package pl.kurs.currencyprovider.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kurs.currencyprovider.model.CurrencyRatesTableDto;

import java.util.List;

@FeignClient(value = "nbp-client", url = "${app.client.base-url}")
public interface NbpFeignClient {

    @GetMapping("/tables/C")
    List<CurrencyRatesTableDto> getCurrencyRates();

}