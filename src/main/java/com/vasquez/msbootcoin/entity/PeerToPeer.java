package com.vasquez.msbootcoin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

/**
 * PeerToPeer entity.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "peer_to_peer")
public class PeerToPeer {

  @Id
  private String peerToPeerId;

  private String documentType;

  private String documentNumber;

  private String phoneNumber;

  private String email;

  private List<String> pendingSales;

  private Boolean hasYankiAccount;

}
