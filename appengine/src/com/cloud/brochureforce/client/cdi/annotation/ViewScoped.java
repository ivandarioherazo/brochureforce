/**
 * 
 */
package com.cloud.brochureforce.client.cdi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.ScopeAnnotation;

/**
 * Having your beans created and injected by Guice implies they will be scoped
 * by Guice too and Guice does know nothing about ViewScope. Luckily it is
 * pretty easy to implement this scope for Guice. The first thing is to create a
 * custom ViewScope annotation (sadly we can't use the original one from JSF).
 * 
 * @author LIA Solutions SAS
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@ScopeAnnotation
public @interface ViewScoped {
}
