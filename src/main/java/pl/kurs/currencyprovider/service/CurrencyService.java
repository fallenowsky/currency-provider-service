package pl.kurs.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.kurs.currencyprovider.client.NbpClient;
import pl.kurs.currencyprovider.model.CurrencyRatesTable;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final RabbitTemplate rabbitTemplate;
    private final NbpClient nbpClient;


    @Scheduled(fixedRate = 300_000)
    public void getExchangeRates() {
        nbpClient.getCurrencyRateData().stream()
                .map(CurrencyRatesTable::getRates)
                .flatMap(Collection::stream)
                .map(Object::toString)
                .forEach(rate -> rabbitTemplate.convertAndSend("rates", rate));
    }

}
