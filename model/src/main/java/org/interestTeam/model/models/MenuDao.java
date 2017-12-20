/**   
 * @Title: MenuDao.java 
 * @Package org.interestTeam.model.models 
 * @Description: 存储菜单目录
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月15日 下午12:02:19 
 * @version V1.0  
 */
package org.interestTeam.model.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @ClassName: MenuDao 
 * @Description: 存储菜单目录
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月15日 下午12:02:19 
 *  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDao {

	String id,title,parentId,icon,href;
	
	boolean spread;
	
	List<MenuDao> children;
	
}
