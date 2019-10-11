package com.ctcc.kf.springboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/test")
@Slf4j
public class TestController {

	@ApiOperation(value = "解密", notes = "字符串解密")
	@GetMapping(value="/decrypt/{pwd}")
	public String decrypt(@PathVariable String pwd){
		log.debug("首次!");
		return "ftl/index/index";
	}
}
