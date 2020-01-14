/**   
 * @Title: MenuImpl.java 
 * @Package org.interestTeam.model2.impl 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2020年1月14日 下午11:04:33 
 * @version V1.0  
 */
package org.interestTeam.model2.impl;

import java.util.List;

import org.interestTeam.model2.service.MenuService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/** 
 * @ClassName: MenuImpl 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2020年1月14日 下午11:04:33 
 *  
 */
@Data
@Component
public class MenuImpl implements MenuService{

	@Value("#{'${project.menus.names}'.split(',')}")
	private List<String> names;
	
	@Value("#{'${project.menus.sqls}'.split(',')}")
	private List<String> sqls;
	
	@Override
	public void getMenus() {
		// TODO Auto-generated method stub
		System.out.println(names.size()+sqls.size());
	}

}
