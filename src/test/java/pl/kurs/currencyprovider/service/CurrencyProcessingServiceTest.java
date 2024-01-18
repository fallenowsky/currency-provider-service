package pl.kurs.currencyprovider.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class CurrencyProcessingServiceTest {

    @MockBean
    private CurrencyService currencyService;


    @Test
    public void testProcessCurrencySend_ResultsInTwoMockInteractions() {
        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() ->
                verify(currencyService, times(2)).getExchangeRates()
        );
    }

}