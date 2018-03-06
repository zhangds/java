/**   
 * @Title: RoleDao.java 
 * @Package org.interestTeam.model.models 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月23日 下午11:20:42 
 * @version V1.0  
 */
package org.interestTeam.model.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @ClassName: RoleDao 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月23日 下午11:20:42 
 *  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDao {

	String roleId,parentRoleId,roleName,createUserId,createTime;
	int roleIndex;
	List<RoleDao> children;
}
