/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hb.kfcenter.kfFlow.rule.bean.WorkGropBean;

/**
 * @author zhangds
 *
 */
@Service
public interface FlowWorkGropService {
	public List<WorkGropBean> getWorkGroupBykeyWord(String keyWords);
	public List<WorkGropBean> getCurrentFlowAndNodeIdByWrokGroups(String flowId,String nodeId);
	public Map<String,String> saveCurrentNodeWrokGroups(String staffno,String flowId,String nodeId,String groupIds);
}
