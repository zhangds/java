/**   
 * @Title: IframeController.java 
 * @Package org.interestTeam.model.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 上午10:48:58 
 * @version V1.0  
 */
package org.interestTeam.model.controller;

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
@RequestMapping(value="/index")
@ConfigurationProperties(prefix="project")
@Data
public class IframeController {

	public String name ;
	
	@RequestMapping(value="/login",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");// 模板文件的名称，不需要指定后缀
		mv.addObject("projectName", name);
		return mv;
	}
}
