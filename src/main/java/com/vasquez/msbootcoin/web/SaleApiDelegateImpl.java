package com.vasquez.msbootcoin.web;

import com.vasquez.msbootcoin.api.SaleApiDelegate;
import com.vasquez.msbootcoin.business.payment.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * SaleApiDelegate implementation
 *
 * @author Vasquez
 * @version 1.0
 */
@Service
public class SaleApiDelegateImpl implements SaleApiDelegate {

  private final SaleService saleService;

  public SaleApiDelegateImpl(SaleService saleService) {
    this.saleService = saleService;
  }

  @Override
  public Mono<ResponseEntity<Boolean>> updateSale(String phoneNumber, String saleId, ServerWebExchange exchange) {
    return saleService.confirmSale(phoneNumber, saleId).map(ResponseEntity::ok);
  }
}
