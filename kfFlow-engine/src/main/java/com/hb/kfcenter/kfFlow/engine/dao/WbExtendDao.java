package com.hb.kfcenter.kfFlow.engine.dao;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WbExtendDao {
	@Autowired
	private TaskExecutor engineWbExecutor;
	
	public void send(String classZ,String method,String workCaseId,String serviceId) {  
		engineWbExecutor.execute(new Runnable() {  
	        @Override  
	        public void run() {  
	            this.invokMethod(classZ,method,workCaseId,serviceId);
	        }  
	    	public void invokMethod(String classZ,String methoded,String workCaseId,String serviceId) {
	    		try {
					if (StringUtils.isNotEmpty(classZ) && StringUtils.isNotEmpty(method)) {
						Class<?> clazz = Class.forName(classZ);
						Class<?>[] argTypes=new Class[2];
						argTypes[0]=String.class;
						argTypes[1]=String.class;
						Method method = clazz.getDeclaredMethod(methoded,argTypes);
						method.invoke(clazz.newInstance(), new Object[]{serviceId,workCaseId});
					}
				} catch (Exception e) {
					log.error("执行外部接口出错!工单编号:"+workCaseId);
				}
	    	}
	    });  
	}
	
}
