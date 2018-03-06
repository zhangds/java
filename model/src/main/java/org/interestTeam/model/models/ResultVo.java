/**   
 * @Title: ResultVo.java 
 * @Package org.interestTeam.model.models 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月25日 上午10:41:23 
 * @version V1.0  
 */
package org.interestTeam.model.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @ClassName: ResultVo 
 * @Description: 统一返回结果数据
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月25日 上午10:41:23 
 *  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo {

	boolean success = false ;
	String msg;
	Object resultData ;
	String errorMsg;
}
