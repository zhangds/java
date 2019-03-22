/**   
 * @Title: AccessLimit.java 
 * @Package org.interestTeam.model.models 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2019年3月21日 下午8:21:32 
 * @version V1.0  
 */
package org.interestTeam.model.models;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ClassName: AccessLimit 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2019年3月21日 下午8:21:32 
 *  
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AccessLimit {
 
    int seconds();
    int maxCount();
    boolean needLogin()default true;

}
