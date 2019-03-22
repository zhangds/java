/**   
 * @Title: Tests.java 
 * @Package com.powerbridge.manifest.manifestFrame.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月4日 上午10:48:29 
 * @version V1.0  
 */
package com.powerbridge.manifest.manifestFrame.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/** 
 * @ClassName: Tests 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月4日 上午10:48:29 
 *  
 */
public class Tests {

	@Test
    public void test() throws Exception{
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		System.out.println( df.format(now) );
	}

}
