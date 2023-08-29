package com.hotelalura.hospede.RabbitMQ.Consumer.Exceptions;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;

public class ErrorHandlerTreatment implements org.springframework.util.ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        String queueName = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
        System.out.println(queueName);

        String message = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
        System.out.println(message);

        System.out.println(t.getCause().getMessage());

        //Log on ElasticSearch
        //Log on Cloud Watch (AWS)

        throw new AmqpRejectAndDontRequeueException("Should not return to the queue");
    }
}
