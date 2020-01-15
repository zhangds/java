package org.interestTeam.model2.mapper.primarySource;

import java.util.List;
import org.interestTeam.model2.dao.MenuDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.interestTeam.model2.service.MenuService;

@Mapper
public interface MenuMapper extends MenuService{

	@Select("select menuId, parentId, menuname, iconstate,iconcls, url, params, createrId, state,orderNum from menus where state='Y' order by parentId,orderNum asc")
	@Results({ @Result(property = "menuId", column = "menuId"),
			@Result(property = "menuTitle", column = "menu_title"),
			@Result(property = "parentId", column = "parentId"),
			@Result(property = "menuName", column = "menuname"),
			@Result(property = "iconState", column = "iconstate"),
			@Result(property = "iconCls", column = "iconcls"),
			@Result(property = "url", column = "url"),
			@Result(property = "params", column = "params"),
			@Result(property = "createrId", column = "createrId"),
			@Result(property = "state", column = "state"),
			@Result(property = "orderNum", column = "orderNum", javaType = Integer.class) })
	public List<MenuDao> getAllMenus();
}
