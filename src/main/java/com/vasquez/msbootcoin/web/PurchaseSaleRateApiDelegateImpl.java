package com.vasquez.msbootcoin.web;


import com.vasquez.msbootcoin.api.PurchaseSaleRateApiDelegate;
import com.vasquez.msbootcoin.business.PurchaseSaleRateService;
import com.vasquez.msbootcoin.model.PurchaseSaleRateModel;
import com.vasquez.msbootcoin.web.mapper.PurchaseSaleRateMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * PurchaseSaleRate api delegate.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Service
public class PurchaseSaleRateApiDelegateImpl implements PurchaseSaleRateApiDelegate {

  private final PurchaseSaleRateService purchaseSaleRate;

  public PurchaseSaleRateApiDelegateImpl(PurchaseSaleRateService purchaseSaleRate) {
    this.purchaseSaleRate = purchaseSaleRate;
  }

  @Override
  public Mono<ResponseEntity<PurchaseSaleRateModel>> addPurchaseSaleRate(Mono<PurchaseSaleRateModel> purchaseSaleRateERequest, ServerWebExchange exchange) {
    return purchaseSaleRateERequest
      .map(PurchaseSaleRateMapper::toEntity)
      .flatMap(purchaseSaleRate::save)
      .map(PurchaseSaleRateMapper::toResponse)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<PurchaseSaleRateModel>> getPurchaseSaleRateById(String purchaserId, ServerWebExchange exchange) {
    return purchaseSaleRate.findById(purchaserId)
      .map(PurchaseSaleRateMapper::toResponse)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<PurchaseSaleRateModel>> updatePurchaseSaleRate(String purchaserId, Mono<PurchaseSaleRateModel> purchaseSaleRateERequest, ServerWebExchange exchange) {
    return purchaseSaleRateERequest
      .map(PurchaseSaleRateMapper::toEntity)
      .flatMap(acc -> purchaseSaleRate.update(acc, purchaserId))
      .map(PurchaseSaleRateMapper::toResponse)
      .map(ResponseEntity::ok);
  }

}
