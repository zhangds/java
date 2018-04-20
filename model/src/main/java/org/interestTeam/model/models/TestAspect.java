/**   
 * @Title: TestAspect.java 
 * @Package com.hakj.modelForCT.dao 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年4月13日 下午10:57:41 
 * @version V1.0  
 */
package org.interestTeam.model.models;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: TestAspect 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年4月13日 下午10:57:41 
 *  
 */
@Aspect
@Component
public class TestAspect {
	@Autowired  
	private  HttpServletRequest request;
	
	@Pointcut("execution(public * org.interestTeam.model.controller.*.*(..)) "
			+ "&& @annotation(org.interestTeam.model.models.MyAnnotation)" )
    public void addAdvice(){}
	@Before("addAdvice()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("second before");
    }
	
	@After("addAdvice()")
	public void doAfter(JoinPoint joinPoint) throws Throwable {
        System.out.println("second After");
        System.out.println(request.getContextPath());
    }
	
    @Around("@annotation(myAnnotation)")
    public Object around(ProceedingJoinPoint pjp, MyAnnotation myAnnotation) {
    	//获取注解里的值
        System.out.println("second around:" + myAnnotation.desc());
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
