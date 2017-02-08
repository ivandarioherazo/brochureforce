/**
 *
 */
package com.cloud.brochureforce.client.cdi;

import com.cloud.brochureforce.logic.impl.*;
import com.cloud.brochureforce.logic.*;

/**
 * This class separates all data needed to set up the DI bindings for the Guice
 * injector. ALL DI objects to be injected MUST BE REGISTERED IN THIS CLASS!
 *
 * @author LIA Solutions SAS.
 */
abstract class InjectorRegistry {

  // Url patterns for FacesServlet, as it would be defined in web.xml
  static String[] JSF_SERVLET_URL_PATTERNS = new String[]{"*.jsf", "*.xhtml"};

  /////////////////////////////////////////////////////////////////////
  // BUSINESS LOGIC OBJECTS.
  /////////////////////////////////////////////////////////////////////
  // Example: visits handler.
  static Class<IVisitsHandler> VISITS_HANDLER = IVisitsHandler.class;
  static IVisitsHandler VISITS_HANDLER_IMPL = new VisitsHandlerImpl();

  // Session handler
  static Class<ISessionManager> SESSION_MANAGER = ISessionManager.class;
  static ISessionManager SESSION_MANAGER_IMPL = new SessionManagerImpl();
  ///////////////////////////////////////////////////////////////////// 
}
