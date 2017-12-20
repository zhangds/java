/**   
 * @Title: MenuService.java 
 * @Package org.interestTeam.model.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月16日 上午1:06:09 
 * @version V1.0  
 */
package org.interestTeam.model.service;

import java.util.List;

import org.interestTeam.model.database.entity.MenuEntity;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: MenuService 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月16日 上午1:06:09 
 *  
 */
@Service
public interface MenuService {

	public List<MenuEntity> getAllDbMenus();
	
	public void insertMenu(MenuEntity menu);
	
	public void updateMenu(MenuEntity menu);
	
	public void deleteMenu();
}
