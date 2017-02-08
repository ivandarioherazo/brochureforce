/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.entity.pk;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ivanh_000
 */
@Embeddable
public class SellerCompanyPK implements Serializable {

  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @Column(name = "company_id")
  private String companyId;

  @Basic(optional = false)
  @Column(name = "seller_id")
  private String sellerId;

  public SellerCompanyPK() {
  }

  public SellerCompanyPK(String companyId, String sellerId) {
    this.companyId = companyId;
    this.sellerId = sellerId;
  }

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  @Override
  public int hashCode() {
    return (this.companyId != null ? this.companyId.hashCode() : 0)
      + (this.sellerId != null ? this.sellerId.hashCode() : 0);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final SellerCompanyPK other = (SellerCompanyPK) obj;
    if (!Objects.equals(this.companyId, other.companyId)) {
      return false;
    }
    return Objects.equals(this.sellerId, other.sellerId);
  }

  @Override
  public String toString() {
    return new StringBuilder().
      append("SellerCompanyPK = [Company ID = ").
      append(this.companyId).append("; Seller ID = ").
      append(this.sellerId).toString();
  }
}
