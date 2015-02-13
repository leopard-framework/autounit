package io.leopard.autounit.rule;

import java.util.ArrayList;
import java.util.List;

public class RuleStateChain {

	private List<RuleState> allList = new ArrayList<RuleState>();
	private List<RuleState> verifiedList = new ArrayList<RuleState>();

	private RuleState lastVerifiedRuleState;

	public void add(RuleState state) {
		allList.add(state);
		if (state.isVerified()) {
			verifiedList.add(state);
		}
		if (state.isVerified()) {
			this.lastVerifiedRuleState = state;
		}
	}

	/**
	 * 获取所有已执行过的规则列表.
	 * 
	 * @return
	 */
	public List<RuleState> allList() {
		return allList;
	}

	/**
	 * 获取已验证的规则列表
	 * 
	 * @return
	 */
	public List<RuleState> verifiedList() {
		return verifiedList;
	}

	public RuleState getLastVerifiedRuleState() {
		return lastVerifiedRuleState;
	}
}
