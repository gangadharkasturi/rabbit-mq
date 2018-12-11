package com.gk.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gk.learning.model.Employee;
import com.gk.learning.mqsender.RabbitMQSender;
/**
 * 
 * @author Kasturi's
 *
 */
@RestController
@RequestMapping(value = "/rabbit-mq-by-gk")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {

		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		rabbitMQSender.sendMessageToQueue(emp);

		return "Message sent to the RabbitMQ : \n" + emp.toString();
	}

}
