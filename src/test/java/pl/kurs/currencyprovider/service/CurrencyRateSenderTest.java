package pl.kurs.currencyprovider.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import pl.kurs.currencyprovider.model.CurrencyRateDto;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class CurrencyRateSenderTest {

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CurrencyRateSender service;

    private CurrencyRateDto dollar;


    @BeforeEach
    public void init() {
        dollar = CurrencyRateDto.builder()
                .code("USD")
                .currency("Dollar")
                .ask(BigDecimal.valueOf(4.11))
                .bid(BigDecimal.valueOf(4.21))
                .build();
    }

    @Test
    public void testSend_HappyPath_ResultsInMethodCallWithDesiredArgumentType() {
        CurrencyRateSender currencyRateSender = mock(CurrencyRateSender.class);
        doNothing().when(currencyRateSender).send(isA(CurrencyRateDto.class));

        currencyRateSender.send(dollar);

        verify(currencyRateSender, times(1)).send(eq(dollar));
    }

    @Test
    public void testSend_HappyPath_ResultsInMockMethodInvocation() {
        service.send(dollar);

        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), eq(dollar));
    }

}