package pl.kurs.currencyprovider.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.currencyprovider.client.NbpFeignClient;
import pl.kurs.currencyprovider.model.CurrencyRateDto;
import pl.kurs.currencyprovider.model.CurrencyRatesTableDto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private NbpFeignClient nbpFeignClient;

    @Mock
    private CurrencyRateSender currencyRateSender;

    @InjectMocks
    private CurrencyService service;

    private CurrencyRatesTableDto currencyRatesTable;
    private List<CurrencyRateDto> rates;

    @BeforeEach
    public void init() {
        CurrencyRateDto euro = CurrencyRateDto.builder().currency("EURO").code("EUR")
                .bid(BigDecimal.valueOf(4.31))
                .ask(BigDecimal.valueOf(4.25))
                .build();
        CurrencyRateDto frank = CurrencyRateDto.builder().currency("FRANK").code("FR")
                .bid(BigDecimal.valueOf(4.61))
                .ask(BigDecimal.valueOf(4.55))
                .build();
        rates = Arrays.asList(euro, frank);
        currencyRatesTable = CurrencyRatesTableDto.builder()
                .table("C")
                .no("019/C/NBP/2024")
                .tradingDate("2024-01-25")
                .effectiveDate("2024-01-26")
                .rates(rates)
                .build();
    }

    @Test
    public void testGetExchangeRates_HappyPath_ResultsInMocksMethodsInvocations() {
        when(nbpFeignClient.getCurrencyRates()).thenReturn(List.of(currencyRatesTable));

        service.getExchangeRates();

        verify(nbpFeignClient, times(1)).getCurrencyRates();
        verify(currencyRateSender, times(2)).send(any(CurrencyRateDto.class));
        verifyNoMoreInteractions(nbpFeignClient, currencyRateSender);
    }

    @Test
    public void testGetExchangeRates_RatesListIsEmpty_ResultsInMocksMethodsInvocations() {
        currencyRatesTable = CurrencyRatesTableDto.builder()
                .table("C")
                .no("019/C/NBP/2024")
                .tradingDate("2024-01-25")
                .effectiveDate("2024-01-26")
                .rates(Collections.emptyList())
                .build();
        when(nbpFeignClient.getCurrencyRates()).thenReturn(List.of(currencyRatesTable));

        service.getExchangeRates();

        verify(nbpFeignClient, times(1)).getCurrencyRates();
        verify(currencyRateSender, never()).send(any(CurrencyRateDto.class));
        verifyNoMoreInteractions(nbpFeignClient);
        verifyNoInteractions(currencyRateSender);
    }


    @Test
    public void testGetExchangeRates_RatesTableIsNull_ResultsInMocksMethodsInvocations() {
        when(nbpFeignClient.getCurrencyRates()).thenReturn(null);

        service.getExchangeRates();

        verify(nbpFeignClient, times(1)).getCurrencyRates();
        verify(currencyRateSender, never()).send(any(CurrencyRateDto.class));
        verifyNoMoreInteractions(nbpFeignClient);
        verifyNoInteractions(currencyRateSender);
    }

}