package com.vasquez.msbootcoin.entity.enums;

import lombok.Getter;

/**
 * PaymentMethod enum.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Getter
public enum PaymentMethod {

  YANKI("YANKI"),
  TRANSFER("TRANSFER");
  private String value;

  PaymentMethod(String value) {
    this.value = value;
  }

}
