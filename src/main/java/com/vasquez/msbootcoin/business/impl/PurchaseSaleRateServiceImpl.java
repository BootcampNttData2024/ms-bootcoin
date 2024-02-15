package com.vasquez.msbootcoin.business.impl;

import com.vasquez.msbootcoin.business.PurchaseSaleRateService;
import com.vasquez.msbootcoin.entity.PurchaseSaleRate;
import com.vasquez.msbootcoin.repository.PurchaseSaleRateRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * PurchaseSaleRate service implementation.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Log4j2
@Service
public class PurchaseSaleRateServiceImpl implements PurchaseSaleRateService {

  private final PurchaseSaleRateRepository purchaseSaleRateRepository;

  /**
   * PurchaseSaleRate service constructor.
   *
   * @param purchaseSaleRateRepository account
   */
  public PurchaseSaleRateServiceImpl(PurchaseSaleRateRepository purchaseSaleRateRepository) {
    this.purchaseSaleRateRepository = purchaseSaleRateRepository;
  }

  @Override
  public Mono<PurchaseSaleRate> save(PurchaseSaleRate request) {
    return purchaseSaleRateRepository.save(request);
  }

  @Override
  public Mono<PurchaseSaleRate> update(PurchaseSaleRate request, String id) {
    log.info("p2p request, {}", request);
    return this.findById(id).flatMap(purchaseSaleRate -> {
      purchaseSaleRate.setPurchasePrice(request.getPurchasePrice());
      return purchaseSaleRateRepository.save(purchaseSaleRate);
    });
  }

  @Override
  public Mono<PurchaseSaleRate> findById(String id) {
    return purchaseSaleRateRepository.findById(id);
  }

  @Override
  public Flux<PurchaseSaleRate> findAll() {
    return purchaseSaleRateRepository.findAll();
  }

}

