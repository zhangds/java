/**   
 * @Title: RoleServiceTest.java 
 * @Package org.interestTeam.model.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月25日 上午12:45:07 
 * @version V1.0  
 */
package org.interestTeam.model.service;

import java.util.List;

import org.interestTeam.model.Application;
import org.interestTeam.model.models.RoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: RoleServiceTest 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月25日 上午12:45:07 
 *  
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class RoleServiceTest {

	@Autowired
	RoleService roleService;
	
	@Test
	public void getAllMenu() throws Exception{
		List<RoleDao> list =roleService.getRoleList();
		log.debug("数组长度:"+list.size());
	}
}
