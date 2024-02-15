package com.vasquez.msbootcoin.business.payment.impl;

import com.vasquez.msbootcoin.business.PurchaseService;
import com.vasquez.msbootcoin.business.exception.AppException;
import com.vasquez.msbootcoin.business.payment.PaymentService;
import com.vasquez.msbootcoin.business.payment.SaleService;
import com.vasquez.msbootcoin.repository.PeerToPeerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


import java.util.UUID;

/**
 * Sale service implementation.
 *
 * @author Vasquez
 * @version 1.0
 */
@Log4j2
@Service
public class SaleServiceImpl implements SaleService {

  private final PeerToPeerRepository peerToPeerRepository;
  private final PurchaseService purchaseService;
  private final PaymentService paymentService;

  public SaleServiceImpl(PeerToPeerRepository peerToPeerRepository, PurchaseService purchaseService, PaymentService paymentService) {
    this.peerToPeerRepository = peerToPeerRepository;
    this.purchaseService = purchaseService;
    this.paymentService = paymentService;
  }

  @Override
  public Mono<Boolean> confirmSale(String phoneNumber, String purchaseId) {
    return purchaseService.findById(purchaseId)
      .doOnNext(print -> log.info("Search purchase findById, {}", print))
      .flatMap(purchase -> {
        purchase.setTransactionNumber(UUID.randomUUID().toString());
        return purchaseService.update(purchase, purchase.getPurchaseId());
      }).doOnNext(print -> log.info("Transaction number created, {}", print))
      .flatMap(response -> paymentService.applyPayment(response.getTransactionNumber())
        .doOnNext(print -> log.info("Payment applied, {}", print))
        .flatMap(res -> peerToPeerRepository.findByPhoneNumber(phoneNumber)))
      .doOnNext(print -> log.info("search PeeToPeer findByPhoneNumber, {}", print))
      .flatMap(p2p -> {
        boolean removed = p2p.getPendingSales().remove(purchaseId);
        if (!removed)
          return Mono.error(AppException.notFound("The saleId %s does not exist".formatted(purchaseId)));
        return peerToPeerRepository.save(p2p);
      })
      .doOnNext(print -> log.info("Updated salesPending, {}", print))
      .flatMap(p2p -> Mono.just(true));
  }

}
