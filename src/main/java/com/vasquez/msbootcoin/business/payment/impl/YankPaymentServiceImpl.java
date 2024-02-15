package com.vasquez.msbootcoin.business.payment.impl;

import com.vasquez.msbootcoin.business.PurchaseSaleRateService;
import com.vasquez.msbootcoin.business.exception.AppException;
import com.vasquez.msbootcoin.business.payment.YankPaymentService;
import com.vasquez.msbootcoin.entity.Purchase;
import com.vasquez.msbootcoin.entity.PurchaseSaleRate;
import com.vasquez.msbootcoin.proxy.YankiProxy;
import com.vasquez.msbootcoin.proxy.model.YankiModel;
import com.vasquez.msbootcoin.repository.PurchaseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


import java.util.UUID;

/**
 * Yank payment implementation
 *
 * @author Vasquez
 * @version 1.0
 */
@Log4j2
@Service
public class YankPaymentServiceImpl implements YankPaymentService {

  private final PurchaseRepository purchaseRepository;
  private final PurchaseSaleRateService purchaseSaleRateService;
  private final YankiProxy yankiProxy;

  public YankPaymentServiceImpl(PurchaseRepository purchaseRepository, PurchaseSaleRateService purchaseSaleRateService, YankiProxy yankiProxy) {
    this.purchaseRepository = purchaseRepository;
    this.purchaseSaleRateService = purchaseSaleRateService;
    this.yankiProxy = yankiProxy;
  }

  @Override
  public Mono<Purchase> payment(Purchase request) {
    //define yank accounts
    Mono<YankiModel> fromYankAccount = yankiProxy.getYankiByPhoneNumber(request.getFromPhoneNumber());
    Mono<YankiModel> toYankAccount = yankiProxy.getYankiByPhoneNumber(request.getToPhoneNumber());
    Mono<PurchaseSaleRate> purchaseSaleRateMono = purchaseSaleRateService.findAll()
      .filter(p -> p.getOriginCurrency().equals("SOL") && p.getTargetCurrency().equals("BOOTCOIN")).single();

    log.info("Yank Payment Method");
    return Mono.zip(fromYankAccount, toYankAccount)
      .flatMap(yankResponse -> purchaseSaleRateMono
        .doOnNext(print -> log.info("purchaseSaleRateMono, {}", purchaseSaleRateMono))
        .flatMap(purchaseSaleRate -> {
          YankiModel fromYank = yankResponse.getT1();
          YankiModel toYank = yankResponse.getT2();

          //define price
          double priceFromPurchase = purchaseSaleRate.getPurchasePrice() * request.getPurchaseAmount();
          log.info("priceFromPurchase " + priceFromPurchase);

          //check amount available
          if (fromYank.getBalance() < priceFromPurchase)
            return Mono.error(AppException.badRequest("The amount of the yanki account %s is insufficient.".formatted(fromYank.getPhoneNumber())));

          //apply payment
          fromYank.setBalance(fromYank.getBalance() - priceFromPurchase);
          toYank.setBalance(toYank.getBalance() + priceFromPurchase);
          return Mono.zip(yankiProxy.updateYanki(fromYank), yankiProxy.updateYanki(toYank))
            .flatMap(yankResponse2 -> {
              request.setTransactionNumber(UUID.randomUUID().toString());
              return purchaseRepository.save(request);
            });
        })
      );
  }
}
