/**   
 * @Title: TestController.java 
 * @Package org.interestTeam.model2.fluxWeb.controller 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2020年1月6日 上午10:50:14 
 * @version V1.0  
 */
package org.interestTeam.model2.fluxWeb.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** 
 * @ClassName: TestController 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2020年1月6日 上午10:50:14 
 *  
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

	@SuppressWarnings("serial")
	@GetMapping(value = "/{id}")
    @ResponseBody
    public Flux<String> test(@PathVariable("id") String id) {
		Flux<String> result = Flux.fromIterable(new ArrayList<String>(2){{
			add(id);
			add(id+":"+id);
			}});
        return result;
    }
}
