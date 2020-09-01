/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.kfcenter.kfFlow.rule.bean.BackFormBean;
import com.hb.kfcenter.kfFlow.rule.service.BackFormService;
import com.hb.kfcenter.kfFlow.rule.service.FlowWorkGropService;
import com.hb.kfcenter.kfFlow.rule.service.FlowWrokRuleService;
import com.hb.kfcenter.kfFlow.rule.service.WBSetService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zhangds
 *
 */
@Controller
@RequestMapping(value = "/FlowRule")
@Api(tags = "工作流前台规则设计")
@Slf4j
public class FlowRuleController {

	@GetMapping(value = "/setNodesRule")
	@ApiIgnore
	public String setNodesRule(@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String nodeType,
			HttpServletRequest request, Model model) {
		model.addAttribute("staffno", staffno);
		model.addAttribute("flowId", flowId);
		model.addAttribute("nodeId", nodeId);
		model.addAttribute("nodeType", nodeType);
		ObjectMapper mapper = new ObjectMapper();
		try {
			model.addAttribute("currentNodeWorkGroups",
					mapper.writeValueAsString(
							flowWorkGropService.getCurrentFlowAndNodeIdByWrokGroups(flowId,nodeId)) );
		} catch (Exception e) {
			log.error("error!流程:"+flowId+",节点:"+nodeId+"...获取当前节点的工作组出错!");
		}
		try {
			model.addAttribute("currentNodeRules",
					mapper.writeValueAsString(
							flowWrokRuleService.getCurrentRules(flowId, nodeId,null, "ruleNode")) );
		} catch (Exception e) {
			log.error("error!流程:"+flowId+",节点:"+nodeId+"...获取当前节点的规则出错!");
		}
		
		try {
			model.addAttribute("currentNodeTimeLimitSet",
					mapper.writeValueAsString(
							flowWrokRuleService.getTimeLimitList(flowId, nodeId)) );
		} catch (Exception e) {
			log.error("error!流程:"+flowId+",节点:"+nodeId+"...获取当前节点的时限配置出错!");
		}
		
		try {
			model.addAttribute("currentLines",
					mapper.writeValueAsString(
							flowWrokRuleService.getCurrentNodeToLineAct(flowId, nodeId)) );
		} catch (Exception e) {
			log.error("error!流程:"+flowId+",节点:"+nodeId+"...获取当前行为的规则出错!");
		}
		
		if (log.isDebugEnabled())
			log.debug("design nodeId:"+nodeId);
		return "kfFlowRule/nodeSetting";
	}
	
	@Autowired
	FlowWorkGropService flowWorkGropService;
	
	@PostMapping(value = "/getAllWrokGroup")
	@ApiIgnore
	@ResponseBody
	public Object getAllWrokGroup(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String keyWords, Model model) {
		return flowWorkGropService.getWorkGroupBykeyWord(keyWords);
	}
	
	@PostMapping(value = "/getCurrentNodeWrokGroups")
	@ApiIgnore
	public @ResponseBody Object getCurrentNodeWrokGroups(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId, Model model) {
		return flowWorkGropService.getCurrentFlowAndNodeIdByWrokGroups(flowId,nodeId);
	}
	
	@PostMapping(value = "/saveCurrentNodeWrokGroups")
	@ApiIgnore
	@ResponseBody
	public Object saveCurrentNodeWrokGroups(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String groupIds,Model model) {
		return flowWorkGropService.saveCurrentNodeWrokGroups(staffno,flowId,nodeId,groupIds);
	}
	
	@GetMapping(value = "/setNodeType")
	@ApiIgnore
	public String setNodeType(@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String ruleId,
			@RequestParam(defaultValue = "") String rulePid,
			@RequestParam(defaultValue = " ") String lineId,
			@RequestParam(defaultValue = "ruleNode") String opType,
			HttpServletRequest request, Model model) {
		model.addAttribute("staffno", staffno);
		model.addAttribute("flowId", flowId);
		model.addAttribute("nodeId", nodeId);
		model.addAttribute("ruleId", ruleId);
		model.addAttribute("rulePid", rulePid);
		model.addAttribute("lineId", lineId);
		model.addAttribute("opType", opType);
		return "kfFlowRule/ruleTypeSet";
	}
	
	@Autowired
	FlowWrokRuleService flowWrokRuleService;
	
	@PostMapping(value = "/refreshRule")
	@ApiIgnore
	@ResponseBody
	public Object refreshCurrentRule(
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String lineId,
			@RequestParam(defaultValue = "ruleNode") String opType,
			Model model) {
		return flowWrokRuleService.getCurrentRules(flowId, nodeId,lineId, opType);
	}
	
	@PostMapping(value = "/saveCurrentRule")
	@ApiIgnore
	@ResponseBody
	public Object saveCurrentRule(
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String ruleId,
			@RequestParam(defaultValue = "") String rulePid,
			@RequestParam(defaultValue = " ") String lineId,
			@RequestParam(defaultValue = "") String opType,
			@RequestParam(defaultValue = "") String ruleType,
			Model model) {
		return flowWrokRuleService.saveRule(staffno, flowId, nodeId, ruleId, rulePid,lineId, opType, ruleType);
	}
	
	@PostMapping(value = "/deleCurrentRule")
	@ApiIgnore
	@ResponseBody
	public boolean deleCurrentRule(
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String ruleId,
			@RequestParam(defaultValue = "opType") String opType,
			Model model) {
		
		return flowWrokRuleService.deleCurrentRule(flowId,nodeId,ruleId,opType);
	}
	
	@SuppressWarnings("serial")
	@GetMapping(value = "/setParamRule")
	@ApiIgnore
	public String setParamRule(
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String lineId,
			@RequestParam(defaultValue = "") String ruleId,
			@RequestParam(defaultValue = "iconParam") String ruleType,
			@RequestParam(defaultValue = "ruleNode") String opType, Model model) {
		model.addAttribute("staffno", staffno);
		model.addAttribute("flowId", flowId);
		model.addAttribute("nodeId", nodeId);
		model.addAttribute("lineId", lineId);
		model.addAttribute("ruleId", ruleId);
		model.addAttribute("ruleType", ruleType);
		model.addAttribute("opType", opType);
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			model.addAttribute("currentNode", objectMapper.writeValueAsString(flowWrokRuleService.getCurrentRule(flowId, nodeId, lineId, ruleId ,opType)));
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		String result = "kfFlowRule/setParamRule";
		//iconParam,iconCondition,iconAction
		if ("iconCondition".equalsIgnoreCase(ruleType)) {
			model.addAttribute("selectEle", new LinkedHashMap<String,String>(4){{
				put("==","等于");
				put("!=","不等于");
				put("like","包含");
				put("not like","不包含");
//				put(">","大于");
//				put(">=","大于等于");
//				put("<","小于");
//				put("<=","小于等于");
			}});
			result = "kfFlowRule/setConditionRule";
		}else if ("iconAction".equalsIgnoreCase(ruleType)) {
			model.addAttribute("selectEle", new LinkedHashMap<String,String>(4){{
				put("do","执行");put("like","刷选 ");
				//put("execute","接口执行");
			}});
			result = "kfFlowRule/setNodeActionRule";
		}else {
			model.addAttribute("selectEle", new LinkedHashMap<String,String>(4){{
				put("baseForms","基础表单");
				put("services","服务请求");
				put("preNode","上一环节");
				put("systems","系统参数");
			}});
		}
		return result;
	}
	
	@PostMapping(value = "/saveOneRuleParam")
	@ApiIgnore
	@ResponseBody
	public Object saveOneRuleParam(
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String ruleId,
			@RequestParam(defaultValue = "") String opType,
			@RequestParam(defaultValue = "") String ruleType,
			@RequestParam(defaultValue = "") String showParam,
			@RequestParam(defaultValue = "") String realParam,
			@RequestParam(defaultValue = "") String orderNo,
			Model model) {
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		try {
			map.put("flag", flowWrokRuleService.saveOneRuleParam(flowId, nodeId, ruleId,opType, ruleType, showParam, realParam,orderNo));
//			map.put("showP", showParam);
//			map.put("realP", realParam);
		} catch (Exception e) {
			map.put("flag",false);
		}
		return map;
	}
	
	@PostMapping(value = "/getParamsByType")
	@ApiIgnore
	@ResponseBody
	public Object getParamsByType(
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String ruleId,
			@RequestParam(defaultValue = "baseForms") String type,
			@RequestParam(defaultValue = "") String keyword,
			Model model) {
		return flowWrokRuleService.getParamsByType(flowId,nodeId,ruleId,type,keyword);
	}
	
	@GetMapping(value = "/setAllRule")
	@ApiIgnore
	public String setAllRule(
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String lineId,
			@RequestParam(defaultValue = "ruleNode") String opType, Model model) {
		//ruleLine
		model.addAttribute("staffno", staffno);
		model.addAttribute("flowId", flowId);
		model.addAttribute("nodeId", nodeId);
		model.addAttribute("lineId", lineId);
		model.addAttribute("opType", opType);
		ObjectMapper mapper = new ObjectMapper();
		try {
			model.addAttribute("currentRules",
				mapper.writeValueAsString(
						flowWrokRuleService.getCurrentRules(flowId, nodeId, lineId, opType)));
		} catch (Exception e) {
			log.error("error!流程:"+flowId+",节点:"+nodeId+",lineID:"+lineId+"...获取当前规则出错!");
		}
		return "kfFlowRule/ruleIndex";
	}
	
	@Autowired
	BackFormService backFormService;
	
	@SuppressWarnings("serial")
	@PostMapping(value = "/getBackFormInfo")
	@ApiIgnore
	@ResponseBody
	public Object getBackFormInfo(
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			Model model) {
		Map<String,Object> resultMap = new LinkedHashMap<String, Object>(10);
		List<String[]> allBackForms = null;
		List<BackFormBean> backForms = null;
		List<BackFormBean> addDzForms = null;
		try {
			allBackForms = backFormService.getAllBackFormList();
			
			addDzForms = backFormService.getCurrentNodeBackFormList(flowId,nodeId);
			if (addDzForms == null || addDzForms.size() == 0) {
				addDzForms = new ArrayList<BackFormBean>(2) {{
					add(new BackFormBean() {{
						setId("dz");setName("定则");;setType("dz");setIsKj("N");setIsBt("N");
					}});
					add(new BackFormBean() {{
						setId("jdzqx");setName("记单准确性");setType("jdzqx");setIsKj("N");setIsBt("N");
					}});
				}};
			}else {
				backForms = addDzForms.stream().filter(formBean -> formBean !=null && "backForm".equals(formBean.getType())).collect(Collectors.toList());
				addDzForms = addDzForms.stream().filter(formBean -> formBean !=null && !"backForm".equals(formBean.getType())).collect(Collectors.toList());
			}
		} catch (Exception e) {
			log.error("获取回单模板列表错误!");
		}
		
		resultMap.put("allBackForms", allBackForms);
		resultMap.put("backForms", backForms);
		resultMap.put("addDzForms", addDzForms);
		return resultMap;
	}
	
	@PostMapping(value = "/getBackFormInfoById")
	@ApiIgnore
	@ResponseBody
	public Object getBackFormInfoById(
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String backFormId,
			Model model) {
		List<BackFormBean> result = null ;
		try {
			result = backFormService.getBackFormById(backFormId);
		} catch (Exception e) {
			log.error("获取回单模板出错!id:"+backFormId);
		}
		return result;
	}
	
	@PostMapping(value = "/saveBackForms")
	@ApiIgnore
	@ResponseBody
	public Object saveBackForms(
			@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String backFormId,
			@RequestParam(value = "backFromEle") String backFromJson,
			@RequestParam(value = "zrdxEle") String zrdxJson,
			Model model) {
		
		return backFormService.saveBackFormsRule(staffno,flowId,nodeId,backFormId,backFromJson,zrdxJson);
	}
	
	@Autowired
	WBSetService wbSetService;
	
	@PostMapping(value="getAllWbConfigs")
	@ApiIgnore
	@ResponseBody
	public Object getAllWbConfigs(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,Model model) {
		Map<String,Object> result = new LinkedHashMap<String, Object>(2);
		result.put("configs", wbSetService.getWbExtendConfig());
		result.put("set", wbSetService.getCurrentNodeWbExtend(flowId,nodeId));
		return result;
	}
	
	@PostMapping(value="setCurrentNodeWbEx")
	@ApiIgnore
	@ResponseBody
	public Object setCurrentNodeWbEx(@RequestParam(defaultValue = "") String staffno,
			@RequestParam(defaultValue = "") String flowId,
			@RequestParam(defaultValue = "") String nodeId,
			@RequestParam(defaultValue = "") String sysId,
			@RequestParam(defaultValue = "") String mothodId,
			@RequestParam(defaultValue = "") String classZ,Model model) {

		return wbSetService.setCurrentNodeWbExtendConfig(flowId,nodeId,sysId,mothodId,classZ);
	}
}
