/**   
 * @Title: UserService.java 
 * @Package org.interestTeam.model.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 下午3:28:46 
 * @version V1.0  
 */
package org.interestTeam.model.service;

import java.util.List;

import org.interestTeam.model.database.entity.UserEntity;
import org.interestTeam.model.database.mapper.primarySource.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserService
 * @Description: 用户服务类
 * @author zhangds zhang198058@hotmail.com
 * @date 2017年12月7日 下午3:28:46
 * 
 */
@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	public void insertUser(UserEntity user) throws Exception {
		userMapper.insertUser(user);
	}

	public List<UserEntity> getUsers() throws Exception {
		return userMapper.getUsers();
	}

	public UserEntity getUserById(String id) throws Exception {
		return userMapper.getUserById(id);
	}
	
	public List<UserEntity> getUserByName(String userName) throws Exception {
		return userMapper.getUserByName(userName);
	}
	
	public Long getCountbyName(String userName) throws Exception {
		return userMapper.getCountbyName(userName);
	}
	
	public void updateUser(UserEntity user) throws Exception {
		userMapper.updateUser(user);
	}
	
	public void deleteUserById(String id) throws Exception {
		userMapper.deleteUserById(id);
	}
	
	public void deleteUserByUser(UserEntity user) throws Exception {
		userMapper.deleteUserByUser(user);
	}
	
}
