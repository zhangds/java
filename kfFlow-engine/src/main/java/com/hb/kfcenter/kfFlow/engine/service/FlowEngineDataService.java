/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hb.kfcenter.kfFlow.engine.bean.ActBean;
import com.hb.kfcenter.kfFlow.engine.bean.BackFormEleBean;
import com.hb.kfcenter.kfFlow.engine.bean.FlowInitBean;
import com.hb.kfcenter.kfFlow.engine.bean.GroupBean;
import com.hb.kfcenter.kfFlow.engine.bean.NodeBean;
import com.hb.kfcenter.kfFlow.engine.bean.RuleBean;


/**
 * @author zhangds
 *
 */
@Service
public interface FlowEngineDataService {

	/**
     * Description: 获取工作流所有节点
     */
	public List<NodeBean> getNodesByFlowId(String flowId);
	/**
	 * Description: 获取工作流所有连线行为
	*/
	public List<ActBean> getActsByFlowId(String flowId);
	/**
	 * Description: 获取工作流当前实例
	*/
	public FlowInitBean getWorkFlowInitByFormId(String formId);
	/**
	 * Description: 获取工作流节点设置的工作组列表
	*/
	public List<GroupBean> getCurrentNodeWKGroupsByFlowIdAndNodeId(String flowId,String nodeId);
	/**
	 * Description: 获取当前节点类型规则
	*/
	public List<RuleBean> getCurrentNodeRulesByType(String flowId,String nodeId,String optype);
	
	public List<BackFormEleBean> getAllNodeBackFormSets(String flowId,String nodeId);
	
	public List<RuleBean> getAllNodeBackFormRules(String flowId,String nodeId);
	
	public Map<String,String> addOtherValue(String workCaseId,String serviceId,Map<String,String>jsonMap);
	
	public String[] getWBExtend(String flowId,String nodeId);
	
}
