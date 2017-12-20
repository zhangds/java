/**   
 * @Title: MenuMapper.java 
 * @Package org.interestTeam.model.database.mapper.primarySource 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月15日 下午12:15:42 
 * @version V1.0  
 */
package org.interestTeam.model.database.mapper.primarySource;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.interestTeam.model.database.entity.MenuEntity;
import org.interestTeam.model.service.MenuService;

/** 
 * @ClassName: MenuMapper 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月15日 下午12:15:42 
 *  
 */
@Mapper
public interface MenuMapper extends MenuService{

	@Select("SELECT * FROM menus order by menu_parent_id desc,menu_index asc")
	@Results({ @Result(property = "menuId", column = "menu_id"),
			@Result(property = "menuTitle", column = "menu_title"),
			@Result(property = "menuParentId", column = "menu_parent_id"),
			@Result(property = "menuIcon", column = "menu_icon"),
			@Result(property = "menuUrl", column = "menu_url"),
			@Result(property = "menuSpread", column = "menu_spread", javaType = Boolean.class),
			@Result(property = "menuIndex", column = "menu_index", javaType = Integer.class) })
	public List<MenuEntity> getAllDbMenus();
	
	@Insert("INSERT INTO menus (menu_id, menu_title, menu_parent_id, menu_icon, menu_url, menu_spread, menu_index)"
			+ " VALUES (#{menuId}, #{menuTitle}, #{menuParentId}, #{menuIcon}, #{menuUrl}, #{menuSpread}, #{menuIndex})")
	public void insertMenu(MenuEntity menu);
	
	@Update("UPDATE menus SET menu_id=#{menuId},menu_title=#{menuTitle},menu_parent_id=#{menuParentId},"
			+ "menu_icon=#{menuIcon},menu_url=#{menuUrl},menu_spread=#{menuSpread},menu_index=#{menuIndex} "
			+ "WHERE menu_id=#{menuId}")
	public void updateMenu(MenuEntity menu);
	
	@Delete("delete from menus")
	public void deleteMenu();
	
}
