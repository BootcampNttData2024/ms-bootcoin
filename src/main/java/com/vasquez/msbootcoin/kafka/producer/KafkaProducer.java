package com.vasquez.msbootcoin.kafka.producer;

import com.vasquez.msbootcoin.kafka.event.KafkaEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.UUID;

@Component
public class KafkaProducer<T> {

  private final KafkaTemplate<String, KafkaEvent<?>> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, KafkaEvent<?>> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publish(String topic, String type, T data) {
    KafkaEvent<T> event = new KafkaEvent<>();
    event.setId(UUID.randomUUID().toString());
    event.setDate(new Date());
    event.setEventType(type);
    event.setData(data);
    this.kafkaTemplate.send(topic, event);
  }

}
