/**   
 * @Title: RoleEntity.java 
 * @Package org.interestTeam.model.database.entity 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月23日 下午3:44:47 
 * @version V1.0  
 */
package org.interestTeam.model.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @ClassName: RoleEntity 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月23日 下午3:44:47 
 *  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

	String roleId,parentRoleId,roleName,createUserId,createTime;
	int roleIndex;
}
