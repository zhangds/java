/**   
 * @Title: EncryptService.java 
 * @Package org.interestTeam.model.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 下午4:12:36 
 * @version V1.0  
 */
package org.interestTeam.model2.service;

import org.interestTeam.model2.util.DesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Data;

/** 
 * @ClassName: EncryptService 
 * @Description: 用于密码的加密及解密，采用的DES算法
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月7日 下午4:12:36 
 *  
 */
@Data
@Service
public class EncryptService {
	
	@Value("${project.defaultKey}")
	private String key;
	
	public String encrypt(String string) throws Exception{
		return DesUtil.encrypt(string, key);
	}
	
	public String decrypt(String string) throws Exception{
		return DesUtil.decrypt(string, key);
	}
	
	public String getDefaultPassword() throws Exception{
		return encrypt(key);
	}
}
