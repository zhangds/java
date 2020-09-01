/**   
 * @Title: NodePojo.java 
 * @Package com.hb.kfcenter.kfFlow.web.models 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2019年1月30日 上午11:31:26 
 * @version V1.0  
 */
package com.hb.kfcenter.kfFlow.design.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @ClassName: NodePojo 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2019年1月30日 上午11:31:26 
 *  
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeBean {
	private String  key ,flowId,name , type;
	private int top=0 ,left=0,width=0,height=0;
	private boolean alt = true;
	
	@Override
	public String toString(){
		StringBuffer _sbf = new StringBuffer("\"");
		_sbf.append(key).append("\":{");
		_sbf.append("\"name\":\"").append(name);
		_sbf.append("\",\"left\":").append(left);
		_sbf.append(",\"top\":").append(top);
		_sbf.append(",\"type\":\"").append(type);
		_sbf.append("\",\"width\":").append(width);
		_sbf.append(",\"height\":").append(height);
		_sbf.append(",\"alt\":").append(String.valueOf(alt));
		_sbf.append("}");
		return _sbf.toString();
	}
}
