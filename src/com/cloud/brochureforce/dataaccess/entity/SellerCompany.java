/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.entity;

import com.cloud.brochureforce.dataaccess.entity.pk.SellerCompanyPK;
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
@Table(name = "seller_company", catalog = "brochureforce", schema = "")
public class SellerCompany implements Serializable, DatabaseUser {

  private static final long serialVersionUID = 1L;
  
  @EmbeddedId
  private SellerCompanyPK sellerCompanyId;
  
  @Column(name = "cm_name")
  private String cmName;
  
  @Column(name = "cm_representatives")
  private String cmRepresentatives;
  
  @Column(name = "cm_phone_number")
  private String cmPhoneNumber;
  
  @Column(name = "cm_email")
  private String cmEmail;
  
  @Column(name = "cm_address")
  private String cmAddress;
  
  @Column(name = "cm_country")
  private String cmCountry;
  
  @Column(name = "cm_province")
  private String cmProvince;
  
  @Column(name = "cm_city")
  private String cmCity;
  
  @Column(name = "cm_fax_number")
  private String cmFaxNumber;
  
  @Column(name = "cm_logo")
  private byte[] cmLogo;
  
  @Column(name = "cm_website")
  private String cmWebsite;
  
  @Column(name = "sl_name")
  private String slName;
  
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
  
  @Column(name = "sl_website")
  private String slWebsite;
  
  @Column(name = "sl_photo")
  private byte[] slPhoto;

  public SellerCompany() {
  }

  public SellerCompany(SellerCompanyPK pk) {
    this.sellerCompanyId = pk;
  }

  public SellerCompany(String companyId, String sellerId) {
    this.sellerCompanyId = new SellerCompanyPK(companyId, sellerId);
  }

  public SellerCompanyPK getSellerCompanyId() {
    return sellerCompanyId;
  }

  public void setSellerCompanyId(SellerCompanyPK companyId) {
    this.sellerCompanyId = companyId;
  }

  public String getCmName() {
    return cmName;
  }

  public void setCmName(String cmName) {
    this.cmName = cmName;
  }

  public String getCmRepresentatives() {
    return cmRepresentatives;
  }

  public void setCmRepresentatives(String cmRepresentatives) {
    this.cmRepresentatives = cmRepresentatives;
  }

  public String getCmPhoneNumber() {
    return cmPhoneNumber;
  }

  public void setCmPhoneNumber(String cmPhoneNumber) {
    this.cmPhoneNumber = cmPhoneNumber;
  }

  public String getCmEmail() {
    return cmEmail;
  }

  public void setCmEmail(String cmEmail) {
    this.cmEmail = cmEmail;
  }

  public String getCmAddress() {
    return cmAddress;
  }

  public void setCmAddress(String cmAddress) {
    this.cmAddress = cmAddress;
  }

  public String getCmCountry() {
    return cmCountry;
  }

  public void setCmCountry(String cmCountry) {
    this.cmCountry = cmCountry;
  }

  public String getCmProvince() {
    return cmProvince;
  }

  public void setCmProvince(String cmProvince) {
    this.cmProvince = cmProvince;
  }

  public String getCmCity() {
    return cmCity;
  }

  public void setCmCity(String cmCity) {
    this.cmCity = cmCity;
  }

  public String getCmFaxNumber() {
    return cmFaxNumber;
  }

  public void setCmFaxNumber(String cmFaxNumber) {
    this.cmFaxNumber = cmFaxNumber;
  }

  public byte[] getCmLogo() {
    return cmLogo;
  }

  public void setCmLogo(byte[] cmLogo) {
    this.cmLogo = cmLogo;
  }

  public String getCmWebsite() {
    return cmWebsite;
  }

  public void setCmWebsite(String cmWebsite) {
    this.cmWebsite = cmWebsite;
  }

  public String getSlName() {
    return slName;
  }

  public void setSlName(String slName) {
    this.slName = slName;
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

  public String getSlWebsite() {
    return slWebsite;
  }

  public void setSlWebsite(String slWebsite) {
    this.slWebsite = slWebsite;
  }

  public byte[] getSlPhoto() {
    return slPhoto;
  }

  public void setSlPhoto(byte[] slPhoto) {
    this.slPhoto = slPhoto;
  }

  @Override
  public ProfileType getProfileType() {
    return ProfileType.SELLER_COMPANY;
  }
  
}
