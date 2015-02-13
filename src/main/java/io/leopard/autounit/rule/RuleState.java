package io.leopard.autounit.rule;

/**
 * 规则执行状态
 * 
 * @author 阿海
 *
 */
public class RuleState {

	private boolean verified;// 是否已验证
	private MethodRule rule;

	private Object result;// 方法调用返回值
	private boolean stop;// 是否终止

	public RuleState(MethodRule rule, Object result) {
		this(rule, result, true);
	}

	public RuleState(MethodRule rule, Object result, boolean verified) {
		this(rule, result, verified, false);
	}

	public RuleState(MethodRule rule, Object result, boolean verified, boolean stop) {
		this.rule = rule;
		this.result = result;
		this.verified = verified;
		this.stop = stop;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public MethodRule getRule() {
		return rule;
	}

	public void setRule(MethodRule rule) {
		this.rule = rule;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
