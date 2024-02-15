package com.vasquez.msbootcoin.web;


import com.vasquez.msbootcoin.api.PeerToPeerApiDelegate;
import com.vasquez.msbootcoin.business.PeerToPeerService;
import com.vasquez.msbootcoin.model.PeerToPeerModel;
import com.vasquez.msbootcoin.web.mapper.PeerToPeerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * PeerToPeer api delegate.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Service
public class PeerToPeerApiDelegateImpl implements PeerToPeerApiDelegate {

  private final PeerToPeerService peerToPeerService;

  public PeerToPeerApiDelegateImpl(PeerToPeerService peerToPeerService) {
    this.peerToPeerService = peerToPeerService;
  }

  @Override
  public Mono<ResponseEntity<PeerToPeerModel>> addPeerToPeer(Mono<PeerToPeerModel> peerToPeerRequest, ServerWebExchange exchange) {
    return peerToPeerRequest
      .map(PeerToPeerMapper::toEntity)
      .flatMap(peerToPeerService::save)
      .map(PeerToPeerMapper::toResponse)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<PeerToPeerModel>> getPeerToPeerById(String peerToPeerId, ServerWebExchange exchange) {
    return peerToPeerService.findById(peerToPeerId)
      .map(PeerToPeerMapper::toResponse)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<PeerToPeerModel>> updatePeerToPeer(String peerToPeerId, Mono<PeerToPeerModel> peerToPeerRequest, ServerWebExchange exchange) {
    return peerToPeerRequest
      .map(PeerToPeerMapper::toEntity)
      .flatMap(acc -> peerToPeerService.update(acc, peerToPeerId))
      .map(PeerToPeerMapper::toResponse)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<PeerToPeerModel>> getPeerToPeerByPhoneNumber(String phoneNumber, ServerWebExchange exchange) {
    return peerToPeerService.findByPhoneNumber(phoneNumber)
      .map(PeerToPeerMapper::toResponse)
      .map(ResponseEntity::ok);
  }

}
