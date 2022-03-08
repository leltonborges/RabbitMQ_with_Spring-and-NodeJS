package com.example.consumerestoque.handlerException;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;

public class CustomFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {
    @Override
    public boolean isFatal(Throwable t) {
        return t.getCause() instanceof JsonParseException;
    }
}
