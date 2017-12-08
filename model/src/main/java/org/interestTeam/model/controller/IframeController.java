/**   
 * @Title: IframeController.java 
 * @Package org.interestTeam.model.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 上午10:48:58 
 * @version V1.0  
 */
package org.interestTeam.model.controller;

import javax.servlet.http.HttpServletRequest;

import org.interestTeam.model.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * @ClassName: IframeController 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月7日 上午10:48:58 
 *  
 */
@RestController
@RequestMapping(value="")
@ConfigurationProperties(prefix="project")
@Data
public class IframeController {

	public String name ;
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");// 模板文件的名称，不需要指定后缀
		mv.addObject("projectName", name);
		return mv;
	}
	
	@RequestMapping(value="/index",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index/index");// 模板文件的名称，不需要指定后缀
		mv.addObject("projectName", name);
		return mv;
	}
	
	@RequestMapping(value="/rest",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	public ModelAndView rest(HttpServletRequest request) {
		loginService.clearCookie(request);
		ModelAndView mv = new ModelAndView("login");// 模板文件的名称，不需要指定后缀
		mv.addObject("projectName", name);
		return mv;
	}
}
