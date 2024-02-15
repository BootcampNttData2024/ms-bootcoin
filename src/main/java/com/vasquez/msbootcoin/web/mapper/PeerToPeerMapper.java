package com.vasquez.msbootcoin.web.mapper;

import com.vasquez.msbootcoin.entity.PeerToPeer;
import com.vasquez.msbootcoin.model.PeerToPeerModel;
import org.springframework.beans.BeanUtils;

/**
 * PeerToPeer mapper.
 *
 * @author Vasquez
 * @version 1.0.
 */
public class PeerToPeerMapper {

  PeerToPeerMapper() {
  }


  /**
   * To entity.
   *
   * @param peerToPeerModel peerToPeerModel
   * @return PeerToPeer
   */
  public static PeerToPeer toEntity(PeerToPeerModel peerToPeerModel) {
    PeerToPeer peerToPeer = new PeerToPeer();
    BeanUtils.copyProperties(peerToPeerModel, peerToPeer);
    return peerToPeer;
  }

  /**
   * To response.
   *
   * @param peerToPeer peerToPeer
   * @return PeerToPeerModel peerToPeerResponse
   */
  public static PeerToPeerModel toResponse(PeerToPeer peerToPeer) {
    PeerToPeerModel peerToPeerResponse = new PeerToPeerModel();
    BeanUtils.copyProperties(peerToPeer, peerToPeerResponse);
    return peerToPeerResponse;
  }

}
