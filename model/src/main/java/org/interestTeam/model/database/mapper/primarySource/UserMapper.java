/**   
 * @Title: UserMapper.java 
 * @Package org.interestTeam.model.database.mapper.primarySource 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 下午2:55:00 
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
import org.interestTeam.model.database.entity.UserEntity;
import org.interestTeam.model.database.enums.SexEnum;
import org.interestTeam.model.database.enums.UserStatusEnum;

/**
 * @ClassName: UserMapper
 * @Description: 用户表接口操作类
 * @author zhangds zhang198058@hotmail.com
 * @date 2017年12月7日 下午2:55:00
 * 
 */
@Mapper
public interface UserMapper {

	@Select("SELECT * FROM user_user order by login_id desc")
	@Results({ @Result(property = "loginId", column = "login_id"),
			@Result(property = "loginName", column = "login_name"),
			@Result(property = "loginPassword", column = "login_password"),
			@Result(property = "createUserId", column = "create_user_id"),
			@Result(property = "loginStatus", column = "login_status", javaType = UserStatusEnum.class),
			@Result(property = "sex", column = "sex", javaType = SexEnum.class),
			@Result(property = "createTime", column = "create_time", javaType = String.class, jdbcType = JdbcType.TIMESTAMP) })
	List<UserEntity> getUsers();
	
	@Select("SELECT * FROM user_user where (login_id like '%'||#{key, jdbcType=VARCHAR}||'%' or login_name like '%'||#{key, jdbcType=VARCHAR}||'%') order by login_id desc")
	@Results({ @Result(property = "loginId", column = "login_id"),
			@Result(property = "loginName", column = "login_name"),
			@Result(property = "loginPassword", column = "login_password"),
			@Result(property = "createUserId", column = "create_user_id"),
			@Result(property = "loginStatus", column = "login_status", javaType = UserStatusEnum.class),
			@Result(property = "sex", column = "sex", javaType = SexEnum.class),
			@Result(property = "createTime", column = "create_time", javaType = String.class, jdbcType = JdbcType.TIMESTAMP) })
	public List<UserEntity> getUsersByKeys(String key);

	@Select("SELECT * FROM user_user WHERE login_id = #{id}")
	@Results({ @Result(property = "loginId", column = "login_id"),
			@Result(property = "loginName", column = "login_name"),
			@Result(property = "loginPassword", column = "login_password"),
			@Result(property = "createUserId", column = "create_user_id"),
			@Result(property = "loginStatus", column = "login_status", javaType = UserStatusEnum.class),
			@Result(property = "sex", column = "sex", javaType = SexEnum.class),
			@Result(property = "createTime", column = "create_time", javaType = String.class, jdbcType = JdbcType.TIMESTAMP) })
	UserEntity getUserById(String id);

	@Select("SELECT * FROM user_user WHERE login_name = #{userName}")
	@Results({ @Result(property = "loginId", column = "login_id"),
			@Result(property = "loginName", column = "login_name"),
			@Result(property = "loginPassword", column = "login_password"),
			@Result(property = "createUserId", column = "create_user_id"),
			@Result(property = "loginStatus", column = "login_status", javaType = UserStatusEnum.class),
			@Result(property = "sex", column = "sex", javaType = SexEnum.class),
			@Result(property = "createTime", column = "create_time", javaType = String.class, jdbcType = JdbcType.TIMESTAMP) })
	List<UserEntity> getUserByName(String userName);

	@Select("SELECT count(*) id FROM user_user WHERE login_name = #{userName}")
	@Results({ @Result(property = "id", column = "id", javaType = Integer.class) })
	int getCountbyName(String userName);
	
	@Select("SELECT count(*) id FROM user_user WHERE login_id = #{userId}")
	@Results({ @Result(property = "id", column = "id", javaType = Integer.class) })
	int getCountbyId(String userId);

	@Insert("INSERT INTO user_user(login_id,login_name,login_password,login_status,mobile,email,sex,create_user_id,CREATE_time) VALUES("
			+ "#{loginId}, #{loginName}, #{loginPassword}, #{loginStatus}, #{mobile},#{email},#{sex}, #{createUserId}, #{createTime})")
	void insertUser(UserEntity user);

	@Update("UPDATE user_user SET login_name=#{loginName},login_password=#{loginPassword},login_status=#{loginStatus},"
			+ "mobile=#{mobile},email=#{email},sex=#{sex},create_user_id=#{createUserId},CREATE_time=#{createTime} "
			+ "WHERE login_id =#{loginId}")
	void updateUser(UserEntity user);

	@Delete("DELETE FROM user_user WHERE login_id =#{id}")
	void deleteUserById(String id);

	@Delete("DELETE FROM user_user WHERE login_id =#{loginId}")
	void deleteUserByUser(UserEntity user);
	
	@Update("update user_user set login_status=#{loginStatus} where login_id =#{loginId}")
	void updateLoginStatusById(UserEntity user);
}
