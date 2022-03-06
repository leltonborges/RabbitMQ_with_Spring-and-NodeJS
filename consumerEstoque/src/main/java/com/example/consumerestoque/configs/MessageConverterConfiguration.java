package com.example.consumerestoque.configs;

import com.example.consumerestoque.exceptions.CustomErrorStrategy;
import com.example.consumerestoque.exceptions.StandardErroHandler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

@Configuration
public class MessageConverterConfiguration {

    @Bean(name = "messageConverterBean")
    public MessageConverter messageConverter(){
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean(name = "customRabbitListenerContainer")
    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListener(ConnectionFactory connectionFactory){
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setPrefetchCount(4);
//        factory.setGlobalQos(true); // Habilitar de forma global por canal

        factory.setConsumersPerQueue(20); //quantas Tread por filas | quantos consumidores por fila

//        factory.setErrorHandler(new StandardErroHandler()); //Forma 1 de tratar error
//        factory.setErrorHandler(errorHandler()); //Forma 2 de tratar error

        return factory;
    }

    @Bean(name = "customDefaultExceptionStrategy")
    public FatalExceptionStrategy defaultExceptionStrategy(){
        return new CustomErrorStrategy();
    }

    @Bean(name = "customErrorHandler")
    public ErrorHandler errorHandler(){
        return new ConditionalRejectingErrorHandler(defaultExceptionStrategy());
    }
}
