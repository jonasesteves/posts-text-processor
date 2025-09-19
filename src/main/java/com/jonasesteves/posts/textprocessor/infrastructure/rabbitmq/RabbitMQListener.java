package com.jonasesteves.posts.textprocessor.infrastructure.rabbitmq;

import com.jonasesteves.posts.textprocessor.api.domain.model.PostData;
import com.jonasesteves.posts.textprocessor.api.domain.model.PostDataResponse;
import com.jonasesteves.posts.textprocessor.api.domain.service.PostProcessorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.jonasesteves.posts.textprocessor.infrastructure.rabbitmq.RabbitMQConfig.QUEUE_PROCESS_POST;

@Component
public class RabbitMQListener {

    private final PostProcessorService postProcessorService;

    public RabbitMQListener(PostProcessorService postProcessorService) {
        this.postProcessorService = postProcessorService;
    }

    @RabbitListener(queues = QUEUE_PROCESS_POST)
    public void handle(@Payload PostData postData) {
        PostDataResponse postDataResponse = postProcessorService.calculateValue(postData);
        //coloca a resposta na fila
        System.out.println(postDataResponse.calculatedValue());
    }
}
