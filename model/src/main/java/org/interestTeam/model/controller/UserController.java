/**   
 * @Title: UserController.java 
 * @Package org.interestTeam.model.controller 
 * @Description: 用户api服务
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 下午3:44:42 
 * @version V1.0  
 */
package org.interestTeam.model.controller;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.interestTeam.model.configure.SystemRunning;
import org.interestTeam.model.database.entity.UserEntity;
import org.interestTeam.model.models.LoginStateVo;
import org.interestTeam.model.models.SessionKeyConstants;
import org.interestTeam.model.service.EncryptService;
import org.interestTeam.model.service.LoginService;
import org.interestTeam.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @ClassName: UserController
 * @Description: 用户api服务
 * @author zhangds zhang198058@hotmail.com
 * @date 2017年12月7日 下午3:44:42
 * 
 */
@RestController
@RequestMapping(value = "/user")
@SessionAttributes({ SessionKeyConstants.USER })
@Slf4j
public class UserController {

	@Autowired
	LoginService loginService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SystemRunning systemRunning;
	
//	@Value("${project.redirect}")
//	private String profileActive;

	@ApiOperation(value = "用户登录验证信息", notes = "用户验证")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "path", name = "userId", dataType = "String", required = true, value = "用户的姓名", defaultValue = "admin"),
			@ApiImplicitParam(paramType = "path", name = "pwd", dataType = "String", required = true, value = "用户的密码", defaultValue = "111") })
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/login/{userId}/{pwd}", method = { RequestMethod.POST })
	@ResponseBody
	public Object login(@PathVariable String userId, @PathVariable String pwd, HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			LoginStateVo result = loginService.login(userId, pwd, SessionKeyConstants.USER, request.getSession());
			data.put("success", result.isSuccess());
			data.put("msg", result.getMsg());
			if (result.isSuccess()) {
				data.put("redirect", systemRunning.getRedirect());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value="/changePwd",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	//ModelAttribute
	public ModelAndView changePwd(@ModelAttribute(SessionKeyConstants.USER) UserEntity user) {
		ModelAndView mv = new ModelAndView("user/changePwd");// 模板文件的名称，不需要指定后缀
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(value="/userManager",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	public ModelAndView userManager(@ModelAttribute(SessionKeyConstants.USER) UserEntity user) {
		ModelAndView mv = new ModelAndView("user/manager");// 模板文件的名称，不需要指定后缀
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(value="/userAddAndEdit",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	public ModelAndView userAddAndEdit(HttpServletRequest request,@SessionAttribute(SessionKeyConstants.USER) UserEntity user) {
		ModelAndView mv = new ModelAndView("user/userInfo");// 模板文件的名称，不需要指定后缀
		mv.addObject("currentUser", user);
		String userId = request.getParameter("userId");
		if (userId != null){
			UserEntity _user = null;
			try {
				_user = userService.getUserById(userId);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			mv.addObject("editUser",_user);
			mv.addObject("editUserJson",JSON.toJSONString(_user));
		}
		return mv;
	}
	
	@ApiOperation(value = "新增/修改用户", notes = "进行用户表的用户新增、修改操作")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "option",dataType = "String", required = false, value = "操作类型(add/edit)", defaultValue = "edit"),
		@ApiImplicitParam(paramType = "query", name = "loginId",dataType = "String", required = false, value = "用户ID", defaultValue = ""),
		@ApiImplicitParam(paramType = "query", name = "loginName",dataType = "String", required = false, value = "用户中文名", defaultValue = ""),
		@ApiImplicitParam(paramType = "query", name = "loginStatus",dataType = "String", required = false, value = "用户状态(ON/CLOSE)", defaultValue = "ON"),
		@ApiImplicitParam(paramType = "query", name = "mobile",dataType = "String", required = false, value = "用户电话", defaultValue = ""),
		@ApiImplicitParam(paramType = "query", name = "sex",dataType = "String", required = false, value = "性别(M/F)", defaultValue = "M"),
		@ApiImplicitParam(paramType = "query", name = "email",dataType = "String", required = false, value = "邮箱", defaultValue = "")
	})
	@RequestMapping(value="/userAdd",method = {RequestMethod.POST})
	@ResponseBody
	public Object addUser(HttpServletRequest request,@SessionAttribute(SessionKeyConstants.USER) UserEntity user){
		Map<String, Object> result = new HashMap<String, Object>();
		String _option = request.getParameter("option");
		try {
			String _loginId = request.getParameter("loginId");
			String _loginName = request.getParameter("loginName");
			String _loginStatus = request.getParameter("loginStatus");
			String _mobile = request.getParameter("mobile");
			String _sex = request.getParameter("sex");
			String _email = request.getParameter("email");
			String _createUserId = user.getLoginId();
			String _pwd = encryptService.getDefaultPassword();
			String _createTime = getCurrentDate("yyyy-MM-dd HH:mm:ss");

			UserEntity _user = userService.getUser(_loginId, _loginName, _loginStatus, _mobile, _sex, _email, _createUserId, _pwd, _createTime);

			if (_option != null && "edit".equalsIgnoreCase(_option)){
				userService.updateUser(_user);
				result.put("msg", "更新用户成功!");
			}else{
				userService.insertUser(_user);
				result.put("msg", "新增用户成功!");
			}
			result.put("flag", true);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("flag", false);
			result.put("msg", "用户操作失败!");
		}
		
		return result;
	}
	
	/**
	 * 查询系统当前时间，时区为中国标准时间,返回类型为String
	 * @param format 格式如：yyyy-MM-dd HH:mm:ss
	 * @return 返回类型为String
	 */
	public static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
		return sdf.format(GregorianCalendar.getInstance().getTime());
	}
	
	@RequestMapping(value="/getUserIdCount/{userId}",method = {RequestMethod.POST})
	@ResponseBody
	public Object getUserIdCount(@PathVariable String userId, HttpServletRequest request) {
		int result = 100 ;
		try {
			result = userService.getCountbyId(userId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return result;
	}
	
	@ApiOperation(value = "锁定或开启用户", notes = "进行用户表的用户锁定或开启操作")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids[]", required = false, value = "用户锁定列表锁定用户ID", defaultValue = "")
		,@ApiImplicitParam(paramType = "path", name = "option", dataType = "String", required = true, value = "操作类型", defaultValue = "close")
	})
	@RequestMapping(value="/userLocked/{option}",method = {RequestMethod.POST})
	@ResponseBody
	public Object setUserLocked(@ModelAttribute(SessionKeyConstants.USER) UserEntity user,@PathVariable String option,@RequestParam("ids[]")String[] ids){
		Map<String, Object> result = new HashMap<String, Object>();
		String message = null;
		if ("close".equalsIgnoreCase(option)){
			message = "用户锁定";
		}else{
			message = "用户开启";
		}
		try {
			for (String id : ids){
				UserEntity _user = new UserEntity(id,option);
				userService.updateLoginStatusById(_user);
			}
			result.put("flag", true);
			result.put("msg", message+"成功!");
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("flag", false);
			result.put("msg", message+"失败!");
		}
		return result;
		
	}
	
	@ApiOperation(value = "删除用户", notes = "进行用户表的用户删除操作")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "ids[]", required = false, value = "用户锁定列表锁定用户ID", defaultValue = "")
	})
	@RequestMapping(value="/deleteUser",method = {RequestMethod.POST})
	@ResponseBody
	public Object userDelete(@ModelAttribute(SessionKeyConstants.USER) UserEntity user,@RequestParam("ids[]")String[] ids){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			for (String id : ids){
				userService.deleteUserById(id);
			}
			result.put("flag", true);
			result.put("msg", "用户删除成功!");
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("flag", false);
			result.put("msg", "用户删除失败!");
		}
		return result;
		
	}
			
	@ApiOperation(value = "获取用户列表", notes = "获取用户列表数据")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "likeKey", dataType = "String", required = false, value = "查询参数,模糊匹配id和名称", defaultValue = "")
			,@ApiImplicitParam(paramType = "query", name = "pageNo", dataType = "int", required = false, value = "查询当前的页码", defaultValue = "1")
	})
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/getUserList", method = { RequestMethod.POST })
	@ResponseBody
	public Object getUserList(@RequestParam("pageNo")int pageNo,HttpServletRequest request,@ModelAttribute(SessionKeyConstants.USER) UserEntity user) {
		Map<String, Object> result = new HashMap<String, Object>();
		String likeKey = request.getParameter("likeKey");
		
		List<UserEntity> list = null;
		try {
			PageHelper.startPage((pageNo<1?1:pageNo), systemRunning.getPageSize());
			if (null == likeKey || "".equals(likeKey.trim())){
				list = userService.getUsers();
			}else{
				list = userService.getUsersByKeys(likeKey);
			}
			PageInfo<UserEntity> page = new PageInfo<UserEntity>(list);
			result.put("page", page);
			result.put("flag", true);
			result.put("msg", "数据读取成功!");
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("flag", false);
			result.put("msg", "数据读取失败!");
		}
		return result;
	}
	
	@Autowired
	EncryptService encryptService;
	
	@ApiOperation(value = "修改某个用户密码", notes = "修改某个用户密码区别于用户密码修改,不需重新登录")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "loginId", dataType = "String", required = true, value = "用户的id", defaultValue = "sys"),
			@ApiImplicitParam(paramType = "query", name = "pwd", dataType = "String", required = true, value = "用户的密码", defaultValue = "111") })
	@RequestMapping(value="/actChangeOnePwd",method = {RequestMethod.POST})
	@ResponseBody
	public Object actChangePwd(@RequestParam("loginId")String id,@RequestParam("pwd")String pwd){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			UserEntity user = userService.getUserById(id);
			user.setLoginPassword(encryptService.encrypt(pwd));
			userService.updateUser(user);
			
			result.put("success", true);
			result.put("msg", "密码更新成功!");
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("success", false);
			result.put("msg", "密码更新失败!");
		}
		return result;
	}
	
	@ApiOperation(value = "用户密码修改", notes = "用户密码修改,由于清空了session中用户的信息,需要重新登录")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "loginId", dataType = "String", required = true, value = "用户的id", defaultValue = "sys"),
			@ApiImplicitParam(paramType = "query", name = "pwd", dataType = "String", required = true, value = "用户的密码", defaultValue = "111") })
	@RequestMapping(value="/actChangePwd",method = {RequestMethod.POST})
	@ResponseBody
	public Object actChangePwd(@RequestParam("loginId")String id,@RequestParam("pwd")String pwd,
			SessionStatus sessionStatus){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			UserEntity user = userService.getUserById(id);
			user.setLoginPassword(encryptService.encrypt(pwd));
			userService.updateUser(user);
			//更新session中的用户信息
			sessionStatus.setComplete();
			
			result.put("success", true);
			result.put("msg", "密码更新成功!请重新登录...");
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("success", false);
			result.put("msg", "密码更新失败!");
		}
		return result;
	}
}
