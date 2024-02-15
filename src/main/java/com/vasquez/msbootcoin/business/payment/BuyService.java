package com.vasquez.msbootcoin.business.payment;

import com.vasquez.msbootcoin.entity.PeerToPeer;
import reactor.core.publisher.Mono;

/**
 * Request purchase interface
 *
 * @author Vasquez
 * @version 1.0
 */
public interface BuyService {

  Mono<PeerToPeer> requestPurchase(String phoneNumber, String purchaseId);

}
