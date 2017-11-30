/**   
 * @Title: ErrorSkipController.java 
 * @Package org.interestTeam.model.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年11月30日 下午3:02:37 
 * @version V1.0  
 */
package org.interestTeam.model.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName: ErrorSkipController 
 * @Description: 错误页面的跳转
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年11月30日 下午3:02:37 
 *  
 */
@RestController
public class ErrorSkipController {

	@RequestMapping(value="/error/404",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView skipToErrorHtml(@ModelAttribute("model") ModelMap model) {
		ModelAndView mv = new ModelAndView("/error/404");// 模板文件的名称，不需要指定后缀
		return mv;
	}
}
