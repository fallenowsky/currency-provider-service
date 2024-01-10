package pl.kurs.currencyprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyProviderApplication.class, args);
	}

}
