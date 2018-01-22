/**   
 * @Title: BigScreenController.java 
 * @Package com.powerbridge.manifest.manifestFrame.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月1日 下午8:56:50 
 * @version V1.0  
 */
package com.powerbridge.manifest.manifestFrame.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.powerbridge.manifest.manifestFrame.service.BigScreenService;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: BigScreenController 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月1日 下午8:56:50 
 *  
 */
@RestController
@Slf4j
public class BigScreenController {

	@RequestMapping(value="/",method = {RequestMethod.GET})
	@ResponseBody
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("bigScreen/index");// 模板文件的名称，不需要指定后缀
		return mv;
	}
	
	@RequestMapping(value="/one",method = {RequestMethod.GET})
	@ResponseBody
	public ModelAndView screenOne() {
		ModelAndView mv = new ModelAndView("bigScreen/centerOne");// 模板文件的名称，不需要指定后缀
		return mv;
	}
	
	@RequestMapping(value="/two",method = {RequestMethod.GET})
	@ResponseBody
	public ModelAndView screenTwo() {
		ModelAndView mv = new ModelAndView("bigScreen/centerTwo");// 模板文件的名称，不需要指定后缀
		return mv;
	}
	
	@RequestMapping(value="/screen",method = {RequestMethod.GET})
	@ResponseBody
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("bigScreen/home");// 模板文件的名称，不需要指定后缀
		return mv;
	}

	@Autowired
	BigScreenService bigScreenService;
	
	@RequestMapping(value="/screenGetData",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object getScreenData() {
		Map<String,String> map = null;
		try {
			map = bigScreenService.getScreenOneData();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return map;
	}
}
