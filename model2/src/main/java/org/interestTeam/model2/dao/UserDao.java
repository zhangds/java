/**   
 * @Title: UserDao.java 
 * @Package org.interestTeam.model2.dao 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2020年1月19日 下午5:02:43 
 * @version V1.0  
 */
package org.interestTeam.model2.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @ClassName: UserDao 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2020年1月19日 下午5:02:43 
 *  
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDao {
	String loginId, loginName, loginPassword, mobile, email, createUserId;
	SexEnum sex;
	String createTime;
	StateEnum state;
	String userIcon;
}
