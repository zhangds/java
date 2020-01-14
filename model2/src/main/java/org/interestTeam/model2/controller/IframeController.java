/**   
 * @Title: IframeController.java 
 * @Package org.interestTeam.model2.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2020年1月13日 下午2:18:12 
 * @version V1.0  
 */
package org.interestTeam.model2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

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
public class IframeController {

	@Value("${project.name}")
	private String name;
	
	@ApiOperation(value = "首页", notes = "首页页面")
	@GetMapping(value="/main")
	public String index(Model model){
		model.addAttribute("projectName", name);
		return "index/main";
	}
}
