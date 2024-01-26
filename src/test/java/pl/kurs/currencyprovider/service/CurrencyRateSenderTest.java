//package pl.kurs.currencyprovider.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.test.context.ActiveProfiles;
//import pl.kurs.currencyprovider.model.dto.CurrencyRateDto;
//
//import java.math.BigDecimal;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@ActiveProfiles("test")
//@SpringBootTest
//class CurrencyRateSenderTest {
//
//    @MockBean
//    private AmqpTemplate amqpTemplate;
//
//    @Autowired
//    private CurrencyRateSender service;
////
////    @Value("${spring.rabbitmq.queueName}")
////    private String queueName;
//
//    private CurrencyRateDto dollar;
//
//
//    @BeforeEach
//    public void init() {
//        service = new CurrencyRateSender(amqpTemplate);
//        dollar = CurrencyRateDto.builder()
//                .code("USD")
//                .currency("Dollar")
//                .ask(BigDecimal.valueOf(4.11))
//                .bid(BigDecimal.valueOf(4.21))
//                .build();
//    }
//
//    @Test
//    public void testSend_HappyPath_ResultsInMethodCallWithDesiredArgumentType() {
//        CurrencyRateSender currencyRateSender = mock(CurrencyRateSender.class);
//        doNothing().when(currencyRateSender).send(isA(CurrencyRateDto.class));
//
//        currencyRateSender.send(dollar);
//
//        verify(currencyRateSender, times(1)).send(eq(dollar));
//    }
//
//    @Test
//    public void testSend_HappyPath_ResultsInMockMethodInvocation() {
//        service.send(dollar);
//
//        verify(amqpTemplate, times(1)).convertAndSend(anyString(), eq(dollar));
//    }
//
//
//}