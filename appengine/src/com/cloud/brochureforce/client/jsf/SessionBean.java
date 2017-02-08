/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.client.jsf;

import com.cloud.brochureforce.client.jsf.navigation.PageNavigator;
import com.cloud.brochureforce.client.jsf.navigation.PageNavigator.Page;
import com.cloud.brochureforce.dataaccess.entity.DatabaseUser;
import com.cloud.brochureforce.dataaccess.entity.SessionObject;
import com.cloud.brochureforce.dataaccess.entity.ProfileType;
import com.cloud.brochureforce.logic.ISessionManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 * This bean handles sessions in the application.
 *
 * @author LIA Solutions SAS
 */
@ManagedBean(name = "jsfbSession")
public class SessionBean {

  @Inject
  private ISessionManager sessionManager;

  private String companyId;
  private String userId;
  private String password;
  private String profileType;

  public List<ProfileType> getProfileTypesList() {
    List<ProfileType> profileTypesList = new ArrayList<>();
    profileTypesList.addAll(Arrays.asList(ProfileType.values()));
    return profileTypesList;
  }

  /**
   * Validates user data and creates a session if credentials are valid.
   *
   * @return Next JSF view to display.
   */
  public String fireLogin() {
    String prfType = this.profileType == null ? "" : this.profileType;
    String cmpId = this.companyId == null ? "" : this.companyId;
    String usrId = this.userId == null ? "" : this.userId;
    String pwd = this.password == null ? "" : this.password;
    this.sessionManager.createSession(prfType, cmpId, usrId, pwd);
    return PageNavigator.getInstance().getJsfView(Page.VALIDATE_LOGIN);
  }

  /**
   * Returns profile type. Useful to redirect to correct dashboard right after
   * login validation.
   * 
   * @return String representation of
   * {@link com.cloud.brochureforce.dataaccess.entity.ProfileType ProfileType}
   * enumeration.
   */
  public String getLoggedUserProfileType() {
    DatabaseUser user = (DatabaseUser) this.sessionManager.getSession().getAttribute(SessionObject.SESSION_ATTRIBUTE_NAME);
    return user.getProfileType().toString();
  }

  /**
   * Closes the current active session.
   *
   * @return Sign in page.
   */
  public String fireLogOutEvent() {
    this.sessionManager.closeSession();
    return PageNavigator.getInstance().getJsfPageUrl(Page.LOGIN);
  }

  public String getProfileType() {
    return profileType;
  }

  public void setProfileType(String profileType) {
    this.profileType = profileType;
  }

  public String getCompanyId() {
    return this.companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
