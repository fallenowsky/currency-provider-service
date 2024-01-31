package pl.kurs.currencyprovider.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.kurs.currencyprovider.model.CurrencyRateDto;
import pl.kurs.currencyprovider.model.CurrencyRatesTableDto;

import java.math.BigDecimal;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@WireMockTest(httpPort = 8090)
class NbpFeignClientTest {

    @Value("${app.client.baseUrl}")
    private String baseUrl;

    @Value("${app.client.detailedUrl}")
    private String detailedUrl;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NbpFeignClient nbpFeignClient;

    private CurrencyRatesTableDto currencyTable;

    @BeforeEach
    public void setUp() {
        CurrencyRateDto dollar = CurrencyRateDto.builder()
                .currency("US Dollar")
                .code("USD")
                .ask(BigDecimal.valueOf(3.95))
                .bid(BigDecimal.valueOf(3.85))
                .build();
        CurrencyRateDto frank = CurrencyRateDto.builder()
                .currency("Frank")
                .code("CHF")
                .ask(BigDecimal.valueOf(4.75))
                .bid(BigDecimal.valueOf(4.60))
                .build();
        currencyTable = CurrencyRatesTableDto.builder()
                .table("C")
                .no("019/C/NBP/2024")
                .tradingDate("2024-01-31")
                .effectiveDate("2024-01-30")
                .rates(List.of(dollar, frank))
                .build();
    }


    @Test
    public void testGetCurrencyRates_HappyPath_ResultsInCorrectResponse() throws JsonProcessingException {
        String body = objectMapper.writeValueAsString(currencyTable);

        stubFor(get(baseUrl + detailedUrl).willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(body)
        ));


        List<CurrencyRatesTableDto> ratesTable = nbpFeignClient.getCurrencyRates();

        assertThat(ratesTable)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        String returnedJson = objectMapper.writeValueAsString(ratesTable.get(0));
        assertThat(returnedJson).isEqualTo(body);
    }

}