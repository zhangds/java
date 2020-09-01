/**
 * 
 */
package com.hb.kfcenter.kfFlow.design.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.kfcenter.kfCore.models.Configuration;
import com.hb.kfcenter.kfCore.util.IsNumber;
import com.hb.kfcenter.kfFlow.design.bean.WorkFlowBean;
import com.hb.kfcenter.kfFlow.design.service.FlowManagerService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zhangds
 *
 */
@Controller
@RequestMapping(value = "/FlowWebDesign")
@Api(tags = "工作流前台设计管理")
@Slf4j
public class FlowDesignController {

	@GetMapping(value = "/list")
	@ApiIgnore
	public String list(@RequestParam(defaultValue = "") String staffno, HttpServletRequest request, Model model) {
		/* staffno需要验证权限 */
		model.addAttribute("staffno", staffno);
		return "kfFlowDesign/managerList";
	}
	
	@Autowired
	FlowManagerService flowManagerService;
	
	@PostMapping(value = "/getWkList")
	@ApiIgnore
	@ResponseBody
	public Object getWkList(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "0") int currentNum,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "") String wkId,
			@RequestParam(defaultValue = "") String wkName,
			@RequestParam(defaultValue = "") String wkRemark) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		if (currentNum == 0 || currentNum == 1) {
			int _counts = flowManagerService.getAllCounts(staffno, wkId, wkName, wkRemark);
			result.put("counts", _counts);
			result.put("pageSize", pageSize);
			result.put("pageNum", _counts / pageSize + (_counts % pageSize > 0 ? 1 : 0));
			result.put("showLaypage", true);
		}
		result.put("datas",flowManagerService.getCurrentPageDatas(currentNum, pageSize, wkId, wkName, staffno, wkRemark));
		if (log.isDebugEnabled())
			log.info("currentNum:"+currentNum);
		return result;
	}
	
	@GetMapping(value = "/detail")
	@ApiIgnore
	public String detail(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId, Model model) {
		model.addAttribute("staffno", staffno);
		WorkFlowBean _dao = null;
		if (!"".equals(flowId.trim())) {
			// 信息展示，如果没查到就变成新增
			_dao = flowManagerService.getCurrentWorkFlowInfo(flowId);
		}
		if (_dao == null) {
			_dao = flowManagerService.getDefaultNewWorkFlowInfo(staffno);
		}
		model.addAttribute("detailInfo", _dao);
		model.addAttribute("stats", flowManagerService.getAllStates());
		return "kfFlowDesign/detailWKInfo";
	}
	
	@PostMapping(value = "/WkOneSave")
	@ApiIgnore
	@ResponseBody
	public Object WkOneSave(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId, @RequestParam(defaultValue = "") String flowName,
			@RequestParam(defaultValue = "A") String flowStates, @RequestParam(defaultValue = "") String flowForm,
			@RequestParam(defaultValue = "") String flowRemark, @RequestParam(defaultValue = "V1.0") String version,
			@RequestParam(defaultValue = "update") String opType, HttpServletRequest request) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		boolean flag = false;
		WorkFlowBean _dao = new WorkFlowBean() {
			{
				setWkId(flowId);
				setWkName(flowName);
				setWkRemark(flowRemark);
				setCreaterId(staffno);
//			setCreateDt(TaskDate.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				setCurrVersion(version);
				setDefForm(flowForm);
				setWkState(flowStates);
				setUpdaterId(staffno);
			}
		};
		if (!StringUtils.isEmpty(opType) && "add".equalsIgnoreCase(opType)) {
			flag = flowManagerService.saveWorkFlow(_dao);
		} else {
			String _version = version.replaceAll("V", "");
			if (!StringUtils.isEmpty(_version) && IsNumber.isDouble(_version)) {
				double _d = Double.parseDouble(_version) + 0.1;
				_dao.setCurrVersion("V" + String.format("%.1f", _d));
			}
			flag = flowManagerService.updateWorkFlow(_dao);
		}
		result.put("flag", flag);
		result.put("msg", flag == true ? "操作成功!" : "操作失败!");
		return result;
	}
	
	@GetMapping(value = "/design")
	@ApiIgnore
	public String indexTo(@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String staffno, HttpServletRequest request, Model model) {
		model.addAttribute("staffno", staffno);
		model.addAttribute("flowId", flowId);
		model.addAttribute("flowJson", flowManagerService.getFlowJson(flowId));
		model.addAttribute("nodeSetUrl",
				Configuration.getInstance().getProperty("com.hb.kfcenter.kfFlow.design.nodeRule"));
		return "kfFlowDesign/designer";
	}
	
	@PostMapping(value = "/designSave")
	@ApiIgnore
	@ResponseBody
	public Object savaFlow(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String flowJson, Model model) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		try {
			boolean flag = flowManagerService.saveDesignWorkflow(flowId, flowJson, staffno);
			result.put("flag", flag);
			if (flag) {
				result.put("msg", "流程图保存成功!");
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			result.put("flag", false);
			result.put("msg", "流程图保存失败!流程数据保存异常!");
		}
		return result;
	}
	
}
