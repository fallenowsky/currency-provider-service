package pl.kurs.currencyprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.kurs.currencyprovider.properties.RabbitProperties;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableConfigurationProperties(RabbitProperties.class)
public class CurrencyProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyProviderApplication.class, args);
	}

}
