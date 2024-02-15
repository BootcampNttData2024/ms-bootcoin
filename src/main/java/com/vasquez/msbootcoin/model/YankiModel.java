package com.vasquez.msbootcoin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * PeerToPeer model
 */

@Schema(name = "YankiModel", description = "PeerToPeer model")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-09T19:33:02.323259900-05:00[America/Lima]")
public class YankiModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private String yankiId;

  private String documentType;

  private String documentNumber;

  private String phoneNumber;

  private String imeiNumber;

  private String email;

  private Double balance;

  private String associatedDebitCard;

  public YankiModel() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public YankiModel(String documentType, String documentNumber, String phoneNumber) {
    this.documentType = documentType;
    this.documentNumber = documentNumber;
    this.phoneNumber = phoneNumber;
  }

  public YankiModel yankiId(String yankiId) {
    this.yankiId = yankiId;
    return this;
  }

  /**
   * Get yankiId
   * @return yankiId
  */
  
  @Schema(name = "yankiId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("yankiId")
  public String getYankiId() {
    return yankiId;
  }

  public void setYankiId(String yankiId) {
    this.yankiId = yankiId;
  }

  public YankiModel documentType(String documentType) {
    this.documentType = documentType;
    return this;
  }

  /**
   * Get documentType
   * @return documentType
  */
  @NotNull 
  @Schema(name = "documentType", example = "DNI, CE", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("documentType")
  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  public YankiModel documentNumber(String documentNumber) {
    this.documentNumber = documentNumber;
    return this;
  }

  /**
   * Get documentNumber
   * @return documentNumber
  */
  @NotNull 
  @Schema(name = "documentNumber", example = "BCP", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("documentNumber")
  public String getDocumentNumber() {
    return documentNumber;
  }

  public void setDocumentNumber(String documentNumber) {
    this.documentNumber = documentNumber;
  }

  public YankiModel phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  */
  @NotNull 
  @Schema(name = "phoneNumber", example = "924152365", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("phoneNumber")
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public YankiModel imeiNumber(String imeiNumber) {
    this.imeiNumber = imeiNumber;
    return this;
  }

  /**
   * Get imeiNumber
   * @return imeiNumber
  */
  
  @Schema(name = "imeiNumber", example = "4521632585471234", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("imeiNumber")
  public String getImeiNumber() {
    return imeiNumber;
  }

  public void setImeiNumber(String imeiNumber) {
    this.imeiNumber = imeiNumber;
  }

  public YankiModel email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  
  @Schema(name = "email", example = "alcibar@gmail.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public YankiModel balance(Double balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
  */
  
  @Schema(name = "balance", example = "500.8", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("balance")
  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public YankiModel associatedDebitCard(String associatedDebitCard) {
    this.associatedDebitCard = associatedDebitCard;
    return this;
  }

  /**
   * Get associatedDebitCard
   * @return associatedDebitCard
  */
  
  @Schema(name = "associatedDebitCard", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("associatedDebitCard")
  public String getAssociatedDebitCard() {
    return associatedDebitCard;
  }

  public void setAssociatedDebitCard(String associatedDebitCard) {
    this.associatedDebitCard = associatedDebitCard;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    YankiModel yankiModel = (YankiModel) o;
    return Objects.equals(this.yankiId, yankiModel.yankiId) &&
        Objects.equals(this.documentType, yankiModel.documentType) &&
        Objects.equals(this.documentNumber, yankiModel.documentNumber) &&
        Objects.equals(this.phoneNumber, yankiModel.phoneNumber) &&
        Objects.equals(this.imeiNumber, yankiModel.imeiNumber) &&
        Objects.equals(this.email, yankiModel.email) &&
        Objects.equals(this.balance, yankiModel.balance) &&
        Objects.equals(this.associatedDebitCard, yankiModel.associatedDebitCard);
  }

  @Override
  public int hashCode() {
    return Objects.hash(yankiId, documentType, documentNumber, phoneNumber, imeiNumber, email, balance, associatedDebitCard);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class YankiModel {\n");
    sb.append("    yankiId: ").append(toIndentedString(yankiId)).append("\n");
    sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
    sb.append("    documentNumber: ").append(toIndentedString(documentNumber)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    imeiNumber: ").append(toIndentedString(imeiNumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    associatedDebitCard: ").append(toIndentedString(associatedDebitCard)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

