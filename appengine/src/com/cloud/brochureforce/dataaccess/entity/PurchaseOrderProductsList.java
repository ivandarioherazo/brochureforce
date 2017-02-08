/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.entity;

import com.cloud.brochureforce.dataaccess.entity.pk.PurchaseOrderProductsListPK;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ivanh_000
 */
@Entity
@Table(name = "purchase_order_products_list", catalog = "brochureforce", schema = "")
public class PurchaseOrderProductsList implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @EmbeddedId
  private PurchaseOrderProductsListPK ordProdListPk;
  
  @Column(name = "ord_provider_id")
  private String ordProviderId;
  
  @Column(name = "ord_seller_id")
  private String ordSellerId;
  
  @Column(name = "ord_client_id")
  private String ordClientId;
  
  @Column(name = "ord_manufct_name")
  private String ordManufctName;
  
  @Column(name = "ord_manufct_website")
  private String ordManufctWebsite;
  
  @Column(name = "ord_prd_name")
  private String ordPrdName;
  
  @Column(name = "ord_prd_model")
  private String ordPrdModel;
  
  @Column(name = "ord_prd_serial_number")
  private String ordPrdSerialNumber;
  
  @Column(name = "ord_prd_tech_specs")
  private String ordPrdTechSpecs;
  
  @Column(name = "ord_prd_unit_price")
  private Double ordPrdUnitPrice;

  public PurchaseOrderProductsList() {
  }

  public PurchaseOrderProductsList(PurchaseOrderProductsListPK ordProdListPk) {
    this.ordProdListPk = ordProdListPk;
  }

  public PurchaseOrderProductsList(String orderId, String productId) {
    this.ordProdListPk = new PurchaseOrderProductsListPK(orderId, productId);
  }

  public PurchaseOrderProductsListPK getOrdProductListPk() {
    return this.ordProdListPk;
  }

  public void setOrdProductListPk(PurchaseOrderProductsListPK ordProdListPk) {
    this.ordProdListPk = ordProdListPk;
  }

  public String getOrdProviderId() {
    return ordProviderId;
  }

  public void setOrdProviderId(String ordProviderId) {
    this.ordProviderId = ordProviderId;
  }

  public String getOrdSellerId() {
    return ordSellerId;
  }

  public void setOrdSellerId(String ordSellerId) {
    this.ordSellerId = ordSellerId;
  }

  public String getOrdClientId() {
    return ordClientId;
  }

  public void setOrdClientId(String ordClientId) {
    this.ordClientId = ordClientId;
  }

  public String getOrdManufctName() {
    return ordManufctName;
  }

  public void setOrdManufctName(String ordManufctName) {
    this.ordManufctName = ordManufctName;
  }

  public String getOrdManufctWebsite() {
    return ordManufctWebsite;
  }

  public void setOrdManufctWebsite(String ordManufctWebsite) {
    this.ordManufctWebsite = ordManufctWebsite;
  }

  public String getOrdPrdName() {
    return ordPrdName;
  }

  public void setOrdPrdName(String ordPrdName) {
    this.ordPrdName = ordPrdName;
  }

  public String getOrdPrdModel() {
    return ordPrdModel;
  }

  public void setOrdPrdModel(String ordPrdModel) {
    this.ordPrdModel = ordPrdModel;
  }

  public String getOrdPrdSerialNumber() {
    return ordPrdSerialNumber;
  }

  public void setOrdPrdSerialNumber(String ordPrdSerialNumber) {
    this.ordPrdSerialNumber = ordPrdSerialNumber;
  }

  public String getOrdPrdTechSpecs() {
    return ordPrdTechSpecs;
  }

  public void setOrdPrdTechSpecs(String ordPrdTechSpecs) {
    this.ordPrdTechSpecs = ordPrdTechSpecs;
  }

  public Double getOrdPrdUnitPrice() {
    return ordPrdUnitPrice;
  }

  public void setOrdPrdUnitPrice(Double ordPrdUnitPrice) {
    this.ordPrdUnitPrice = ordPrdUnitPrice;
  }
  
}
