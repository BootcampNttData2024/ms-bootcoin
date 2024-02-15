package com.vasquez.msbootcoin.repository;

import com.vasquez.msbootcoin.entity.PeerToPeer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * PeerToPeer repository.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Repository
public interface PeerToPeerRepository extends ReactiveMongoRepository<PeerToPeer, String> {

  Mono<PeerToPeer> findByPhoneNumber(String phoneNumber);

}
