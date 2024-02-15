package com.vasquez.msbootcoin.web.mapper;

import com.vasquez.msbootcoin.entity.PurchaseSaleRate;
import com.vasquez.msbootcoin.model.PurchaseSaleRateModel;
import org.springframework.beans.BeanUtils;

/**
 * PurchaseSaleRate mapper.
 *
 * @author Vasquez
 * @version 1.0.
 */
public class PurchaseSaleRateMapper {

  PurchaseSaleRateMapper() {
  }


  /**
   * To entity.
   *
   * @param purchaseSaleRateModel purchaseSaleRateModel
   * @return PurchaseSaleRate
   */
  public static PurchaseSaleRate toEntity(PurchaseSaleRateModel purchaseSaleRateModel) {
    PurchaseSaleRate purchaseSaleRate = new PurchaseSaleRate();
    BeanUtils.copyProperties(purchaseSaleRateModel, purchaseSaleRate);
    return purchaseSaleRate;
  }

  /**
   * To response.
   *
   * @param purchaseSaleRate purchaseSaleRate
   * @return PurchaseSaleRateModel purchaseSaleRateResponse
   */
  public static PurchaseSaleRateModel toResponse(PurchaseSaleRate purchaseSaleRate) {
    PurchaseSaleRateModel purchaseSaleRateResponse = new PurchaseSaleRateModel();
    BeanUtils.copyProperties(purchaseSaleRate, purchaseSaleRateResponse);
    return purchaseSaleRateResponse;
  }

}
