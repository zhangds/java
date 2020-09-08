/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.service;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author zhangds
 *
 */
@Service
public interface FlowEngineService {
	/**
	 * Description: 获取工作流节点
	*/
	public Map<String,Object> getCurrentStepInfo(String workCaseId,String flowId,String serviceId,String paramJson);
	/**
	 * Description: 获取工作流节点的工作组及属性
	*/
	//public List<GroupBean> getNodeWkGroupById(String flowId,String nodeId,List<NodeBean> nodeslist,List<ActBean> actsList);
	/**
	 * Description: 驱动工作流实例到下一步
	*/
	public Map<String,Object> toNextStep(String staffno,String workCaseId,String flowId,String nodeId,String lineId,String serviceId,String userGroupId,String paramJson);
	
	public Map<String,Object> getFlowNodeGroup(String staffno,String workCaseId,String flowId,String nodeId,String serviceId,String paramJson);
	
	public Map<String,Object> setBackOption(String staffno,String workCaseId,int step);
}
