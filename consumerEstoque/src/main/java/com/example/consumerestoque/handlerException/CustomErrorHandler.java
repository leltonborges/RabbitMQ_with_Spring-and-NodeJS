package com.example.consumerestoque.handlerException;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class CustomErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        ListenerExecutionFailedException exception = (ListenerExecutionFailedException) t;
        throw new AmqpRejectAndDontRequeueException(exception.getFailedMessages().toString());
    }
}
