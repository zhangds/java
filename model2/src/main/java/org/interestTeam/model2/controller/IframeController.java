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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.interestTeam.model2.dao.MenuDao;
import org.interestTeam.model2.dao.UserDao;
import org.interestTeam.model2.service.EncryptService;
import org.interestTeam.model2.service.MenuService;
import org.interestTeam.model2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
//import lombok.extern.slf4j.Slf4j;

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
//@Slf4j
public class IframeController {

	@Value("${project.name}")
	private String name;
	
	@Value("${project.redirect}")
	private String redirectUrl;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	EncryptService encryptService;
	
	@ApiOperation(value = "登录", notes = "登录页面")
	@GetMapping(value="/login")
	public String login(Model model){
		model.addAttribute("projectName", name);
		return "index/login";
	}
	
	@ApiOperation(value = "登录验证", notes = "登录验证")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "userId", required = true, value = "用户ID", defaultValue = "")
		,@ApiImplicitParam(paramType = "query", name = "pwd", dataType = "String", required = true, value = "密码", defaultValue = "")
	})
	@PostMapping(value="/checkUser")
	@ResponseBody
	public Object checkUser(@RequestParam("userId")String userId,@RequestParam("pwd")String pwd,
			HttpServletRequest request,Model model){
		Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
		UserDao _currentUser = null;
		try {
			_currentUser = new UserDao(){{
			setLoginId(userId);
			setLoginPassword(encryptService.encrypt(pwd));
		}};
			_currentUser = userService.getUserByIdAndEncryptPwd(_currentUser);
		} catch (Exception e) {
			_currentUser = null;
		}
		if (_currentUser != null){
//			HttpSession session = request.getSession();
//			session.setAttribute("sessionId", _currentUser);
			request.getSession().setAttribute("userName",JSON.toJSONString( _currentUser));
			resultMap.put("flag", true);
			resultMap.put("redirectUrl", redirectUrl);
		}else{
			resultMap.put("flag", false);
			resultMap.put("msg", "用户名或密码错误!");
		}
		return resultMap;
	}
	
	@ApiOperation(value = "首页", notes = "首页页面")
	@GetMapping(value="/main")
	public String index(HttpServletRequest request,Model model){
		String userJson = (String)request.getSession().getAttribute("userName");
		if (userJson == null)
			return "redirect:/login";
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
