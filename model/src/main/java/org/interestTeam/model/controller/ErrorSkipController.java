/**   
 * @Title: ErrorSkipController.java 
 * @Package org.interestTeam.model.controller 
 * @Description: 错误页面处理
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年11月30日 下午3:02:37 
 * @version V1.0  
 */
package org.interestTeam.model.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;

/** 
 * @ClassName: ErrorSkipController 
 * @Description: 错误页面的跳转
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年11月30日 下午3:02:37 
 *  
 */
@RestController
public class ErrorSkipController {

	@ApiOperation(value="错误404页面跳转", notes="错误页面跳转处理")
    //@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value="/error/404",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView skipToErrorHtml() {
		ModelAndView mv = new ModelAndView("/error/404");// 模板文件的名称，不需要指定后缀
		return mv;
	}
}
