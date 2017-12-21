/**   
 * @Title: HelperController.java 
 * @Package org.interestTeam.model.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月20日 上午11:06:13 
 * @version V1.0  
 */
package org.interestTeam.model.controller;

import org.interestTeam.model.database.entity.UserEntity;
import org.interestTeam.model.models.SessionKeyConstants;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

/**
 * @ClassName: HelperController
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com
 * @date 2017年12月20日 上午11:06:13
 * 
 */
@RestController
@RequestMapping(value = "/help")
@SessionAttributes({ SessionKeyConstants.USER })
public class HelperController {

	@RequestMapping(value = "/icon", method = { RequestMethod.GET })
	@ResponseBody
	@ApiIgnore
	public ModelAndView changePwd(@ModelAttribute(SessionKeyConstants.USER) UserEntity user) {
		ModelAndView mv = new ModelAndView("help/icon");
		return mv;
	}
}
