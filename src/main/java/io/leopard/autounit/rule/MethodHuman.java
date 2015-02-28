package io.leopard.autounit.rule;

import java.lang.reflect.Method;
import java.util.Map;

public interface MethodHuman {

	Method find(Object bean) throws NoSuchMethodException;

	Object invoke(UnitMethod unitMethod, boolean log) throws NoSuchMethodException;

}
