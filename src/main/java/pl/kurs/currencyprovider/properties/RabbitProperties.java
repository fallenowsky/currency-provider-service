package pl.kurs.currencyprovider.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class RabbitProperties {

    private String queueName;
}
