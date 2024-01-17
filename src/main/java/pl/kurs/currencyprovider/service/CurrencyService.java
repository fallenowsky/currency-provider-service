package pl.kurs.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.currencyprovider.client.NbpFeignClient;
import pl.kurs.currencyprovider.model.CurrencyRatesTable;
import pl.kurs.currencyprovider.model.dto.CurrencyRateDto;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final NbpFeignClient nbpClient;
    private final CurrencyRateSender currencyRateSender;


    public void getExchangeRates() {
        nbpClient.getCurrencyRates().stream()
                .map(CurrencyRatesTable::getRates)
                .flatMap(Collection::stream)
                .map(CurrencyRateDto::mapToDto)
                .forEach(currencyRateSender::send);
    }

}
