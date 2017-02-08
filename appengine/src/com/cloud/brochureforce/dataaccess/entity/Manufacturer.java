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
@Table(name = "manufacturer", catalog = "brochureforce", schema = "")
public class Manufacturer implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Basic(optional = false)
  @Column(name = "mf_id")
  private String mfId;

  @Column(name = "mf_name")
  private String mfName;

  @Column(name = "mf_password")
  private String mfPassword;
  
  @Column(name = "mf_phone_number")
  private String mfPhoneNumber;
  
  @Column(name = "mf_email")
  private String mfEmail;
  
  @Column(name = "mf_address")
  private String mfAddress;
  
  @Column(name = "mf_country")
  private String mfCountry;
  
  @Column(name = "mf_province")
  private String mfProvince;
  
  @Column(name = "mf_city")
  private String mfCity;
  
  @Column(name = "mf_fax_number")
  private String mfFaxNumber;
  
  @Column(name = "mf_photo")
  private byte[] mfPhoto;
  
  @Column(name = "mf_representatives")
  private String mfRepresentatives;
  
  @Column(name = "mf_website")
  private String mfWebsite;

  public Manufacturer() {
  }

  public String getMfId() {
    return mfId;
  }

  public void setMfId(String mfId) {
    this.mfId = mfId;
  }

  public String getMfName() {
    return mfName;
  }

  public void setMfName(String mfName) {
    this.mfName = mfName;
  }

  public String getMfPassword() {
    return mfPassword;
  }

  public void setMfPassword(String mfPassword) {
    this.mfPassword = mfPassword;
  }

  public String getMfPhoneNumber() {
    return mfPhoneNumber;
  }

  public void setMfPhoneNumber(String mfPhoneNumber) {
    this.mfPhoneNumber = mfPhoneNumber;
  }

  public String getMfEmail() {
    return mfEmail;
  }

  public void setMfEmail(String mfEmail) {
    this.mfEmail = mfEmail;
  }

  public String getMfAddress() {
    return mfAddress;
  }

  public void setMfAddress(String mfAddress) {
    this.mfAddress = mfAddress;
  }

  public String getMfCountry() {
    return mfCountry;
  }

  public void setMfCountry(String mfCountry) {
    this.mfCountry = mfCountry;
  }

  public String getMfProvince() {
    return mfProvince;
  }

  public void setMfProvince(String mfProvince) {
    this.mfProvince = mfProvince;
  }

  public String getMfCity() {
    return mfCity;
  }

  public void setMfCity(String mfCity) {
    this.mfCity = mfCity;
  }

  public String getMfFaxNumber() {
    return mfFaxNumber;
  }

  public void setMfFaxNumber(String mfFaxNumber) {
    this.mfFaxNumber = mfFaxNumber;
  }

  public byte[] getMfPhoto() {
    return mfPhoto;
  }

  public void setMfPhoto(byte[] mfPhoto) {
    this.mfPhoto = mfPhoto;
  }

  public String getMfRepresentatives() {
    return mfRepresentatives;
  }

  public void setMfRepresentatives(String mfRepresentatives) {
    this.mfRepresentatives = mfRepresentatives;
  }

  public String getMfWebsite() {
    return mfWebsite;
  }

  public void setMfWebsite(String mfWebsite) {
    this.mfWebsite = mfWebsite;
  }
}
