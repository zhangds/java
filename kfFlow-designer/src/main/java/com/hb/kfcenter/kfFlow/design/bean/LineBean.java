/**   
 * @Title: LinePojo.java 
 * @Package com.hb.kfcenter.kfFlow.web.models 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2019年1月30日 上午11:32:08 
 * @version V1.0  
 */
package com.hb.kfcenter.kfFlow.design.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @ClassName: LinePojo 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2019年1月30日 上午11:32:08 
 *  
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineBean {
	private String key,flowId,name,from,to,type;
	private boolean alt= true;

	@Override
	public String toString(){
		StringBuffer _sbf = new StringBuffer("\"");
		_sbf.append(key).append("\":{");
		_sbf.append("\"name\":\"").append(name);
		_sbf.append("\",\"type\":\"").append(type);
		_sbf.append("\",\"from\":\"").append(from);
		_sbf.append("\",\"to\":\"").append(to);
		_sbf.append("\",\"alt\":").append(String.valueOf(alt));
		_sbf.append("}");
		return _sbf.toString();
	}
}
