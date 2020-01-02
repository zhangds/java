/**   
 * @Title: ErrorController.java 
 * @Package org.interestTeam.model2.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2020年1月2日 下午4:17:42 
 * @version V1.0  
 */
package org.interestTeam.model2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: ErrorController 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2020年1月2日 下午4:17:42 
 *  
 */
@Controller
@RequestMapping(value = "/error")
@Slf4j
public class ErrorController {

	@ApiOperation(value = "错误页面", notes = "错误页面")
	@GetMapping(value="/{errorNum}")
	public String decrypt(@PathVariable String errorNum,Model model){
		return "error/404";
	}
}
