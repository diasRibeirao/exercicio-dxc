package com.exercicio.dxc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exercicio.dxc.services.rabbitmq.RabbitMQConsumerService;
import com.exercicio.dxc.services.rabbitmq.RabbitMQProducerService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RabbitMQTest {

	@Autowired
	public RabbitMQProducerService producer;
	
	@Autowired
	public RabbitMQConsumerService consumer;

	@Test
	@Order(1)
	@DisplayName("Envia uma mensagem para fila rabbitMQ")
	public void producerTest() throws Exception {

	}

	@Test
	@Order(2)
	@DisplayName("Lê uma mensagem da fila rabbitMQ")
	public void consumerTest() throws Exception {

	}
}
