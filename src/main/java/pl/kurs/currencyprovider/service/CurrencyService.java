package pl.kurs.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.currencyprovider.client.NbpFeignClient;
import pl.kurs.currencyprovider.model.CurrencyRatesTableDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final NbpFeignClient nbpClient;
    private final CurrencyRateSender currencyRateSender;


    public void getExchangeRates() {
        Optional.ofNullable(nbpClient.getCurrencyRates())
                .orElse(Collections.emptyList())
                .stream()
                .map(CurrencyRatesTableDto::getRates)
                .flatMap(Collection::stream)
                .forEach(currencyRateSender::send);
    }

}
