/**   
 * @Title: BigScreenService.java 
 * @Package com.powerbridge.manifest.manifestFrame.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月4日 上午10:23:35 
 * @version V1.0  
 */
package com.powerbridge.manifest.manifestFrame.service;

import java.util.Map;

import org.springframework.stereotype.Service;

/** 
 * @ClassName: BigScreenService 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月4日 上午10:23:35 
 *  
 */
@Service
public interface BigScreenService {

	public Map<String,String> getScreenOneData() throws Exception;
}
