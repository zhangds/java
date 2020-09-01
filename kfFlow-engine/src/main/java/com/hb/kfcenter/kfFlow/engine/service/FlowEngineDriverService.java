/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hb.kfcenter.kfFlow.engine.bean.FlowInitBean;
import com.hb.kfcenter.kfFlow.engine.bean.NodeBean;

/**
 * @author zhangds
 *
 */
@Service
public interface FlowEngineDriverService {

	/**
	 * Description: 校验及驱动工作
	 */
	public Map<String, Object> checkAndDriverToNext(int flag,FlowInitBean flowInit,NodeBean currentNode,
			String staffno,String userGroupId,String flowId,String nodeId,String lineId,
			String workCaseId,String serviceId,String paramJson);
}
