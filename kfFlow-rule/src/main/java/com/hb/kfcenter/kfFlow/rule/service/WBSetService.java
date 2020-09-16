/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hb.kfcenter.kfFlow.rule.bean.WbBean;

/**
 * @author zhangds
 *
 */
@Service
public interface WBSetService {

	public List<WbBean> getWbExtendConfig();
	
	public String[] getCurrentNodeWbExtend(String flowId,String nodeId);
	
	public Map<String,Object> setCurrentNodeWbExtendConfig(String flowId,String nodeId,String sysId,String mothodId,String classZ,String pdIds);
}
