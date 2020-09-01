/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hb.kfcenter.kfFlow.rule.bean.LineBean;
import com.hb.kfcenter.kfFlow.rule.bean.ParamBean;
import com.hb.kfcenter.kfFlow.rule.bean.RuleBean;

/**
 * @author zhangds
 *
 */
@Service
public interface FlowWrokRuleService {

	//public List<RuleBean> getCurrentRules(String flowId,String nodeId,String opType);
	public RuleBean getCurrentRule(String flowId,String nodeId,String lineId,String ruleId,String opType);
	public boolean saveRule(String staffno,String flowId,String nodeId,String ruleId,String rulePid,String lineId,String opType,String ruleType);
	public boolean deleCurrentRule(String flowId,String nodeId,String ruleId,String opType);
	public List<ParamBean> getParamsByType(String flowId,String nodeId,String ruleId,String type,String keyword);
	public boolean saveOneRuleParam(String flowId,String nodeId,String ruleId,String opType, String ruleType,String showParam, String realParam,String orderNo);
	public List<LineBean> getCurrentNodeToLineAct(String flowId,String nodeId);
	public List<RuleBean> getCurrentRules(String flowId,String nodeId,String lineId,String opType);
	public String[] getTimeLimitList(String flowId,String nodeId);
	
}
