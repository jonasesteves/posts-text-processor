package com.jonasesteves.posts.textprocessor.infrastructure.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    private static final String QUEUE_ROOT_NAME = "posts-text-processor.process-post.v1";
    public static final String QUEUE_PROCESS_POST = QUEUE_ROOT_NAME + ".q";
    public static final String DEAD_LETTER_QUEUE_PROCESS_POST = QUEUE_ROOT_NAME + ".dlq";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue queuePostProcess() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "");
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_PROCESS_POST);
        return QueueBuilder.durable(QUEUE_PROCESS_POST).withArguments(args).build();
    }

    @Bean
    public Queue deadLetterQueueProcessPost() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE_PROCESS_POST).build();
    }

    @Bean
    public Binding bindingPostProcess() {
        return BindingBuilder.bind(queuePostProcess()).to(exchange());
    }

    private FanoutExchange exchange() {
        return ExchangeBuilder.fanoutExchange("posts-service.post-created.v1.e").build();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
