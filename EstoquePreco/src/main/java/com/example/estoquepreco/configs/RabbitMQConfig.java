package com.example.estoquepreco.configs;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConfig {

    @Value("${amqp.exchange}")
    private String nameExchange;
    @Value("${amqp.queue.price}")
    private String queuePrice;
    @Value("${amqp.queue.estoque}")
    private String queueEstoque;

    private AmqpAdmin amqpAdmin;

    @Autowired
    public RabbitMQConfig(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String nameQueue){
        return new Queue(nameQueue, true, false, false);
    }

    private DirectExchange directExchange(){
        return new DirectExchange(nameExchange);
    }

    private Binding binding(Queue queue, DirectExchange exchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void addQueue(){
        Queue queueEstoque = this.queue(this.queueEstoque);
        Queue queuePrice = this.queue(this.queuePrice);

        DirectExchange exchange = this.directExchange();
        Binding bindingEstoque = this.binding(queueEstoque, exchange);
        Binding bindingPrice = this.binding(queuePrice, exchange);

        this.amqpAdmin.declareQueue(queueEstoque);
        this.amqpAdmin.declareQueue(queuePrice);
        this.amqpAdmin.declareExchange(exchange);
        this.amqpAdmin.declareBinding(bindingEstoque);
    }
}

