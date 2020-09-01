/**
 * 
 */
package com.hb.kfcenter.kfFlow.design.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hb.kfcenter.kfFlow.design.bean.WorkFlowBean;

/**
 * @author zhangds
 *
 */
@Service
public interface FlowManagerService {

	public int getAllCounts(String staffno, String wkId, String wkName, String wkRemark);
	public List<WorkFlowBean> getCurrentPageDatas(int currentNum,int pageSize, String wkId, String wkName,String staffno,String wkRemark);
	public WorkFlowBean getCurrentWorkFlowInfo(String flowId);
	public WorkFlowBean getDefaultNewWorkFlowInfo(String staffno);
	public Map<String,String> getAllStates();
	public boolean saveWorkFlow(WorkFlowBean dao);
	public boolean updateWorkFlow(WorkFlowBean dao);
	public String getFlowJson(String flowId);
	public boolean saveDesignWorkflow(String flowId,String flowJson,String staffno);
	
}
