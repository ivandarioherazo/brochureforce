/**
 * 
 */
package com.cloud.brochureforce.client.cdi.annotation;

import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Scopes;

/**
 * View scoped beans are placed into the UIRoot's viewMap just like session
 * scoped beans are placed into the current session's attribute map. So the
 * following is a simple copy of Guice's own SessionScope implementation with
 * slight modifications to access the view map.
 * 
 * @author LIA Solutions SAS
 */
public class ViewScopedImpl implements Scope {
    private enum NullObject {
        INSTANCE
    }

    @Override
    public <T> Provider<T> scope(Key<T> key, final Provider<T> unscoped) {
        final String name = key.toString();
        return new Provider<T>() {

            @Override
            public T get() {
                final FacesContext facesContext = FacesContext.getCurrentInstance();
                final UIViewRoot viewRoot = facesContext.getViewRoot();

                // fallback if no view is active. Consider to throw an exception 
                // (you might also want to check whether there actually is a FacesContext 
                // available to prevent injecting your beans outside a valid view scope)
                if (viewRoot == null) {
                    return unscoped.get();
                }

                final Map<String, Object> viewMap = viewRoot.getViewMap(true);
                synchronized (viewMap) {
                    Object obj = viewMap.get(name);
                    if (obj == NullObject.INSTANCE) {
                        return null;
                    }

                    @SuppressWarnings("unchecked")
                    T t = (T) obj;
                    if (t == null) {
                        t = unscoped.get();
                        if (!Scopes.isCircularProxy(t)) {
                            viewRoot.getViewMap().put(name, t == null
                                    ? NullObject.INSTANCE
                                    : t);
                        }
                    }
                    return t;
                }
            }

            @Override
            public String toString() {
                return String.format("%s[%s]", unscoped, ViewScopedImpl.this);
            }

        };
    }

    @Override
    public final String toString() {
        return "Custom.ViewScope (JsfViewScopeImpl)";
    }}
