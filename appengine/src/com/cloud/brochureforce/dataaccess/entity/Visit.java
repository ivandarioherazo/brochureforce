/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "visits", catalog = "brochureforce", schema = "")
public class Visit implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "visit_id")
  private Long visitId;
  
  @Basic(optional = false)
  @Column(name = "user_ip")
  private String userIp;
  
  @Basic(optional = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  public Visit() {
  }

  public Visit(Long visitId) {
    this.visitId = visitId;
  }

  public Visit(Long visitId, String userIp, Date timestamp) {
    this.visitId = visitId;
    this.userIp = userIp;
    this.timestamp = timestamp;
  }

  public Long getVisitId() {
    return visitId;
  }

  public void setVisitId(Long visitId) {
    this.visitId = visitId;
  }

  public String getUserIp() {
    return userIp;
  }

  public void setUserIp(String userIp) {
    this.userIp = userIp;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (visitId != null ? visitId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Visit)) {
      return false;
    }
    Visit other = (Visit) object;
    if ((this.visitId == null && other.visitId != null) || (this.visitId != null && !this.visitId.equals(other.visitId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Visit[ Id = " + visitId + ", User IP = " + this.userIp + ", date = " + this.timestamp.toString() + "]";
  } 
}