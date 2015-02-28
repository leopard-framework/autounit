package io.leopard.autounit.rule;

public interface MethodTemplate {

	Object add(UnitMethod unitMethod, boolean log) throws NoSuchMethodRuntimeException;

	Object get(UnitMethod unitMethod, boolean log) throws NoSuchMethodRuntimeException;

	Object delete(UnitMethod unitMethod, boolean log) throws NoSuchMethodRuntimeException;
}
