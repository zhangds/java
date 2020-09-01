package com.hb.kfcenter.kfFlow.engine.dao;

import org.apache.commons.lang3.StringUtils;

import com.hb.kfcenter.kfFlow.engine.bean.RuleBean;
import com.hb.kfcenter.kfFlow.engine.service.ExpressionService;

public class NotLikeExpressionDao implements ExpressionService{

	private String expValue = null;
	private RuleBean conditionBean = null;
	public NotLikeExpressionDao(String value,RuleBean bean) {
		this.expValue = value;
		this.conditionBean = bean;
	}
	
	@Override
	public boolean runExpression() {
		if (StringUtils.isNotEmpty(expValue) && conditionBean != null 
				&& StringUtils.isNotEmpty(conditionBean.getParaTwo())) {
			String[] _strings = conditionBean.getParaTwo().split(";");
			for (String _string : _strings) {
				if (expValue.contains(_string) ) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
