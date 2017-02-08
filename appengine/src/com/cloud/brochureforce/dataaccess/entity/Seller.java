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
@Table(name = "seller", catalog = "brochureforce", schema = "")
public class Seller implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Basic(optional = false)
  @Column(name = "sl_id")
  private String slId;
  
  @Column(name = "sl_name")
  private String slName;
  
  @Column(name = "sl_password")
  private String slPassword;
  
  @Column(name = "sl_phone_number")
  private String slPhoneNumber;
  
  @Column(name = "sl_email")
  private String slEmail;
  
  @Column(name = "sl_address")
  private String slAddress;
  
  @Column(name = "sl_country")
  private String slCountry;
  
  @Column(name = "sl_province")
  private String slProvince;
  
  @Column(name = "sl_city")
  private String slCity;
  
  @Column(name = "sl_fax_number")
  private String slFaxNumber;
  
  @Column(name = "sl_photo")
  private byte[] slPhoto;
  
  @Column(name = "sl_representatives")
  private String slRepresentatives;
  
  @Column(name = "sl_website")
  private String slWebsite;

  public Seller() {
  }

  public String getSlId() {
    return slId;
  }

  public void setSlId(String slId) {
    this.slId = slId;
  }

  public String getSlName() {
    return slName;
  }

  public void setSlName(String slName) {
    this.slName = slName;
  }

  public String getSlPassword() {
    return slPassword;
  }

  public void setSlPassword(String slPassword) {
    this.slPassword = slPassword;
  }

  public String getSlPhoneNumber() {
    return slPhoneNumber;
  }

  public void setSlPhoneNumber(String slPhoneNumber) {
    this.slPhoneNumber = slPhoneNumber;
  }

  public String getSlEmail() {
    return slEmail;
  }

  public void setSlEmail(String slEmail) {
    this.slEmail = slEmail;
  }

  public String getSlAddress() {
    return slAddress;
  }

  public void setSlAddress(String slAddress) {
    this.slAddress = slAddress;
  }

  public String getSlCountry() {
    return slCountry;
  }

  public void setSlCountry(String slCountry) {
    this.slCountry = slCountry;
  }

  public String getSlProvince() {
    return slProvince;
  }

  public void setSlProvince(String slProvince) {
    this.slProvince = slProvince;
  }

  public String getSlCity() {
    return slCity;
  }

  public void setSlCity(String slCity) {
    this.slCity = slCity;
  }

  public String getSlFaxNumber() {
    return slFaxNumber;
  }

  public void setSlFaxNumber(String slFaxNumber) {
    this.slFaxNumber = slFaxNumber;
  }

  public byte[] getSlPhoto() {
    return slPhoto;
  }

  public void setSlPhoto(byte[] slPhoto) {
    this.slPhoto = slPhoto;
  }

  public String getSlRepresentatives() {
    return slRepresentatives;
  }

  public void setSlRepresentatives(String slRepresentatives) {
    this.slRepresentatives = slRepresentatives;
  }

  public String getSlWebsite() {
    return slWebsite;
  }

  public void setSlWebsite(String slWebsite) {
    this.slWebsite = slWebsite;
  }
}
