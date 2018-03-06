/**   
 * @Title: RoleController.java 
 * @Package org.interestTeam.model.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月25日 上午10:33:13 
 * @version V1.0  
 */
package org.interestTeam.model.controller;

import java.util.List;

import org.interestTeam.model.database.entity.UserEntity;
import org.interestTeam.model.models.ResultVo;
import org.interestTeam.model.models.RoleDao;
import org.interestTeam.model.models.SessionKeyConstants;
import org.interestTeam.model.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/** 
 * @ClassName: RoleController 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月25日 上午10:33:13 
 *  
 */
@RestController
@RequestMapping(value = "/role")
@SessionAttributes({ SessionKeyConstants.USER })
@Slf4j
public class RoleController {

	@RequestMapping(value="",method = {RequestMethod.GET})
	@ResponseBody
	@ApiIgnore
	public ModelAndView changePwd(@ModelAttribute(SessionKeyConstants.USER) UserEntity user) {
		ModelAndView mv = new ModelAndView("role/role");// 模板文件的名称，不需要指定后缀
		mv.addObject("user", user);
		return mv;
	}
	
	@Autowired
	RoleService roleService;
	
	@ApiOperation(value = "获取角色清单", notes = "获取全部角色清单，以树形结构组织")
	@ApiImplicitParams({
			})
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/getRoles", method = { RequestMethod.POST })
	@ResponseBody
	public Object getAllRoleList(@SessionAttribute(SessionKeyConstants.USER) UserEntity user) {
		List<RoleDao> list = null;
		try {
			list = roleService.getRoleList();
			return new ResultVo(true,"提取成功!",list,null);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultVo(false,"提取失败!",list,e.getMessage());
		}
	}
	
	
}
