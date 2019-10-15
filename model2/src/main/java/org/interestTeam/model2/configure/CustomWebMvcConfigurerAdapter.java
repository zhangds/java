package org.interestTeam.model2.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dongshengzhang
 * 标注此文件为一个配置项，spring boot才会扫描到该配置。该注解类似于之前使用xml进行配置
 */
@Configuration
public class CustomWebMvcConfigurerAdapter implements WebMvcConfigurer {
	
	@Autowired
	WebInterceptor webInterceptor;
	
	@Autowired
	AccessLimitInterceptor accessLimitInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webInterceptor).excludePathPatterns("/","/error","/static/**","/swagger**/**").addPathPatterns("/**");
		registry.addInterceptor(accessLimitInterceptor);
		
	}
	
}