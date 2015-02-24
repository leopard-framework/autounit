package io.leopard.autounit.rule.human;

import java.lang.reflect.Method;
import java.util.Map;

public interface MethodHuman {

	Method find() throws Exception;

	Object invokeAndAssert(Map<String, String> tson, String[] names, Object[] args) throws Exception;
}
