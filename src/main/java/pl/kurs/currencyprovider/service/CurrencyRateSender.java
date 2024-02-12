package pl.kurs.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pl.kurs.currencyprovider.model.CurrencyRateDto;
import pl.kurs.currencyprovider.properties.RabbitProperties;

@Service
@RequiredArgsConstructor
public class CurrencyRateSender {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    public void send(CurrencyRateDto rate) {
        rabbitTemplate.convertAndSend(rabbitProperties.getQueueName(), rate);
    }
}
