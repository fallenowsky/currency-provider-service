package pl.kurs.currencyprovider.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NbpFeignClient nbpFeignClient;

    @Value("${app.client.detailed-url}")
    private String detailedUrl;

    private List<CurrencyRatesTableDto> currencyTablesResponse;

    @BeforeEach
    public void setUp() {
        CurrencyRateDto dollar = new CurrencyRateDto()
                .setCurrency("US Dollar")
                .setCode("USD")
                .setAsk(BigDecimal.valueOf(3.95))
                .setBid(BigDecimal.valueOf(3.85));
        CurrencyRateDto frank = new CurrencyRateDto()
                .setCurrency("Frank")
                .setCode("CHF")
                .setAsk(BigDecimal.valueOf(4.75))
                .setBid(BigDecimal.valueOf(4.60));
        CurrencyRatesTableDto currencyTable = CurrencyRatesTableDto.builder()
                .table("C")
                .no("019/C/NBP/2024")
                .tradingDate("2024-01-31")
                .effectiveDate("2024-01-30")
                .rates(List.of(dollar, frank))
                .build();
        currencyTablesResponse = List.of(currencyTable);
    }


    @Test
    public void testGetCurrencyRates_HappyPath_ResultsInCorrectResponse() throws JsonProcessingException {
        String body = objectMapper.writeValueAsString(currencyTablesResponse);

        stubFor(get(urlEqualTo(detailedUrl)).willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBody(body)
        ));

        List<CurrencyRatesTableDto> ratesTable = nbpFeignClient.getCurrencyRates();

        assertThat(ratesTable)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        String returnedJson = objectMapper.writeValueAsString(ratesTable);
        assertThat(returnedJson).isEqualTo(body);
    }

}