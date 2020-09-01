/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hb.kfcenter.kfFlow.rule.bean.BackFormBean;

/**
 * @author zhangds
 *
 */
@Service
public interface BackFormService {

	public List<BackFormBean> getCurrentNodeBackFormList(String flowId,String nodeId);
	/**
	 * 获取所有回单模板信息
	 */
	public List<String[]> getAllBackFormList() throws Exception;
	
	/**
	 * 获取所有回单模板信息
	 */
	public List<BackFormBean> getBackFormById(String keyId) throws Exception;
	
	public Map<String,Object> saveBackFormsRule(String staffno,String flowId,String nodeId,String backFormId,String backFromJson,String zrdxJson);
}
