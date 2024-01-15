package pl.kurs.currencyprovider.config;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kurs.currencyprovider.client.NbpFeignClient;

@Configuration
public class NbpFeignClientConfiguration {

    @Value("${exchange.api.base-url}")
    private String baseUrl;

    @Bean
    public NbpFeignClient exchangeFeignClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(NbpFeignClient.class))
                .logLevel(Logger.Level.FULL)
                .target(NbpFeignClient.class, baseUrl);
    }
}

