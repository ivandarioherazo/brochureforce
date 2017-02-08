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
public class DbSessionPK implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Basic(optional = false)
  @Column(name = "jsession_id")
  private String jsessionId;

  @Basic(optional = false)
  @Column(name = "jobject_ptr")
  private String jobjectPtr;

  public DbSessionPK() {
  }

  public DbSessionPK(String jsessionId, String jobjectPtr) {
    this.jsessionId = jsessionId;
    this.jobjectPtr = jobjectPtr;
  }

  public String getJsessionId() {
    return jsessionId;
  }

  public void setJsessionId(String jsessionId) {
    this.jsessionId = jsessionId;
  }

  public String getJobjectPtr() {
    return jobjectPtr;
  }

  public void setJobjectPtr(String jobjectPtr) {
    this.jobjectPtr = jobjectPtr;
  }

  @Override
  public int hashCode() {
    return (this.jsessionId != null ? this.jsessionId.hashCode() : 0)
      + (this.jobjectPtr != null ? this.jobjectPtr.hashCode() : 0);
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
    final DbSessionPK other = (DbSessionPK) obj;
    if (!Objects.equals(this.jsessionId, other.jsessionId)) {
      return false;
    }
    return Objects.equals(this.jobjectPtr, other.jobjectPtr);
  }

  @Override
  public String toString() {
    return "DbSessionPK[ jsessionId=" + jsessionId + ", jobjectPtr=" + jobjectPtr + " ]";
  }

}
