package com.example.estoquepreco.resources;

import com.example.estoquepreco.service.RabbitMQSendMessage;
import lombok.extern.slf4j.Slf4j;
import org.lib.amqp.dtos.PrecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("preco")
public class PrecoResource {
    @Autowired
    private RabbitMQSendMessage sendMessage;

    @Value("${amqp.queue.price}")
    private String queuePrice;

    @PutMapping
    public ResponseEntity updatePreco(@RequestBody PrecoDTO precoDTO){
        log.info("preco: {}", precoDTO);
        this.sendMessage.sendMessage(queuePrice, precoDTO);
        return ResponseEntity.ok().build();
    }
}
