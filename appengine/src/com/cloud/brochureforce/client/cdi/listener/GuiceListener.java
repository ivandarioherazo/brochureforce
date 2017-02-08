package com.cloud.brochureforce.client.cdi.listener;

import java.util.HashMap;

import com.cloud.brochureforce.client.cdi.JSFModule;
import com.cloud.brochureforce.client.cdi.JSFInjectionProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Servlet-listener to activate Dependency Injection.
 *
 * @author LIA Solutions SAS.
 * @see This file: <a href=
 *      "https://raw.githubusercontent.com/zach-m/gae-jersey-guice-jsf/jsf-primefaces/src/main/java/com/tectonica/guice/GuiceListener.java">
 * GuiceListener.java</a>
 */
public class GuiceListener extends GuiceServletContextListener {

  protected AbstractModule module;
  private static Injector INJECTOR;
  private static final HashMap<String, Object> INJECTED_OBJECTS;
  
  static {
    // Object map to ensure that we inject a unique instance.
    INJECTED_OBJECTS = new HashMap<>();

    // Create the injector.
    if (INJECTOR == null) {
      INJECTOR = Guice.createInjector(new JSFModule());
    }
  }
  
  @Override
  public Injector getInjector() {
    return INJECTOR;
  }

  /**
   * Given an injectable instance, injects its dependencies and make sure to
   * only inject one.
   * @param instance The object that is going to be injected.
   */
  public static void injectMembers(Object instance) {
    Object obj = null;
    if (JSFInjectionProvider.isBusinessLogicObject(obj)) {
      String instanceClassName = instance.getClass().getName();
      Object mappedInstance = INJECTED_OBJECTS.get(instanceClassName);
      if (mappedInstance == null) {
        // It's a new bean instance. It's stored in the bean map
        // to be able to reuse it.
        INJECTED_OBJECTS.put(instanceClassName, instance);
        obj = instance;
      } else {
        // There's already a bean instance. Let's reuse it!.
        obj = mappedInstance;
      }
    } else { // it should be a managed bean.
      obj = instance;
    }
    INJECTOR.injectMembers(obj);
  }
}
