package com.vasquez.msbootcoin.repository;

import com.vasquez.msbootcoin.entity.PurchaseSaleRate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * PurchaseSaleRate repository.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Repository
public interface PurchaseSaleRateRepository extends ReactiveMongoRepository<PurchaseSaleRate, String> {

}
