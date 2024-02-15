package com.vasquez.msbootcoin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * PurchaseSaleRate entity.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "purchase_sales_rate")
public class PurchaseSaleRate {

  @Id
  private String purchaseSaleRateId;

  private String originCurrency;

  private String targetCurrency;

  private Double salePrice;

  private Double purchasePrice;

}
