/**   
 * @Title: EncryptService.java 
 * @Package org.interestTeam.model.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 下午4:12:36 
 * @version V1.0  
 */
package org.interestTeam.model.service;

import org.interestTeam.model.models.DesUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import lombok.Data;

/** 
 * @ClassName: EncryptService 
 * @Description: 用于密码的加密及解密，采用的DES算法
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月7日 下午4:12:36 
 *  
 */
@ConfigurationProperties(prefix="project")
@Data
@Service
public class EncryptService {

	private String defaultKey;
	
	public String encrypt(String string) throws Exception{
		return DesUtil.encrypt(string, defaultKey);
	}
	
	public String decrypt(String string) throws Exception{
		return DesUtil.decrypt(string, defaultKey);
	}
}
