package org.interestTeam.model2.service;

import org.interestTeam.model2.mapper.secondSource.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public void deleteUserById(String id) throws Exception {
		userMapper.deleteUserById(id);
	}
}
