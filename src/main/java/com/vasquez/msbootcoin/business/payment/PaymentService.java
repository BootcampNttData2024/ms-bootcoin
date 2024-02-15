package com.vasquez.msbootcoin.business.payment;

import com.vasquez.msbootcoin.entity.Purchase;
import reactor.core.publisher.Mono;

/**
 * Request purchase interface
 *
 * @author Vasquez
 * @version 1.0
 */
public interface PaymentService {

  Mono<Purchase> applyPayment(String transactionNumber);

}
