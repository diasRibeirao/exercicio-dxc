package com.exercicio.dxc.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.name}")
	private String queue;

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	@Bean
	protected Queue queue() {
		return new Queue(queue);
	}

	@Bean
	protected TopicExchange exchange() {
		return new TopicExchange(exchange);
	}

	@Bean
	protected Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
	}

}