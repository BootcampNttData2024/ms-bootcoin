package com.vasquez.msbootcoin.web;


import com.vasquez.msbootcoin.api.PurchaseApiDelegate;
import com.vasquez.msbootcoin.business.PurchaseService;
import com.vasquez.msbootcoin.model.PurchaseModel;
import com.vasquez.msbootcoin.web.mapper.PurchaseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Purchase api delegate.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Service
public class PurchaseApiDelegateImpl implements PurchaseApiDelegate {

  private final PurchaseService purchaserService;

  public PurchaseApiDelegateImpl(PurchaseService purchaserService) {
    this.purchaserService = purchaserService;
  }

  @Override
  public Mono<ResponseEntity<PurchaseModel>> addPurchase(Mono<PurchaseModel> purchaserRequest, ServerWebExchange exchange) {
    return purchaserRequest
      .map(PurchaseMapper::toEntity)
      .flatMap(purchaserService::save)
      .map(PurchaseMapper::toResponse)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<PurchaseModel>> getPurchaseById(String purchaserId, ServerWebExchange exchange) {
    return purchaserService.findById(purchaserId)
      .map(PurchaseMapper::toResponse)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<PurchaseModel>> updatePurchase(String purchaserId, Mono<PurchaseModel> purchaserRequest, ServerWebExchange exchange) {
    return purchaserRequest
      .map(PurchaseMapper::toEntity)
      .flatMap(acc -> purchaserService.update(acc, purchaserId))
      .map(PurchaseMapper::toResponse)
      .map(ResponseEntity::ok);
  }

}
