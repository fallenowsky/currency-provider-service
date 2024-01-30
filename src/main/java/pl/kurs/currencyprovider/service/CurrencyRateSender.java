package pl.kurs.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kurs.currencyprovider.model.CurrencyRateDto;

@Service
@RequiredArgsConstructor
public class CurrencyRateSender {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queueName}")
    private String queueName;


    public void send(CurrencyRateDto rate) {
        rabbitTemplate.convertAndSend(queueName, rate);
    }
}
