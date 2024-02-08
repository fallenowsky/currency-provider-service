package pl.kurs.currencyprovider.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest
@ActiveProfiles("test")
//@Testcontainers
class CurrencyProcessingServiceTest {

//    private static final String RABBIT_IMAGE_NAME = "rabbitmq:3.9-management-alpine";

    @MockBean
    private CurrencyService currencyService;

//    @Container
//    @ServiceConnection
//    private static RabbitMQContainer rabbitmq = new RabbitMQContainer(RABBIT_IMAGE_NAME);
//
//
//    @Test
//    public void connectionEstablished() {
//        assertTrue(rabbitmq.isCreated());
//        assertTrue(rabbitmq.isRunning());
//    }

    @Test
    public void testProcessCurrencySend_ResultsInTwoMockInteractions() {
        await()
                .atMost(2, TimeUnit.SECONDS)
                .untilAsserted(() -> verify(currencyService, times(2)).getExchangeRates());

    }

}