package com.vasquez.msbootcoin.business;

import com.vasquez.msbootcoin.entity.Purchase;
import reactor.core.publisher.Mono;

/**
 * Purchase service.
 *
 * @author Vasquez
 * @version 1.0.
 */
public interface PurchaseService {

  Mono<Purchase> save(Purchase request);

  Mono<Purchase> update(Purchase request, String purchaseId);

  Mono<Purchase> findById(String purchaseId);

  Mono<Purchase> findByTransactionNumber(String transactionNumber);

}
