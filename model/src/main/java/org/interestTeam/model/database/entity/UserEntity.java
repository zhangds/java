/**   
 * @Title: UserEntity.java 
 * @Package org.interestTeam.model.database.entity 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 下午2:29:34 
 * @version V1.0  
 */
package org.interestTeam.model.database.entity;

import java.io.Serializable;
import org.interestTeam.model.database.enums.SexEnum;
import org.interestTeam.model.database.enums.UserStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserEntity
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com
 * @date 2017年12月7日 下午2:29:34
 * 
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
	String loginId, loginName, loginPassword, mobile, email, createUserId;
	UserStatusEnum loginStatus;
	SexEnum sex;
	String createTime;
}
