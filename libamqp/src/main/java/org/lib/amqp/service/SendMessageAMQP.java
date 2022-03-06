package org.lib.amqp.service;

@FunctionalInterface
public interface SendMessageAMQP {
    void sendMessage(String nameQueue, Object message);
}
