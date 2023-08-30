package com.hotelalura.RabbitMQ.Connection;

import Constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {

    private static final String NAME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding relation(Queue queue, DirectExchange trade) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, trade.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void addConnection() {
        Queue reservationQueue = this.queue(RabbitMQConstants.RESERVATION_QUEUE);
        DirectExchange exchange = this.directExchange();
        Binding relation = this.relation(reservationQueue, exchange);

        this.amqpAdmin.declareQueue(reservationQueue);
        this.amqpAdmin.declareExchange(exchange);
        this.amqpAdmin.declareBinding(relation);
    }
}
