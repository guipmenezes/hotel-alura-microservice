package com.hotelalura.hospede.RabbitMQ.Consumer.Exceptions;

import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler.DefaultExceptionStrategy;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;

public class CustomErrorStrategy extends DefaultExceptionStrategy {

    @Override
    public boolean isFatal(Throwable t) {
        System.out.println(new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody()));

        return t.getCause() instanceof IllegalArgumentException;
    }

}
