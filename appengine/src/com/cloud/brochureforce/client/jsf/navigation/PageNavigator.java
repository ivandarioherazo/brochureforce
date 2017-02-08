/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud.brochureforce.client.jsf.navigation;

import com.cloud.brochureforce.exception.PageNavigationException;
import java.io.IOException;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * This class handles page navigation.
 *
 * @author LIA Solutions SAS.
 */
public final class PageNavigator {

  /**
   * This enumeration provides identifiers for every page of the application.
   */
  public static enum Page {
    /**
     * Login page.
     */
    LOGIN,
    /**
     * Login validation page.
     */
    VALIDATE_LOGIN,
    /**
     * Log out page.
     */
    LOGOUT;
  }

  private final Properties pageUrls;
  private static PageNavigator SINGLETON_INSTANCE;
  private static String CONTEXT_PATH;

  // It can't be instantiated.
  private PageNavigator() {
    this.pageUrls = new Properties();
    try {
      this.pageUrls.load(PageNavigator.class.getResourceAsStream(
        "/com/cloud/brochureforce/client/jsf/navigation/PageUrls.properties"));
    } catch (IOException ex) {
      throw new PageNavigationException(ex.getMessage());
    }
  }

  private void checkContextPath() {
    FacesContext jsfCtx = FacesContext.getCurrentInstance();
    if (jsfCtx != null && CONTEXT_PATH == null) {
      CONTEXT_PATH = jsfCtx.getExternalContext().getRequestContextPath();
    }
  }

  /**
   * It gets the singleton instance of the page navigator.
   *
   * @return Page navigator instance.
   */
  public static PageNavigator getInstance() {
    if (SINGLETON_INSTANCE == null) {
      SINGLETON_INSTANCE = new PageNavigator();
    }
    return SINGLETON_INSTANCE;
  }

  /**
   * Dispatchs the current request to another resource (like a JSF page or
   * servlet) with all its parameters and attributes.
   *
   * @param request The current request object.
   * @param response The response object.
   * @param forwardToPage One of the {@link Page Page} constants, which
   * represent one of the registered pages of the system.
   * @param pageParamValues Array with any parameter values needed by the
   * resource. It can be null if the resource doesn't receive any parameters.
   * @throws PageNavigationException If one of these anomalies occur:
   * <ol>
   * <li>If the target resource throws either a <code>java.io.IOException</code>
   * or a <code>javax.servlet.ServletException</code>.</li>
   * <li>If the response was already committed.</li>
   * </ol>
   */
  public void dispatchToPage(ServletRequest request, ServletResponse response,
    Page forwardToPage, Object... pageParamValues)
    throws PageNavigationException {
    try {
      String pageUrl = this.getJsfPageUrl(forwardToPage);
      //request.getRequestDispatcher(pageUrl).forward(request, response);
      ((HttpServletResponse) response).sendRedirect(pageUrl);
    } catch (IOException | IllegalStateException e) {
      throw new PageNavigationException(e.getMessage());
    }
  }

  /**
   * Gets the page URL referenced by the {@link Page Page} constant which was
   * passed on as an argument. Used to invoke non-jsf resources, like servlets.
   *
   * @param pageId One of the constants of the {@link Page Page} enumeration.
   * @return The full URL of the page.
   */
  public String getPageUrl(Page pageId) {
    this.checkContextPath();
    StringBuilder sb = new StringBuilder(CONTEXT_PATH);
    return sb.append(this.pageUrls.getProperty(pageId.toString())).toString();
  }

  /**
   * Gets the JSF page URL referenced by the {@link Page Page} constant which
   * was passed on as an argument. It only appends "*.jsf" extension to the URL
   * returned by the {@link getPageUrl(Page) getPageUrl(Page)} method.
   *
   * @param pageId One of the constants of the {@link Page Page} enumeration.
   * @return The full URL of the page with "*.jsf" extension.
   */
  public String getJsfPageUrl(Page pageId) {
    return this.getPageUrl(pageId) + ".jsf";
  }

  /**
   * Gets the value of one of the JSF page IDs that have been defined on the
   * navigation properties file. This method will be used for implicit
   * navigation.
   *
   * @param pageId One of the constants of the {@link Page Page} enumeration.
   * @return The JSF view.
   */
  public String getJsfView(Page pageId) {
    return this.pageUrls.getProperty(pageId.toString());
  }
}
