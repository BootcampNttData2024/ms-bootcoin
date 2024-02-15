package com.vasquez.msbootcoin.business;

import com.vasquez.msbootcoin.entity.PeerToPeer;
import reactor.core.publisher.Mono;

/**
 * PeerToPeer service.
 *
 * @author Vasquez
 * @version 1.0.
 */
public interface PeerToPeerService {

  Mono<PeerToPeer> save(PeerToPeer request);

  Mono<PeerToPeer> update(PeerToPeer request, String peerToPeerId);

  Mono<PeerToPeer> findById(String peerToPeerId);

  Mono<PeerToPeer> findByPhoneNumber(String phoneNumber);

}
