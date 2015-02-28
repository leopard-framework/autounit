package io.leopard.autounit.rule;

import java.lang.reflect.Method;

public interface MethodHuman {

	Method find(Object bean) throws NoSuchMethodRuntimeException;

	Object invoke(UnitMethod unitMethod, boolean log) throws NoSuchMethodRuntimeException;

}
