package org.interestTeam.model.models;

import org.interestTeam.model.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class HttpClientUtilTest {
	
	@org.junit.Test
    public void test(){
		String s = HttpClientUtil.doPost("http://cert.wh-eport.cn:9099/api/user/zhangds", "{\"FNumber\":\"zhangds\",\"FName\":\"zhangds\",\"FMyName\":\"zhangds\"}");
    	log.debug("back:"+s);
    	s = HttpClientUtil.doPut("http://cert.wh-eport.cn:9099/api/user/zhangds", "{\"FNumber\":\"zhangds\",\"FName\":\"zhangds\",\"FMyName\":\"zhangds\"}");
    	log.debug("back:"+s);
    	s = HttpClientUtil.doGet("http://cert.wh-eport.cn:9099/api/user/zhangds");
    	log.debug("back:"+s);
    	String s1 = HttpClientUtil.doDelete("http://cert.wh-eport.cn:9099/api/user/zhangds");
    	log.debug("back:"+s1);
    }
}
