package pl.kurs.currencyprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyProcessingService {

    private final CurrencyService currencyService;

    @Scheduled(cron = "${scheduled.cron.fetchRatePeriod}")
    public void processCurrencySend() {
        currencyService.getExchangeRates();
    }
}
