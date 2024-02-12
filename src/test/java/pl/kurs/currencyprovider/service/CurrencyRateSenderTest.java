package pl.kurs.currencyprovider.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kurs.currencyprovider.model.CurrencyRateDto;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class CurrencyRateSenderTest {

    private static final String RABBIT_IMAGE = "rabbitmq:3.9-management-alpine";

    @Value("${spring.rabbitmq.queue-name}")
    private String queueName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CurrencyRateSender service;

    @Container
    @ServiceConnection
    private static RabbitMQContainer rabbitmq = new RabbitMQContainer(RABBIT_IMAGE);

    private CurrencyRateDto dollar;


    @BeforeEach
    public void init() {
        dollar = new CurrencyRateDto()
                .setCode("USD")
                .setCurrency("Dollar")
                .setAsk(BigDecimal.valueOf(4.11))
                .setBid(BigDecimal.valueOf(4.21));
    }


    @Test
    public void testRabbitMqContainer_ConnectionEstablished() {
        assertTrue(rabbitmq.isCreated());
        assertTrue(rabbitmq.isRunning());
    }

    @Test
    public void testSend_ResultsInDatBeingSentToRabbitQueue() {
        service.send(dollar);

        Object received = rabbitTemplate.receiveAndConvert(queueName, 500);
        assertTrue(received instanceof CurrencyRateDto);
        assertThat((CurrencyRateDto) received)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(dollar);
    }

}