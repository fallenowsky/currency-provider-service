package pl.kurs.currencyprovider.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kurs.currencyprovider.properties.RabbitProperties;

@Configuration
@RequiredArgsConstructor
public class RabbitConfiguration {

    private final RabbitProperties rabbitProperties;

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queue() {
        return new Queue(rabbitProperties.getQueueName(), true);
    }
}

