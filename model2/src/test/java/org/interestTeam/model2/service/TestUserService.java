package org.interestTeam.model2.service;

import org.interestTeam.model2.BootApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApp.class)
@WebAppConfiguration
@Slf4j
public class TestUserService {

	@Autowired
	UserService userService;
	
	@Test
	public void test(){
		try {
			//userService.deleteUserById("zhangds");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
