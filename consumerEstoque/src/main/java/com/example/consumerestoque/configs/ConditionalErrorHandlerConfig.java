package com.example.consumerestoque.configs;

import com.example.consumerestoque.handlerException.CustomErrorHandler;
import com.example.consumerestoque.handlerException.CustomFatalExceptionStrategy;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

@Configuration
public class ConditionalErrorHandlerConfig {

    @Bean(name = "customListenerContainerFactory")
    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setPrefetchCount(2);
//        factory.setErrorHandler(new CustomErrorHandler());

        factory.setErrorHandler(errorHandler());
        return factory;
    }

    @Bean
    public FatalExceptionStrategy fatalExceptionStrategy(){
        return new CustomFatalExceptionStrategy();
    }

    @Bean
    public ErrorHandler errorHandler(){
        return new ConditionalRejectingErrorHandler(fatalExceptionStrategy());
    }

}
