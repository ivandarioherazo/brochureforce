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
@Table(name = "client", catalog = "brochureforce", schema = "")
public class Client implements Serializable, DatabaseUser {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "cl_id")
  private String clId;
  
  @Column(name = "cl_id_type")
  private String clIdType;
  
  @Column(name = "cl_name")
  private String clName;
  
  @Column(name = "cl_password")
  private String clPassword;
  
  @Column(name = "cl_phone_number")
  private String clPhoneNumber;
  
  @Column(name = "cl_email")
  private String clEmail;
  
  @Column(name = "cl_address")
  private String clAddress;
  
  @Column(name = "cl_country")
  private String clCountry;
  
  @Column(name = "cl_province")
  private String clProvince;
  
  @Column(name = "cl_city")
  private String clCity;
  
  @Column(name = "cl_fax_number")
  private String clFaxNumber;
  
  @Column(name = "cl_photo")
  private byte[] clPhoto;

  public Client() {
  }

  public String getClId() {
    return clId;
  }

  public void setClId(String clId) {
    this.clId = clId;
  }

  public String getClIdType() {
    return clIdType;
  }

  public void setClIdType(String clIdType) {
    this.clIdType = clIdType;
  }

  public String getClName() {
    return clName;
  }

  public void setClName(String clName) {
    this.clName = clName;
  }

  public String getClPassword() {
    return clPassword;
  }

  public void setClPassword(String clPassword) {
    this.clPassword = clPassword;
  }

  public String getClPhoneNumber() {
    return clPhoneNumber;
  }

  public void setClPhoneNumber(String clPhoneNumber) {
    this.clPhoneNumber = clPhoneNumber;
  }

  public String getClEmail() {
    return clEmail;
  }

  public void setClEmail(String clEmail) {
    this.clEmail = clEmail;
  }

  public String getClAddress() {
    return clAddress;
  }

  public void setClAddress(String clAddress) {
    this.clAddress = clAddress;
  }

  public String getClCountry() {
    return clCountry;
  }

  public void setClCountry(String clCountry) {
    this.clCountry = clCountry;
  }

  public String getClProvince() {
    return clProvince;
  }

  public void setClProvince(String clProvince) {
    this.clProvince = clProvince;
  }

  public String getClCity() {
    return clCity;
  }

  public void setClCity(String clCity) {
    this.clCity = clCity;
  }

  public String getClFaxNumber() {
    return clFaxNumber;
  }

  public void setClFaxNumber(String clFaxNumber) {
    this.clFaxNumber = clFaxNumber;
  }

  public byte[] getClPhoto() {
    return clPhoto;
  }

  public void setClPhoto(byte[] clPhoto) {
    this.clPhoto = clPhoto;
  }

  @Override
  public ProfileType getProfileType() {
    return ProfileType.CLIENT;
  }
  
}
