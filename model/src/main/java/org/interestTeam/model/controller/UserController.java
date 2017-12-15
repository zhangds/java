/**   
 * @Title: UserController.java 
 * @Package org.interestTeam.model.controller 
 * @Description: 用户api服务
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 下午3:44:42 
 * @version V1.0  
 */
package org.interestTeam.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.interestTeam.model.database.entity.UserEntity;
import org.interestTeam.model.models.LoginStateVo;
import org.interestTeam.model.models.SessionKeyConstants;
import org.interestTeam.model.service.EncryptService;
import org.interestTeam.model.service.LoginService;
import org.interestTeam.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

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
	
	@Value("${project.redirect}")
	private String profileActive;

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
				data.put("redirect", profileActive);
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
	
	@Autowired
	EncryptService encryptService;
	
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
