/**
 * 
 */
package com.cloud.brochureforce.client.cdi;

import javax.faces.bean.ManagedBean;

import com.cloud.brochureforce.client.cdi.annotation.BusinessLogic;
import com.cloud.brochureforce.client.cdi.listener.GuiceListener;
import com.sun.faces.spi.InjectionProviderException;
import com.sun.faces.vendor.WebContainerInjectionProvider;

/**
 * An optional injector for Mojarra-based applications where dependency
 * injection is required into the {@code @ManagedBean}s. It extends
 * {@link WebContainerInjectionProvider}, which normally handles invocations of
 * {@code @PostConstruct} and {@code @PreDestroy}, by also adding
 * dependency-injection for {@code @ManagedBean}s using the Guice injector
 * created in {@link GuiceListener}. This creator, by the way, also handles
 * {@code @PostConstruct} methods, so we make sure to avoid double invocation
 * here.
 * <p>
 * To use, add the following paragraph to {@code web.xml} alongside your other
 * JSF configuration:
 * 
 * <pre>
 * &lt;context-param&gt;
 *    &lt;param-name&gt;com.sun.faces.injectionProvider&lt;/param-name&gt;
 *    &lt;param-value&gt;com.cloud.brochureforce.client.cdi.JSFInjectionProvider&lt;/param-value&gt;
 * &lt;/context-param&gt;
 * </pre>
 * 
 * <b>NOTE:</b> make sure your {@link GuiceListener}-subclass is an active
 * listener in the {@code web.xml}, or NullPointerExceptions will be thrown
 * <p>
 * 
 * @author LIA Solutions SAS
 * @see This file: <a href=
 *      "https://raw.githubusercontent.com/zach-m/gae-jersey-guice-jsf/jsf-primefaces/src/main/java/com/tectonica/guice/GuiceJsfInjector.java">
 *      GuiceJsfInjector.java</a>
 */
public class JSFInjectionProvider extends WebContainerInjectionProvider {
	@Override
	public void inject(Object obj) throws InjectionProviderException {
		if (isManagedBean(obj) || isBusinessLogicObject(obj)) {
			GuiceListener.injectMembers(obj);
		}
	}

	/**
	 * As an arbitrary choice, the choice here is to inject only into
	 * {@code @ManagedBean} instances, so that other classes - not written by us
	 * - wouldn't be injected too. This choice could be altered.
	 * 
	 * @param obj
	 *            A JSF bean instance (annotated with @ManagedBean).
	 * @return
	 */
	private boolean isManagedBean(Object obj) {
		return obj != null && obj.getClass().getAnnotation(ManagedBean.class) != null;
	}
	
	public static boolean isBusinessLogicObject(Object obj) {
		return obj != null && obj.getClass().getAnnotation(BusinessLogic.class) != null;
	}
}
