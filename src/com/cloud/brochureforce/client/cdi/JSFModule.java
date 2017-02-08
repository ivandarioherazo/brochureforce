/**
 * 
 */
package com.cloud.brochureforce.client.cdi;

import javax.faces.webapp.FacesServlet;
import javax.inject.Singleton;

import com.cloud.brochureforce.client.cdi.annotation.ViewScoped;
import com.cloud.brochureforce.client.cdi.annotation.ViewScopedImpl;
import com.cloud.brochureforce.client.cdi.listener.PostConstructTypeListener;
import com.cloud.brochureforce.client.jsf.FacesHttpServlet;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.ServletModule;

/**
 * DI setup for JSF.
 * 
 * @author LIA Solutions SAS
 */
public class JSFModule extends ServletModule {
	private void businessLogicBindings() {
		bind(InjectorRegistry.VISITS_HANDLER).toInstance(InjectorRegistry.VISITS_HANDLER_IMPL);
    bind(InjectorRegistry.SESSION_MANAGER).toInstance(InjectorRegistry.SESSION_MANAGER_IMPL);
	}

	private void systemBindings() {
		// Add support for the @PostConstruct annotation for Guice-injected
		// objects.
		bindListener(Matchers.any(), new PostConstructTypeListener(null));

		// Binding a custom implementation of "@ViewScoped" scope.
		bindScope(ViewScoped.class, new ViewScopedImpl());
	}

	private void jsfBindings() {
		// Define and bind MyFaces' Servlet as singleton object
		// so it can be injected in FacesHttpServlet's constructor.
		bind(FacesServlet.class).in(Singleton.class);

		// JSF patterns to be served by FacesHttpServlet.
		for (String urlPattern : InjectorRegistry.JSF_SERVLET_URL_PATTERNS) {
			serve(urlPattern).with(FacesHttpServlet.class);
		}
	}

	@Override
	protected void configureServlets() {
		// Guice injector bindings.
		this.systemBindings();
		this.businessLogicBindings();
		this.jsfBindings();
	}
}
