/**   
 * @Title: LoginService.java 
 * @Package org.interestTeam.model.service 
 * @Description: 登录服务
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月7日 下午10:16:56 
 * @version V1.0  
 */
package org.interestTeam.model.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.interestTeam.model.database.entity.UserEntity;
import org.interestTeam.model.models.LoginStateVo;
import org.interestTeam.model.models.SessionKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;

/** 
 * @ClassName: LoginService 
 * @Description: 登录服务
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月7日 下午10:16:56 
 *  
 */
@Service
public class LoginService {

	@Autowired
	UserService userService;
	
	@Autowired
	EncryptService encryptService;
	
	public LoginStateVo login(String userId,String originPwd,HttpSession session) throws Exception{
		UserEntity user = userService.getUserById(userId);
		String pwd = encryptService.encrypt(originPwd);
		LoginStateVo state = new LoginStateVo();
		state.setSuccess(false);
		if (user != null && user.getLoginPassword().equals(pwd)){
			session.setAttribute( SessionKeyConstants.USER, user);
			state.setSuccess(true);
			state.setMsg("登录成功!");
		}else if (user != null && !user.getLoginPassword().equals(pwd)){
			state.setMsg("密码错误!");
		}else{
			state.setMsg("用户名不正确!");
		}
		return state;
	}
	
	public void clearCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
			}
		}
	}
	
}
