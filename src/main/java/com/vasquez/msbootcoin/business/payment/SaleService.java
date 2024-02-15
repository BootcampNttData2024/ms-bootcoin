package com.vasquez.msbootcoin.business.payment;

import reactor.core.publisher.Mono;

/**
 * Request purchase interface
 *
 * @author Vasquez
 * @version 1.0
 */
public interface SaleService {

  Mono<Boolean> confirmSale(String phoneNumber, String purchaseId);

}
