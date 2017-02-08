/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ivanh_000
 */
@Entity
@Table(name = "company", catalog = "brochureforce", schema = "")
public class Company implements Serializable, DatabaseUser {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "cm_id")
  private String cmId;
  
  @Column(name = "cm_name")
  private String cmName;

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

  @Column(name = "cm_password")
  private String cmPassword;

  @Column(name = "cm_representatives")
  private String cmRepresentatives;

  @Column(name = "cm_website")
  private String cmWebsite;

  public Company() {
  }

  public String getCmId() {
    return cmId;
  }

  public void setCmId(String cmId) {
    this.cmId = cmId;
  }

  public String getCmName() {
    return cmName;
  }

  public void setCmName(String cmName) {
    this.cmName = cmName;
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

  public String getCmPassword() {
    return cmPassword;
  }

  public void setCmPassword(String cmPassword) {
    this.cmPassword = cmPassword;
  }

  public String getCmRepresentatives() {
    return cmRepresentatives;
  }

  public void setCmRepresentatives(String cmRepresentatives) {
    this.cmRepresentatives = cmRepresentatives;
  }

  public String getCmWebsite() {
    return cmWebsite;
  }

  public void setCmWebsite(String cmWebsite) {
    this.cmWebsite = cmWebsite;
  }

  @Override
  public ProfileType getProfileType() {
    return ProfileType.COMPANY;
  }
  
}
