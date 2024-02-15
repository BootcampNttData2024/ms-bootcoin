package com.vasquez.msbootcoin.business.payment;

import com.vasquez.msbootcoin.entity.Purchase;
import reactor.core.publisher.Mono;

/**
 * Yank payment service
 *
 * @author Vasquez
 * @version 1.0
 */
public interface YankPaymentService {

  Mono<Purchase> payment(Purchase request);

}
