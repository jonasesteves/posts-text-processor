package com.jonasesteves.posts.textprocessor.infrastructure.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return QueueBuilder.durable(QUEUE_PROCESS_POST).build();
        // TODO: Criar a queue dead letter para onde vai ser direcionado caso dê erro nesta queue.
    }

    @Bean
    public Binding bindingPostProcess() {
        return BindingBuilder.bind(queuePostProcess()).to(exchange());
    }

    private FanoutExchange exchange() {
        return ExchangeBuilder.fanoutExchange("post-text-processor.post-received.v1.e").build();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
