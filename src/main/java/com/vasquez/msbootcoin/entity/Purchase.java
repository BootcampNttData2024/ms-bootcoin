package com.vasquez.msbootcoin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * PurchaseRepository entity.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "purchase")
public class Purchase {

  @Id
  private String purchaseId;

  private String transactionNumber;

  private String fromPhoneNumber;

  private String toPhoneNumber;

  private Double purchaseAmount;

  private String paymentMethod;

}
