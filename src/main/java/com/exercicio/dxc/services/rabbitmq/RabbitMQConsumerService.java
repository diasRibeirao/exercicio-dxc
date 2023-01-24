package com.exercicio.dxc.services.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {

	@RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message){
        System.out.println(String.format("Recebendo mensagem: %s", message));
    }
	
}
