/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.kfcenter.kfFlow.engine.service.FlowEngineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author zhangds
 *
 */
@Controller
@RequestMapping(value = "/FlowWebEngine")
@Api(tags = "工作流执行引擎")
public class FlowEngineController {
	
	@Autowired
	FlowEngineService flowEngineService;
	
//	@Autowired
//	FlowEngineDataService flowEngineDataService;
	
	//@GetMapping(value = "/getNodeInfo")
	@RequestMapping(value="/getNodeInfo",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value = "当前节点允许有哪些操作及工作组", notes = "获取当前节点允许有哪些操作及工作组")
	@ResponseBody
	public Object getNodeInfo(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String workCaseId,/*工单实例id*/
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String serviceId,
			@RequestParam(defaultValue = "") String paramJson) {
		//Map<String,Object> result = null;new LinkedHashMap<String,Object>();
		if ( StringUtils.isNotEmpty(flowId) ) {
			return flowEngineService.getCurrentStepInfo(workCaseId,flowId,serviceId,paramJson);
		}
		
		return null;
	}
	
	//@GetMapping(value = "/toNextFlowNode")
	@RequestMapping(value="/toNextFlowNode",method={RequestMethod.POST,RequestMethod.GET})
	@ApiOperation(value = "驱动工作流流转", notes = "驱动工作流流转到下一个节点和工作组")
	@ResponseBody
	public Object toNextFlowNode(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String workCaseId,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String lineId,
			@RequestParam(defaultValue = "") String serviceId,
			@RequestParam(defaultValue = "") String paramJson,
			@RequestParam(defaultValue = "") String userGroupId) {

		//Map<String,Object> result = null;new LinkedHashMap<String,Object>();
		if (StringUtils.isNotEmpty(workCaseId) &&
				StringUtils.isNotEmpty(flowId)) {
			return flowEngineService.toNextStep(staffno,workCaseId,flowId,nodeId,lineId,serviceId,userGroupId,paramJson);
		}
		
		return null;
	}
}