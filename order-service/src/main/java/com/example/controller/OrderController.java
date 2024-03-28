package com.example.controller;

import com.example.dto.Order;
import com.example.dto.OrderEvent;
import com.example.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class OrderController {
	private OrderService orderService;

	@PostMapping("/orders")
	public String placeOrder(@RequestBody Order order) {

		order.setOrderId(UUID.randomUUID()
				.toString());

		OrderEvent orderEvent = new OrderEvent();
		orderEvent.setStatus("PENDING");
		orderEvent.setMessage("order status is in pending state");
		orderEvent.setOrder(order);

		orderService.sendMessage(orderEvent);

		return "Order placed successfully ...";
	}
}