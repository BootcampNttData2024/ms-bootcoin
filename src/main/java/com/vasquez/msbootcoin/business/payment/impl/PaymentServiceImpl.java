package com.vasquez.msbootcoin.business.payment.impl;

import com.vasquez.msbootcoin.business.PurchaseSaleRateService;
import com.vasquez.msbootcoin.business.exception.AppException;
import com.vasquez.msbootcoin.business.payment.PaymentService;
import com.vasquez.msbootcoin.business.payment.YankPaymentService;
import com.vasquez.msbootcoin.entity.Purchase;
import com.vasquez.msbootcoin.entity.PurchaseSaleRate;
import com.vasquez.msbootcoin.entity.enums.PaymentMethod;
import com.vasquez.msbootcoin.proxy.YankiProxy;
import com.vasquez.msbootcoin.proxy.model.YankiModel;
import com.vasquez.msbootcoin.repository.PurchaseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


import java.util.UUID;

/**
 * payment service implementation.
 *
 * @author Vasquez
 * @version 1.0
 */
@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService {

  private final PurchaseRepository purchaseRepository;
  private final YankPaymentService yankPaymentService;

  public PaymentServiceImpl(PurchaseRepository purchaseRepository, YankPaymentService yankPaymentService) {
    this.purchaseRepository = purchaseRepository;
    this.yankPaymentService = yankPaymentService;
  }

  @Override
  public Mono<Purchase> applyPayment(String transactionNumber) {
    return purchaseRepository.findByTransactionNumber(transactionNumber)
      .flatMap(request -> {
        boolean isYankPaymentMethod = PaymentMethod.YANKI.getValue().equals(request.getPaymentMethod());
        boolean isTransferPaymentMethod = PaymentMethod.TRANSFER.getValue().equals(request.getPaymentMethod());
        if (isYankPaymentMethod)
          return yankPaymentService.payment(request);
        if (isTransferPaymentMethod)
          //not implemented
          return null;
        return null;
      });
  }

}
