/**   
 * @Title: SystemRunning.java 
 * @Package org.interestTeam.model.configure 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月25日 下午4:47:35 
 * @version V1.0  
 */
package org.interestTeam.model.configure;

import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/** 
 * @ClassName: SystemRunning 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月25日 下午4:47:35 
 *  
 */
@ConfigurationProperties(prefix="project")
@Configuration
@Data
public class SystemRunning {

	String pageSize ;
	
	public int getPageSize(){
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		if (pageSize != null && pattern.matcher(pageSize).matches()){
			return Integer.valueOf(pageSize);
		}
		return 20;
	}
	
	String redirect;
	String name;
	String defaultKey;
	String defaultPWD;
}
