package com.example.consumerestoque.exceptions;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class StandardErroHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        ListenerExecutionFailedException exception = (ListenerExecutionFailedException) t;
        String consumerQueue = exception.getFailedMessage().getMessageProperties().getConsumerQueue();
        String message = new String(exception.getFailedMessage().getBody());
        String causeError = exception.getCause().getMessage();

        System.out.println(consumerQueue);
        System.out.println(message);
        System.out.println(causeError);

        // Logar no ElasticSearch
        // Logar no Cloud Watch (AWS)
        // ...

        throw new AmqpRejectAndDontRequeueException("Not return queue");

    }
}
