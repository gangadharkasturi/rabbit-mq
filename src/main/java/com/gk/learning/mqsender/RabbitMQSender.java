package com.gk.learning.mqsender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gk.learning.model.Employee;

/**
 * 
 * @author Kasturi's
 *
 */
@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${gk.rabbit.exchange}")
	public String exchange;

	@Value("${gk.rabbit.routingKey}")
	private String routingKey;

	public void sendMessageToQueue(Employee employee) {

		amqpTemplate.convertAndSend(exchange, routingKey, employee);
		System.out.println("Message Received at Rabbit MQ : \n " + employee);
	}

}
