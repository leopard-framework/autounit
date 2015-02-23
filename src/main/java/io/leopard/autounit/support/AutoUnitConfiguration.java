package io.leopard.autounit.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.TestExecutionListeners;

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@TestExecutionListeners({ AutoUnitTestExecutionListener.class })
public @interface AutoUnitConfiguration {

}
