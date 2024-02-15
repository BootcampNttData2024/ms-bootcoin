package com.vasquez.msbootcoin.web.mapper;

import com.vasquez.msbootcoin.entity.Purchase;
import com.vasquez.msbootcoin.model.PurchaseModel;
import org.springframework.beans.BeanUtils;

/**
 * Purchase mapper.
 *
 * @author Vasquez
 * @version 1.0.
 */
public class PurchaseMapper {

  PurchaseMapper() {
  }


  /**
   * To entity.
   *
   * @param purchaseModel purchaseModel
   * @return Purchase
   */
  public static Purchase toEntity(PurchaseModel purchaseModel) {
    Purchase purchase = new Purchase();
    BeanUtils.copyProperties(purchaseModel, purchase);
    return purchase;
  }

  /**
   * To response.
   *
   * @param purchase purchase
   * @return PurchaseModel purchaseResponse
   */
  public static PurchaseModel toResponse(Purchase purchase) {
    PurchaseModel purchaseResponse = new PurchaseModel();
    BeanUtils.copyProperties(purchase, purchaseResponse);
    return purchaseResponse;
  }

}
