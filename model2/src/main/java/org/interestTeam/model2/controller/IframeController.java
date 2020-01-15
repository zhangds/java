/**   
 * @Title: IframeController.java 
 * @Package org.interestTeam.model2.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2020年1月13日 下午2:18:12 
 * @version V1.0  
 */
package org.interestTeam.model2.controller;

import java.util.ArrayList;
import java.util.List;

import org.interestTeam.model2.dao.MenuDao;
import org.interestTeam.model2.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

/** 
 * @ClassName: IframeController 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2020年1月13日 下午2:18:12 
 *  
 */
@Controller
@Data
@RequestMapping(value = "/")
public class IframeController {

	@Value("${project.name}")
	private String name;
	
	@Autowired
	MenuService menuService;
	
	@ApiOperation(value = "首页", notes = "首页页面")
	@GetMapping(value="/main")
	public String index(Model model){
		model.addAttribute("projectName", name);
		List<MenuDao> menuList = menuService.getAllMenus();
		model.addAttribute("menus", JSON.toJSONString(realListForMenus(menuList)));
		return "index/main";
	}
	
	public List<MenuDao> realListForMenus(List<MenuDao> dlist){
		List<MenuDao> list = new ArrayList<MenuDao>(100);
		for (MenuDao _entity:dlist){
			if ( _entity.getParentId()==null || "".equals(_entity.getParentId()) ){
				MenuDao _dao = _entity;
				List<MenuDao> _list = getRecursionMenuDao(dlist,_entity.getMenuId());
				if (_list != null && _list.size()>0)
					_dao.setChildren(_list);
				list.add(_dao);
			}
		}
		return list;
	}
	
	public List<MenuDao> getRecursionMenuDao(List<MenuDao> dlist,String parentId){
		List<MenuDao> list = new ArrayList<MenuDao>();
		for (MenuDao _entity:dlist){
			if (parentId.equals(_entity.getParentId())){
				MenuDao _dao = _entity;
				List<MenuDao> children = getRecursionMenuDao(dlist,_entity.getMenuId());
				if (children != null && children.size()>0){
					_dao.setIconState("true");
					_dao.setChildren(children);
				}else{
					_dao.setIconState("");
				}
				list.add(_dao);
			}
		}
		return list;
	}
}
