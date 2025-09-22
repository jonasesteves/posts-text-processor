package com.jonasesteves.posts.textprocessor.infrastructure.rabbitmq;

import com.jonasesteves.posts.textprocessor.api.domain.model.PostData;
import com.jonasesteves.posts.textprocessor.api.domain.model.PostDataResponse;
import com.jonasesteves.posts.textprocessor.api.domain.service.PostProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.jonasesteves.posts.textprocessor.infrastructure.rabbitmq.RabbitMQConfig.QUEUE_PROCESS_POST;

@Component
public class RabbitMQListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);
    private final PostProcessorService postProcessorService;
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQListener(PostProcessorService postProcessorService, RabbitTemplate rabbitTemplate) {
        this.postProcessorService = postProcessorService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = QUEUE_PROCESS_POST)
    public void handle(@Payload PostData postData) {
        PostDataResponse postDataResponse = postProcessorService.calculateValue(postData);
        rabbitTemplate.convertAndSend("posts-service.post-processing-result.v1.q", postDataResponse);
        logger.info("Calculated data was sent: {}",  postDataResponse);
    }
}
