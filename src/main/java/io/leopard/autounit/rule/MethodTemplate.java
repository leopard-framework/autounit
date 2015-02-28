package io.leopard.autounit.rule;

public interface MethodTemplate {

	Object add(UnitMethod unitMethod, boolean log) throws NoSuchMethodException;

	Object get(UnitMethod unitMethod, boolean log) throws NoSuchMethodException;

	Object delete(UnitMethod unitMethod, boolean log) throws NoSuchMethodException;
}
