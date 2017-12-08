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

import org.interestTeam.model.models.LoginStateVo;
import org.interestTeam.model.models.SessionKeyConstants;
import org.interestTeam.model.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: UserController
 * @Description: 用户api服务
 * @author zhangds zhang198058@hotmail.com
 * @date 2017年12月7日 下午3:44:42
 * 
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

	@Autowired
	LoginService loginService;
	
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
}
