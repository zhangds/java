/**   
 * @Title: MenuService.java 
 * @Package org.interestTeam.model2.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2020年1月14日 下午10:58:04 
 * @version V1.0  
 */
package org.interestTeam.model2.service;

import java.util.List;

import org.interestTeam.model2.dao.MenuDao;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: MenuService 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2020年1月14日 下午10:58:04 
 *  
 */
@Service
public interface MenuService {

	public List<MenuDao> getAllMenus();
}
