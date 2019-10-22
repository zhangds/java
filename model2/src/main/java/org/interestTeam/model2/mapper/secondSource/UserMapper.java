package org.interestTeam.model2.mapper.secondSource;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	@Delete("DELETE FROM user_user WHERE login_id =#{id}")
	void deleteUserById(String id);
	
	
}
