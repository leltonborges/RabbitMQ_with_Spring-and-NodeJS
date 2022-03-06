package com.example.consumerestoque.consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.lib.amqp.dtos.EstoqueDTO;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EstoqueConsumer {

    @RabbitListener(queues = "queue.estoque", containerFactory = "customRabbitListenerContainer")
    private void consumerMessage(@Payload EstoqueDTO estoqueDTO){
        log.info("Process: {}", estoqueDTO);
//        throw new IllegalArgumentException("invalid");
    }
}
