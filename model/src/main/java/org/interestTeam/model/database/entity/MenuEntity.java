/**   
 * @Title: MenuEntity.java 
 * @Package org.interestTeam.model.database.entity 
 * @Description: 菜单数据
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月15日 下午12:08:41 
 * @version V1.0  
 */
package org.interestTeam.model.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @ClassName: MenuEntity 
 * @Description: 菜单数据
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月15日 下午12:08:41 
 *  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuEntity {

	String menuId,menuTitle,menuParentId,menuIcon,menuUrl;
	boolean menuSpread;
	int menuIndex;
	
}
