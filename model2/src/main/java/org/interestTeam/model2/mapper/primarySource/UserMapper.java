package org.interestTeam.model2.mapper.primarySource;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.interestTeam.model2.dao.SexEnum;
import org.interestTeam.model2.dao.StateEnum;
import org.interestTeam.model2.dao.UserDao;

@Mapper
public interface UserMapper {

	@Delete("DELETE FROM user_user WHERE userId =#{id}")
	public void deleteUserById(String id);
	
	@Select("SELECT * FROM user_user WHERE userId = #{loginId} and state='Y'")
	@Results({ @Result(property = "loginId", column = "userId"),
			@Result(property = "loginName", column = "userName"),
			@Result(property = "loginPassword", column = "userPwd"),
			@Result(property = "mobile", column = "mobile"),
			@Result(property = "email", column = "email"),
			@Result(property = "createUserId", column = "createrId"),
			@Result(property = "sex", column = "userSex", javaType = SexEnum.class),
			@Result(property = "createTime", column = "createrDt", javaType = String.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "state", column = "state", javaType = StateEnum.class),
			@Result(property = "userIcon", column = "userIcon")})
	public UserDao getUserById(String loginId);
	
	@Select("SELECT * FROM user_user WHERE userId = #{loginId} and userPwd=#{loginPassword} and state='Y'")
	@Results({ @Result(property = "loginId", column = "userId"),
			@Result(property = "loginName", column = "userName"),
			@Result(property = "loginPassword", column = "userPwd"),
			@Result(property = "mobile", column = "mobile"),
			@Result(property = "email", column = "email"),
			@Result(property = "createUserId", column = "createrId"),
			@Result(property = "sex", column = "userSex", javaType = SexEnum.class),
			@Result(property = "createTime", column = "createrDt", javaType = String.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "state", column = "state", javaType = StateEnum.class),
			@Result(property = "userIcon", column = "userIcon")})
	public UserDao getUserByIdAndEncryptPwd(UserDao dao);
	
	
	
}
