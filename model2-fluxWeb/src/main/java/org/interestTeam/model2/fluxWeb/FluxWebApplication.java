/**   
 * @Title: FluxWebApplication.java 
 * @Package org.interestTeam.model2.fluxWeb 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2020年1月3日 下午5:49:01 
 * @version V1.0  
 */
package org.interestTeam.model2.fluxWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/** 
 * @ClassName: FluxWebApplication 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2020年1月3日 下午5:49:01 
 *  
 */
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class FluxWebApplication {
	public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(FluxWebApplication.class,args);
    }
}
