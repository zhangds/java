/**   
 * @Title: MenuServiceTest.java 
 * @Package org.interestTeam.model.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月16日 上午1:10:36 
 * @version V1.0  
 */
package org.interestTeam.model.service;

import java.util.List;

import org.interestTeam.model.Application;
import org.interestTeam.model.database.entity.MenuEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: MenuServiceTest 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月16日 上午1:10:36 
 *  
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class MenuServiceTest {

	@Autowired
	MenuService service;
	
	@Before
	public void insert(){
		service.deleteMenu();
		MenuEntity menu = new MenuEntity("sys_opt","系统管理",null,"icon","url",false,5);
		MenuEntity menu_pwd = new MenuEntity("sys_opt_user","密码修改","sys_opt","icon","/user/changePwd",false,0);
		MenuEntity menu_user = new MenuEntity("sys_opt_user","用户管理","sys_opt","icon","url",false,1);
		MenuEntity menu_role = new MenuEntity("sys_opt_role","角色管理","sys_opt","icon","url",false,2);
		MenuEntity menu_menu = new MenuEntity("sys_opt_menu","菜单管理","sys_opt","icon","url",false,3);
		service.insertMenu(menu);
		service.insertMenu(menu_pwd);
		service.insertMenu(menu_user);
		service.insertMenu(menu_role);
		service.insertMenu(menu_menu);
	}
	
	@Test
	public void getAllMenu(){
		List<MenuEntity> list =service.getAllDbMenus();
		log.debug("数组长度:"+list.size());
	}
}
