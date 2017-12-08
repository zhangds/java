/**   
 * @Title: LoginStateVo.java 
 * @Package org.interestTeam.model.models 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 下午10:30:26 
 * @version V1.0  
 */
package org.interestTeam.model.models;

import lombok.Data;

/** 
 * @ClassName: LoginStateVo 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月7日 下午10:30:26 
 *  
 */
@Data
public class LoginStateVo {

	private boolean success=false;
	private String msg;
}
