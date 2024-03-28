package com.example.service;

import com.example.dto.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {

	private NewTopic topic;

	private KafkaTemplate<String, OrderEvent> kafkaTemplate;

	public void sendMessage(OrderEvent event) {
		log.info(String.format("Order event => %s", event.toString()));

		// create Message
		Message<OrderEvent> message = MessageBuilder.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		kafkaTemplate.send(message);
	}
}