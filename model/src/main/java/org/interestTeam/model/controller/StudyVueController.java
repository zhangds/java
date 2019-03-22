/**   
 * @Title: StudyVueController.java 
 * @Package org.interestTeam.model.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年3月22日 上午9:46:08 
 * @version V1.0  
 */
package org.interestTeam.model.controller;

import org.interestTeam.model.database.entity.UserEntity;
import org.interestTeam.model.models.AccessLimit;
import org.interestTeam.model.models.SessionKeyConstants;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * @ClassName: StudyVueController 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年3月22日 上午9:46:08 
 *  
 */
@RestController
@RequestMapping(value = "/vue")
@SessionAttributes({ SessionKeyConstants.USER })
@Slf4j
public class StudyVueController {

	@RequestMapping(value="/test/{step}",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	@AccessLimit(seconds=5, maxCount=5, needLogin=true)
	//ModelAttribute
	public ModelAndView changePwd(@ModelAttribute(SessionKeyConstants.USER) UserEntity user
			,@PathVariable String step) {
		ModelAndView mv = null;
		if (step != null && "test".equalsIgnoreCase(step)){
			mv = new ModelAndView("vue/stepOne");
			log.debug("第一阶段!");
		}
		mv.addObject("user", user);
		return mv;
	}
}
