/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.entity;

import com.cloud.brochureforce.dataaccess.entity.pk.DbSessionPK;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ivanh_000
 */
@Entity
@Table(name = "db_session", catalog = "brochureforce", schema = "")
public class DbSession implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @EmbeddedId
  protected DbSessionPK dbSessionPK;

  public DbSession() {
  }

  public DbSession(DbSessionPK dbSessionPK) {
    this.dbSessionPK = dbSessionPK;
  }

  public DbSession(String jsessionId, String jobjectPtr) {
    this.dbSessionPK = new DbSessionPK(jsessionId, jobjectPtr);
  }

  public DbSessionPK getDbSessionPK() {
    return dbSessionPK;
  }

  public void setDbSessionPK(DbSessionPK dbSessionPK) {
    this.dbSessionPK = dbSessionPK;
  }

}
