package com.gk.learning.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @author Kasturi's
 *
 */
@Configuration
public class RabbitConfig {

	@Value("${gk.rabbit.queueName}")
	public String queueName;

	@Value("${gk.rabbit.exchange}")
	public String exchange;

	@Value("${gk.rabbit.routingKey}")
	private String routingKey;

	@Bean
	 Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	 DirectExchange directExchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	 Binding binding(Queue queue, DirectExchange exchange) {

		return BindingBuilder.bind(queue).to(exchange).with(routingKey);

	}

	@Bean
	 MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}
}
