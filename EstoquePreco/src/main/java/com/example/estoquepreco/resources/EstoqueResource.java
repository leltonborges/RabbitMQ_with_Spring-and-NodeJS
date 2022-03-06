package com.example.estoquepreco.resources;

import com.example.estoquepreco.service.RabbitMQSendMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.lib.amqp.dtos.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("estoque")
public class EstoqueResource {
    @Value("${amqp.queue.estoque}")
    private String queueEstoque;

    @Autowired
    private RabbitMQSendMessage sendMessage;
    @Autowired
    private ObjectMapper objectMapper;

    @PutMapping
    public ResponseEntity updateEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        try {
            log.info("estoque: {}", estoqueDTO);
            String objJson = objectMapper.writeValueAsString(estoqueDTO);
            this.sendMessage.sendMessage(queueEstoque, objJson);
            return ResponseEntity.ok().build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
