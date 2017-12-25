/**   
 * @Title: IframeController.java 
 * @Package org.interestTeam.model.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 上午10:48:58 
 * @version V1.0  
 */
package org.interestTeam.model.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.interestTeam.model.configure.SystemRunning;
import org.interestTeam.model.database.entity.MenuEntity;
import org.interestTeam.model.database.entity.UserEntity;
import org.interestTeam.model.service.EncryptService;
import org.interestTeam.model.service.LoginService;
import org.interestTeam.model.service.MenuService;
import org.interestTeam.model.models.MenuDao;
import org.interestTeam.model.models.SessionKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * @ClassName: IframeController 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月7日 上午10:48:58 
 *  
 */
@RestController
@Data
@SessionAttributes({ SessionKeyConstants.USER })
@Slf4j
public class IframeController {

//	public String name ;
	@Autowired
	SystemRunning systemRunning;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");// 模板文件的名称，不需要指定后缀
		mv.addObject("projectName", systemRunning.getName());
		return mv;
	}
	
	@RequestMapping(value="/index",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	public ModelAndView index(@ModelAttribute(SessionKeyConstants.USER) UserEntity user) {
		ModelAndView mv = new ModelAndView("index/index");// 模板文件的名称，不需要指定后缀
		mv.addObject("projectName", systemRunning.getName());
		mv.addObject("user", user);
		return mv;
	}
	
	@Autowired
	MenuService menuService;
	
	@ApiOperation(value = "获取菜单", notes = "获取当前用户所属菜单,目前未加入权限体系,只是将用户注入其中")
	@RequestMapping(value="/menus",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object getCurrentUserForMenus(@ModelAttribute(SessionKeyConstants.USER) UserEntity user){
		List<MenuDao> result = null;
		try {
			List<MenuEntity> list = menuService.getAllDbMenus();
			result = realListForMenus(list);
				
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}
	
	public List<MenuDao> realListForMenus(List<MenuEntity> dlist){
		List<MenuDao> list = new ArrayList<MenuDao>();
		for (MenuEntity _entity:dlist){
			if ( _entity.getMenuParentId()==null || _entity.getMenuParentId().equals("") ){
				MenuDao _dao = new MenuDao(_entity.getMenuId(),_entity.getMenuTitle(),_entity.getMenuParentId(),
						_entity.getMenuIcon(),_entity.getMenuUrl(),_entity.isMenuSpread(),null);
				List<MenuDao> _list = getRecursionMenuDao(dlist,_entity.getMenuId());
				if (_list != null && _list.size()>0)
					_dao.setChildren(_list);
				list.add(_dao);
			}
		}
		return list;
	}
	
	public List<MenuDao> getRecursionMenuDao(List<MenuEntity> dlist,String parentId){
		List<MenuDao> list = new ArrayList<MenuDao>();
		for (MenuEntity _entity:dlist){
			if (parentId.equals(_entity.getMenuParentId())){
				MenuDao _dao = new MenuDao(_entity.getMenuId(),_entity.getMenuTitle(),_entity.getMenuParentId(),
						_entity.getMenuIcon(),_entity.getMenuUrl(),_entity.isMenuSpread(),null);
				List<MenuDao> children = getRecursionMenuDao(dlist,_entity.getMenuId());
				if (children != null && children.size()>0)
					_dao.setChildren(children);
				list.add(_dao);
			}
		}
		return list;
	}
	
	@Autowired
	EncryptService encryptService;
	
	@ApiOperation(value = "加密", notes = "字符串加密")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "encryptString", dataType = "String", required = true, value = "加密字符串", defaultValue = "111")
			})
	@RequestMapping(value="/encrypt",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object encryptString(@RequestParam("encryptString")String encryptString){
		try {
			return encryptService.encrypt(encryptString);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	@ApiOperation(value = "解密", notes = "字符串解密")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "decryptString", dataType = "String", required = true, value = "加密字符串", defaultValue = "4wnCY/I+bn4=")
			})
	@RequestMapping(value="/decrypt",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object decryptString(@RequestParam("decryptString")String decryptString){
		try {
			return encryptService.decrypt(decryptString);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	@RequestMapping(value="/rest",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	public ModelAndView rest(HttpServletRequest request) {
		loginService.clearCookie(request);
		ModelAndView mv = new ModelAndView("login");// 模板文件的名称，不需要指定后缀
		mv.addObject("projectName", systemRunning.getName());
		return mv;
	}
	
}
