/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.util;

import com.hb.kfcenter.kfFlow.engine.bean.RuleBean;
import com.hb.kfcenter.kfFlow.engine.dao.EqualsExpressionDao;
import com.hb.kfcenter.kfFlow.engine.dao.LikeExpressionDao;
import com.hb.kfcenter.kfFlow.engine.dao.NotEqualsExpressionDao;
import com.hb.kfcenter.kfFlow.engine.dao.NotLikeExpressionDao;
import com.hb.kfcenter.kfFlow.engine.service.ExpressionService;

/**
 * @author zhangds
 *
 */
public class LogicMath {
	
	public static ExpressionService init(String value,RuleBean bean) {
		if (bean != null) {
			if ("==".equals( bean.getParaOne() )) {
				return new EqualsExpressionDao(value,bean);
			}else if ("!=".equals( bean.getParaOne() )) {
				return new NotEqualsExpressionDao(value,bean);
			}else if ("like".equals( bean.getParaOne() )) {
				return new LikeExpressionDao(value,bean);
			}else if ("not like".equals( bean.getParaOne() )) {
				return new NotLikeExpressionDao(value,bean);
			}
		}
		return null;
	}
}
