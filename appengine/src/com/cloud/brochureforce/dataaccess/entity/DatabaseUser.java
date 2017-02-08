/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.dataaccess.entity;

/**
 * Tagging interface for registered profiles.
 *
 * @author LIA Solutions SAS / Permalink Group.
 */
public interface DatabaseUser {
  
  /**
   * Profile type of the logged user. Used to define the dashboard options to display.
   * 
   * @return String representation of one of the {@link ProfileType ProfileType} enum.
   */
  public ProfileType getProfileType();
}
