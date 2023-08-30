package com.hotelalura.hospede.RabbitMQ.Consumer;

import Constants.RabbitMQConstants;
import DTO.ReservasRegistrationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReservaConsumer {

    @RabbitListener(queues = RabbitMQConstants.RESERVATION_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public void consumer(String message) throws JsonProcessingException, InterruptedException {
        ReservasRegistrationRequest reservaDto = new ObjectMapper().readValue(message, ReservasRegistrationRequest.class);

        System.out.println(reservaDto.roomType);
        System.out.println(reservaDto.formaPagamento);
        System.out.println(reservaDto.valor);
        System.out.println(reservaDto.dataEntrada);
        System.out.println(reservaDto.dataSaida);
        System.out.println("-----------------------");

        // This error was just for Exception Handler testing.
        //throw new IllegalArgumentException("Invalid argument");
    }
}
