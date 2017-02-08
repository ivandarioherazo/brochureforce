/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.entity;

/**
 * Profile type of the logged user. Used to define the dashboard options to
 * display.
 *
 * @author LIA Solutions SAS.
 */
public enum ProfileType {
  /**
   * Profile type: Company.
   */
  COMPANY,
  /**
   * Profile type: Client.
   */
  CLIENT,
  /**
   * Profile type: Company which has been authorized to sell products.
   */
  SELLER_COMPANY;
  
  static {
    CLIENT.description = "Client";
    COMPANY.description = "Company";
    SELLER_COMPANY.description = "Seller";
  }
  
  private String description;

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
