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
public class PurchaseOrderProductsListPK implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Basic(optional = false)
  @Column(name = "ord_id")
  private String ordId;
  
  @Basic(optional = false)
  @Column(name = "ord_prd_id")
  private String ordPrdId;

  public PurchaseOrderProductsListPK() {
  }

  public PurchaseOrderProductsListPK(String ordId, String ordPrdId) {
    this.ordId = ordId;
    this.ordPrdId = ordPrdId;
  }

  public String getOrdId() {
    return ordId;
  }

  public void setOrdId(String ordId) {
    this.ordId = ordId;
  }

  public String getOrdPrdId() {
    return ordPrdId;
  }

  public void setOrdPrdId(String ordPrdId) {
    this.ordPrdId = ordPrdId;
  }

  @Override
  public int hashCode() {
    return (this.ordId != null ? this.ordId.hashCode() : 0)
      + (this.ordPrdId != null ? this.ordPrdId.hashCode() : 0);
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
    final PurchaseOrderProductsListPK other = (PurchaseOrderProductsListPK) obj;
    if (!Objects.equals(this.ordId, other.ordId)) {
      return false;
    }
    return Objects.equals(this.ordPrdId, other.ordPrdId);
  }

  @Override
  public String toString() {
    return new StringBuilder().
      append("PurchaseOrderProductsListPK [Order ID = ").
      append(this.ordId).append("; Product ID = ").
      append(this.ordPrdId).toString();
  }
}
