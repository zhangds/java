package org.interestTeam.model2.service;

import org.interestTeam.model2.dao.UserDao;
import org.interestTeam.model2.mapper.primarySource.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public UserDao getUserById(String id) throws Exception {
		return userMapper.getUserById(id);
	}
	
	public UserDao getUserByIdAndEncryptPwd(UserDao dao) throws Exception {
		return userMapper.getUserByIdAndEncryptPwd(dao);
	}
}
