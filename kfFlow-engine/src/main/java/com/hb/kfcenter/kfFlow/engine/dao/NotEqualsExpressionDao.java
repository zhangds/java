/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.dao;

import org.apache.commons.lang3.StringUtils;

import com.hb.kfcenter.kfFlow.engine.bean.RuleBean;
import com.hb.kfcenter.kfFlow.engine.service.ExpressionService;

/**
 * @author zhangds
 *
 */
public class NotEqualsExpressionDao implements ExpressionService{

	private String expValue = null;
	private RuleBean conditionBean = null;
	public NotEqualsExpressionDao(String value,RuleBean bean) {
		this.expValue = value;
		this.conditionBean = bean;
	}
	
	@Override
	public boolean runExpression() {
		if (StringUtils.isNotEmpty(expValue) && conditionBean != null 
				&& StringUtils.isNotEmpty(conditionBean.getParaTwo())) {
			String[] _strings = conditionBean.getParaTwo().split(";");
			for (String _string : _strings) {
				if (expValue.equalsIgnoreCase(_string) ) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
