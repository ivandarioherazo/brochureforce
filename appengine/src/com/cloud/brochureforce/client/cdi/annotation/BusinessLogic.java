/**
 * 
 */
package com.cloud.brochureforce.client.cdi.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

/**
 * Guice binding annotation for business logic interfaces.
 * 
 * @author LIA Solutions SAS
 */
@Documented
@BindingAnnotation
@Retention(RUNTIME)
@Target({ TYPE })
public @interface BusinessLogic {
}
