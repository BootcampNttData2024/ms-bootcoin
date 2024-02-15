package com.vasquez.msbootcoin.kafka.consumer;

import com.vasquez.msbootcoin.business.PeerToPeerService;
import com.vasquez.msbootcoin.business.exception.AppException;
import com.vasquez.msbootcoin.entity.PeerToPeer;
import com.vasquez.msbootcoin.kafka.event.KafkaEvent;
import com.vasquez.msbootcoin.repository.PeerToPeerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


import java.util.Map;

/**
 * Kafka consumer
 *
 * @author Vasquez
 * @version 1.0
 */
@Log4j2
@EnableKafka
@Component
public class KafkaConsumer {

  private final PeerToPeerRepository peerToPeerRepository;

  public KafkaConsumer(PeerToPeerRepository peerToPeerRepository) {
    this.peerToPeerRepository = peerToPeerRepository;
  }

  @KafkaListener(topics = "P2P_TOPIC_RES", containerFactory = "kafkaListenerContainerFactory", groupId = "BOOTCOIN_GROUP")
  public void peerToPeerConsumer(KafkaEvent<Map<String, Object>> event) {
    log.info("Listen P2P_TOPIC_RES, {}", event.getData());
    String phoneNumber = event.getData().get("phoneNumber").toString();
    peerToPeerRepository.findByPhoneNumber(phoneNumber)
      //.onErrorResume(err -> Mono.error(AppException.notFound("Error on request purchase %s".formatted(err.getMessage()))))
      .single()
      .subscribe(p2p -> {
        p2p.setHasYankiAccount(true);
        peerToPeerRepository.save(p2p)
          .subscribe(res -> log.info("P2P has yanki account updated, {}", res));
      });
  }

  @KafkaListener(topics = "PURCHASE_TOPIC_RES", containerFactory = "kafkaListenerContainerFactory", groupId = "BOOTCOIN_GROUP")
  public void purchaseConsumer(KafkaEvent<Map<String, Object>> event) {
    log.info("Listen PURCHASE_TOPIC_RES, {}", event.getData());
  }

}
