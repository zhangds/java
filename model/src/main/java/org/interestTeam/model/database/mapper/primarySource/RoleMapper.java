/**   
 * @Title: RoleMapper.java 
 * @Package org.interestTeam.model.database.mapper.primarySource 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月23日 下午3:46:40 
 * @version V1.0  
 */
package org.interestTeam.model.database.mapper.primarySource;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.interestTeam.model.database.entity.RoleEntity;

/** 
 * @ClassName: RoleMapper 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月23日 下午3:46:40 
 *  
 */
@Mapper
public interface RoleMapper{

	@Select("SELECT * FROM USER_ROLE order by PARENT_ROLE_ID desc")
	@Results({ @Result(property = "roleId", column = "ROLE_ID"),
			@Result(property = "parentRoleId", column = "PARENT_ROLE_ID"),
			@Result(property = "roleName", column = "ROLE_NAME"),
			@Result(property = "createUserId", column = "CREATE_USER_ID"),
			@Result(property = "createTime", column = "CREATE_TIME", javaType = String.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "roleIndex", column = "ROLE_INDEX", javaType = Integer.class, jdbcType = JdbcType.INTEGER)})
	List<RoleEntity> getRoleList();
	
	@Insert("INSERT INTO USER_ROLE(ROLE_ID,PARENT_ROLE_ID,ROLE_NAME,CREATE_USER_ID,CREATE_TIME,ROLE_INDEX) VALUES("
			+ "#{roleId}, #{parentRoleId}, #{roleName}, #{createUserId}, #{createTime},#{roleIndex})")
	void insertRole(RoleEntity role);
	
	@Update("UPDATE USER_ROLE SET PARENT_ROLE_ID=#{parentRoleId},ROLE_NAME=#{roleName},"
			+ "CREATE_USER_ID=#{createUserId},CREATE_TIME=#{createTime},ROLE_INDEX=#{roleIndex} "
			+ "WHERE ROLE_ID =#{roleId}")
	void updateRole(RoleEntity role);
	
	@Delete("DELETE FROM USER_ROLE WHERE ROLE_ID =#{roleId}")
	void deleteUserById(String roleId);
	
}
