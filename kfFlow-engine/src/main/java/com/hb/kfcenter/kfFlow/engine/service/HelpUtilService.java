/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hb.kfcenter.kfFlow.engine.bean.RuleBean;

/**
 * @author zhangds
 *
 */
@Service
public interface HelpUtilService {

	public Map<String,String> getJsonStringToLevelMap(String jsonString);
	
	public boolean parseToExpression(RuleBean bean,List<RuleBean> list,Map<String,String> paramJson);
	
}
