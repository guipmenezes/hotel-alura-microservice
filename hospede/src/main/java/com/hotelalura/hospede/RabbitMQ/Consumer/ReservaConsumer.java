package com.hotelalura.hospede.RabbitMQ.Consumer;

import Constants.RabbitMQConstants;
import DTO.ReservasRegistrationRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReservaConsumer {

    @RabbitListener(queues = RabbitMQConstants.RESERVATION_QUEUE)
    public void consumer(ReservasRegistrationRequest request) {
        System.out.println(request.roomType);
        System.out.println(request.formaPagamento);
        System.out.println(request.valor);
        System.out.println(request.dataEntrada);
        System.out.println(request.dataSaida);
        System.out.println("-----------------------");
    }
}
