package com.vasquez.msbootcoin.business.payment.impl;

import com.vasquez.msbootcoin.business.exception.AppException;
import com.vasquez.msbootcoin.business.payment.BuyService;
import com.vasquez.msbootcoin.entity.PeerToPeer;
import com.vasquez.msbootcoin.repository.PeerToPeerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Buy service implementation.
 *
 * @author Vasquez
 * @version 1.0
 */
@Log4j2
@Service
public class BuyServiceImpl implements BuyService {

  private final PeerToPeerRepository peerToPeerRepository;

  public BuyServiceImpl(PeerToPeerRepository peerToPeerRepository) {
    this.peerToPeerRepository = peerToPeerRepository;
  }

  @Override
  public Mono<PeerToPeer> requestPurchase(String phoneNumber, String purchaseId) {
    log.info("updatePurchase request %s %s".formatted(phoneNumber, purchaseId));
    return peerToPeerRepository.findByPhoneNumber(phoneNumber)
      .switchIfEmpty(Mono.error(AppException.notFound("Phone number %s does not exist".formatted(phoneNumber))))
      .flatMap(p2p -> {
        p2p.getPendingSales().add(purchaseId);
        log.info("p2p request %s %s".formatted(phoneNumber, purchaseId));
        return peerToPeerRepository.save(p2p);
      }).onErrorResume(err -> Mono.error(AppException.notFound("Error on request purchase %s".formatted(err.getMessage()))));
  }

}
