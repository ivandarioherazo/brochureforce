/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ivanh_000
 */
@Entity
@Table(name = "product", catalog = "brochureforce", schema = "")
public class Product implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Basic(optional = false)
  @Column(name = "prd_manufct_id")
  private String prdManufctId;
  
  @Column(name = "prd_manufct_name")
  private String prdManufctName;
  
  @Column(name = "prd_manufct_website")
  private String prdManufctWebsite;
  
  @Basic(optional = false)
  @Column(name = "prd_id")
  private String prdId;
  
  @Column(name = "prd_name")
  private String prdName;
  
  @Column(name = "prd_model")
  private String prdModel;
  
  @Column(name = "prd_serial_number")
  private String prdSerialNumber;
  
  @Column(name = "prd_tech_specs")
  private String prdTechSpecs;

  @Column(name = "prd_unit_price")
  private Double prdUnitPrice;
  
  @Column(name = "prd_stock_qty")
  private Integer prdStockQty;
  
  @Column(name = "prd_image")
  private byte[] prdImage;

  public Product() {
  }

  public String getPrdManufctId() {
    return prdManufctId;
  }

  public void setPrdManufctId(String prdManufctId) {
    this.prdManufctId = prdManufctId;
  }

  public String getPrdManufctName() {
    return prdManufctName;
  }

  public void setPrdManufctName(String prdManufctName) {
    this.prdManufctName = prdManufctName;
  }

  public String getPrdManufctWebsite() {
    return prdManufctWebsite;
  }

  public void setPrdManufctWebsite(String prdManufctWebsite) {
    this.prdManufctWebsite = prdManufctWebsite;
  }

  public String getPrdId() {
    return prdId;
  }

  public void setPrdId(String prdId) {
    this.prdId = prdId;
  }

  public String getPrdName() {
    return prdName;
  }

  public void setPrdName(String prdName) {
    this.prdName = prdName;
  }

  public String getPrdModel() {
    return prdModel;
  }

  public void setPrdModel(String prdModel) {
    this.prdModel = prdModel;
  }

  public String getPrdSerialNumber() {
    return prdSerialNumber;
  }

  public void setPrdSerialNumber(String prdSerialNumber) {
    this.prdSerialNumber = prdSerialNumber;
  }

  public String getPrdTechSpecs() {
    return prdTechSpecs;
  }

  public void setPrdTechSpecs(String prdTechSpecs) {
    this.prdTechSpecs = prdTechSpecs;
  }

  public Double getPrdUnitPrice() {
    return prdUnitPrice;
  }

  public void setPrdUnitPrice(Double prdUnitPrice) {
    this.prdUnitPrice = prdUnitPrice;
  }

  public Integer getPrdStockQty() {
    return prdStockQty;
  }

  public void setPrdStockQty(Integer prdStockQty) {
    this.prdStockQty = prdStockQty;
  }

  public byte[] getPrdImage() {
    return prdImage;
  }

  public void setPrdImage(byte[] prdImage) {
    this.prdImage = prdImage;
  }
  
}
