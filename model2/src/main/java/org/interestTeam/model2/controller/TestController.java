package org.interestTeam.model2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Controller
//@RequestMapping(value = "/test")
@Slf4j
public class TestController {

	@ApiOperation(value = "解密", notes = "字符串解密")
	@GetMapping(value="/test/decrypt/{pwd}")
	public String decrypt(@PathVariable String pwd,Model model){
		log.debug("首次!");
		model.addAttribute("projectName", "demo");
		return "index/index";
	}

}
