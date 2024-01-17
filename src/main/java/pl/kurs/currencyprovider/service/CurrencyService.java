package pl.kurs.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.kurs.currencyprovider.client.NbpFeignClient;
import pl.kurs.currencyprovider.model.CurrencyRatesTable;
import pl.kurs.currencyprovider.utils.Converter;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final AmqpTemplate amqpTemplate;
    private final NbpFeignClient nbpClient;
    private final Converter converter;


    @Scheduled(fixedRate = 300_000)
    public void getExchangeRates() {
        nbpClient.getCurrencyRates().stream()
                .map(CurrencyRatesTable::getRates)
                .flatMap(Collection::stream)
                .map(converter::toJson)
                .forEach(rate -> amqpTemplate.convertAndSend("rates", rate));
    }

}
