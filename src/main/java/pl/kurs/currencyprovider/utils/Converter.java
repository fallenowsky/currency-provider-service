package pl.kurs.currencyprovider.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kurs.currencyprovider.model.CurrencyRate;

@Component
@RequiredArgsConstructor
public class Converter {

    private final ObjectMapper objectMapper;

    public String toJson(CurrencyRate rate) {
        try {
            return objectMapper.writeValueAsString(rate);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
