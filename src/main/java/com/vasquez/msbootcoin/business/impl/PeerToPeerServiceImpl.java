package com.vasquez.msbootcoin.business.impl;

import com.vasquez.msbootcoin.business.PeerToPeerService;
import com.vasquez.msbootcoin.entity.PeerToPeer;
import com.vasquez.msbootcoin.kafka.producer.KafkaProducer;
import com.vasquez.msbootcoin.repository.PeerToPeerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


import java.util.ArrayList;

/**
 * PeerToPeer service implementation.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Log4j2
@Service
public class PeerToPeerServiceImpl implements PeerToPeerService {

  private final PeerToPeerRepository peerToPeerRepository;
  private final KafkaProducer<PeerToPeer> kafkaProducer;

  /**
   * PeerToPeer service constructor.
   *
   * @param peerToPeerRepository account
   */
  public PeerToPeerServiceImpl(PeerToPeerRepository peerToPeerRepository, KafkaProducer<PeerToPeer> kafkaProducer) {
    this.peerToPeerRepository = peerToPeerRepository;
    this.kafkaProducer = kafkaProducer;
  }

  @Override
  public Mono<PeerToPeer> save(PeerToPeer request) {
    request.setHasYankiAccount(false);
    request.setPendingSales(new ArrayList<>());
    kafkaProducer.publish("P2P_TOPIC_REQ", "CREATED", request);
    return peerToPeerRepository.save(request);
  }

  @Override
  public Mono<PeerToPeer> update(PeerToPeer request, String id) {
    log.info("p2p request, {}", request);
    return this.findById(id).flatMap(p2p -> {
      p2p.setHasYankiAccount(true);
      return peerToPeerRepository.save(p2p);
    });
  }

  @Override
  public Mono<PeerToPeer> findById(String id) {
    return peerToPeerRepository.findById(id);
  }

  @Override
  public Mono<PeerToPeer> findByPhoneNumber(String phoneNumber) {
    return peerToPeerRepository.findByPhoneNumber(phoneNumber);
  }

}

