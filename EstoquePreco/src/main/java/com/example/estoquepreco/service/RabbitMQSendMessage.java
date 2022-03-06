package com.example.estoquepreco.service;

import org.lib.amqp.service.SendMessageAMQP;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSendMessage implements SendMessageAMQP {
    @Autowired
    private RabbitTemplate template;

    @Override
    public void sendMessage(String nameQueue, Object message) {
        this.template.convertAndSend(nameQueue, message);
    }
}
