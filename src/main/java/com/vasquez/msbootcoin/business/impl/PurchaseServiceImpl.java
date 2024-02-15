package com.vasquez.msbootcoin.business.impl;

import com.vasquez.msbootcoin.business.PeerToPeerService;
import com.vasquez.msbootcoin.business.PurchaseSaleRateService;
import com.vasquez.msbootcoin.business.PurchaseService;
import com.vasquez.msbootcoin.business.exception.AppException;
import com.vasquez.msbootcoin.business.payment.BuyService;
import com.vasquez.msbootcoin.entity.Purchase;
import com.vasquez.msbootcoin.entity.PurchaseSaleRate;
import com.vasquez.msbootcoin.entity.enums.PaymentMethod;
import com.vasquez.msbootcoin.kafka.producer.KafkaProducer;
import com.vasquez.msbootcoin.proxy.YankiProxy;
import com.vasquez.msbootcoin.proxy.model.YankiModel;
import com.vasquez.msbootcoin.repository.PurchaseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


import java.util.UUID;

/**
 * Purchase service implementation.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Log4j2
@Service
public class PurchaseServiceImpl implements PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final BuyService buyService;

  /**
   * Purchase service constructor.
   *
   * @param purchaseRepository account
   */
  public PurchaseServiceImpl(PurchaseRepository purchaseRepository, BuyService buyService) {
    this.purchaseRepository = purchaseRepository;
    this.buyService = buyService;
  }

  @Override
  public Mono<Purchase> save(Purchase request) {
    return purchaseRepository.save(request)
      .flatMap(purchase -> buyService.requestPurchase(request.getToPhoneNumber(), purchase.getPurchaseId())
        .flatMap(res -> Mono.just(purchase))
        .doOnError(err -> {
          log.info("Error saving purchase");
          purchaseRepository.delete(purchase).subscribe();
        })
      );
  }

  @Override
  public Mono<Purchase> update(Purchase request, String id) {
    log.info("p2p request, {}", request);
    return this.findById(id).flatMap(p2p -> {
      p2p.setTransactionNumber(request.getTransactionNumber());
      return purchaseRepository.save(p2p);
    });
  }

  @Override
  public Mono<Purchase> findById(String id) {
    return purchaseRepository.findById(id);
  }

  @Override
  public Mono<Purchase> findByTransactionNumber(String transactionNumber) {
    return purchaseRepository.findByTransactionNumber(transactionNumber);
  }

}
