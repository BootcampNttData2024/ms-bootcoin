package com.vasquez.msbootcoin.repository;

import com.vasquez.msbootcoin.entity.Purchase;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Purchase repository.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Repository
public interface PurchaseRepository extends ReactiveMongoRepository<Purchase, String> {
  Mono<Purchase> findByTransactionNumber(String transactionNumber);

}
