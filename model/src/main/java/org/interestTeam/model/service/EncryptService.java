/**   
 * @Title: EncryptService.java 
 * @Package org.interestTeam.model.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 下午4:12:36 
 * @version V1.0  
 */
package org.interestTeam.model.service;

import org.interestTeam.model.configure.SystemRunning;
import org.interestTeam.model.models.DesUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	SystemRunning systemRunning;
	
	public String encrypt(String string) throws Exception{
		return DesUtil.encrypt(string, systemRunning.getDefaultKey());
	}
	
	public String decrypt(String string) throws Exception{
		return DesUtil.decrypt(string, systemRunning.getDefaultKey());
	}
	
	public String getDefaultPassword() throws Exception{
		return encrypt(systemRunning.getDefaultPWD());
	}
}
