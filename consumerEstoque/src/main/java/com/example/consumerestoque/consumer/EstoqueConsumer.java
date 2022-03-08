package com.example.consumerestoque.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.lib.amqp.dtos.EstoqueDTO;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EstoqueConsumer {

    @RabbitListener(queues = "queue.estoque")
    private void consumerMessage(Message message, @Payload EstoqueDTO estoqueDTO) throws InterruptedException {
//        log.info("message: {}", message);
        log.info("process: {}", estoqueDTO);
//        throw new IllegalArgumentException(estoqueDTO.toString());
        Thread.sleep(5000);
    }
}
