package com.vasquez.msbootcoin.business;

import com.vasquez.msbootcoin.entity.PurchaseSaleRate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * PurchaseSaleRate service.
 *
 * @author Vasquez
 * @version 1.0.
 */
public interface PurchaseSaleRateService {

  Mono<PurchaseSaleRate> save(PurchaseSaleRate request);

  Mono<PurchaseSaleRate> update(PurchaseSaleRate request, String purchaseSaleRateId);

  Mono<PurchaseSaleRate> findById(String purchaseSaleRateId);

  Flux<PurchaseSaleRate> findAll();
}
