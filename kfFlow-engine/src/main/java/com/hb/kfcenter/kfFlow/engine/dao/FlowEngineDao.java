/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hb.kfcenter.kfCore.models.BeanFactory;
import com.hb.kfcenter.kfCore.util.IsNumber;
import com.hb.kfcenter.kfFlow.engine.bean.ActBean;
import com.hb.kfcenter.kfFlow.engine.bean.BackFormBean;
import com.hb.kfcenter.kfFlow.engine.bean.BackFormEleBean;
import com.hb.kfcenter.kfFlow.engine.bean.FlowInitBean;
import com.hb.kfcenter.kfFlow.engine.bean.GroupBean;
import com.hb.kfcenter.kfFlow.engine.bean.NodeBean;
import com.hb.kfcenter.kfFlow.engine.bean.RuleBean;
import com.hb.kfcenter.kfFlow.engine.service.FlowEngineDataService;
import com.hb.kfcenter.kfFlow.engine.service.FlowEngineDriverService;
import com.hb.kfcenter.kfFlow.engine.service.FlowEngineService;
import com.hb.kfcenter.kfFlow.engine.service.HelpUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangds
 *
 */
@Component
@Slf4j
public class FlowEngineDao implements FlowEngineService {
	
	@Autowired
	FlowEngineDataService flowEngineDataService;
	
	public int getCaseByWCaseAndFlowId(String workCaseId, String flowId,FlowInitBean init) {
		int _result = 0;
		
		if ( init != null && StringUtils.isNotEmpty( init.getFlowId()) ) {
			if (init.getFlowId().equals(flowId) ) {
				//原流程驱动
				_result = 3;
			}else {
				//重启新流程
				_result = 2;
			}
		}else {
			//新建流程实例
			_result = 1;
		}
		return _result ;
	}
	
	/**
	 * 寻找流程的当前节点
	 */
	public NodeBean getCurrentNode(String nodeId,List<NodeBean> nodeList) {
		List<NodeBean> currentNodeList = null;
		if ( StringUtils.isEmpty(nodeId) ) {
			currentNodeList = nodeList.stream().filter(node -> node != null && StringUtils.isNotEmpty(node.getType()) 
					&& node.getType().toLowerCase().contains("start")).collect(Collectors.toList());
		}else {
			currentNodeList = nodeList.stream().filter(node -> node != null && StringUtils.isNotEmpty(node.getId()) 
					&& node.getId().equals(nodeId)).collect(Collectors.toList());
		}
		return ((currentNodeList != null && currentNodeList.size()>0)?currentNodeList.get(0):null);
	}
	
	private boolean checkActRule(String flowId,NodeBean node,ActBean actBean,Map<String,String> paramJson) {
		boolean flag = true ;
		if ( StringUtils.isNotEmpty(flowId) && node != null &&
				StringUtils.isNotEmpty(node.getId()) && actBean != null &&
						StringUtils.isNotEmpty(actBean.getId())) {
			List<RuleBean> _ruleList = flowEngineDataService.getCurrentNodeRulesByType(flowId,node.getId(),"actLine");
			_ruleList = _ruleList.stream().filter(ruleBean -> ruleBean !=null && actBean.getId().equalsIgnoreCase(ruleBean.getOrtherId())).collect(Collectors.toList());
			
			if (_ruleList != null && _ruleList.size() >0) {
				List<RuleBean> _ruleAct = _ruleList.stream().filter(ruleBean -> ruleBean != null && StringUtils.isNotEmpty(ruleBean.getParaThree()) 
						&& IsNumber.isNumeric(ruleBean.getParaThree()) && StringUtils.isNotEmpty(ruleBean.getType()) && ruleBean.getType().toLowerCase().equals("action")).collect(Collectors.toList());
				if (_ruleAct != null && _ruleAct.size()>0) {
					_ruleAct.sort((o1,o2) -> Integer.valueOf(o1.getParaThree()) -Integer.valueOf(o2.getParaThree()));
					for (RuleBean ruleBean :_ruleAct) {
						boolean _checked = helpUtilService.parseToExpression(ruleBean,_ruleList,paramJson);
						if ( _checked && StringUtils.isNotEmpty( ruleBean.getParaTwo() )) {
							return Boolean.valueOf( ruleBean.getParaTwo());
						}
					}
				}
				
			}
			
		}
		return flag;
	}
	
	private void setNodeGroupIds(String workCaseId,String serviceId,Map<String,String> paramJson
			,String flowId,NodeBean currentNode,NodeBean nodeBean){
		List<GroupBean> _groupList = flowEngineDataService.getCurrentNodeWKGroupsByFlowIdAndNodeId(flowId,nodeBean.getId());
		List<RuleBean> _ruleList = nodeBean.getRuleList();
		if (_ruleList != null && _ruleList.size() > 0) {
			List<RuleBean> _ruleAct = _ruleList.stream().filter(ruleBean -> ruleBean != null && StringUtils.isNotEmpty(ruleBean.getParaThree()) 
					&& IsNumber.isNumeric(ruleBean.getParaThree()) && StringUtils.isNotEmpty(ruleBean.getType()) && ruleBean.getType().toLowerCase().equals("action")).collect(Collectors.toList());
			_ruleAct.sort((o1,o2) -> Integer.valueOf(o1.getParaThree()) -Integer.valueOf(o2.getParaThree()));
			for (RuleBean ruleBean :_ruleAct) {
				boolean _checked = helpUtilService.parseToExpression(ruleBean,_ruleList,paramJson);
				if ( _checked && StringUtils.isNotEmpty( ruleBean.getParaOne() )
						&& StringUtils.isNotEmpty( ruleBean.getParaTwo() )) {
					String[] _string = ruleBean.getParaTwo().split(";");
					if ("do".equalsIgnoreCase(ruleBean.getParaOne())) {
						if (_string != null && _string.length >0) {
							List<GroupBean> _ruleGroupList = new ArrayList<GroupBean>(100); 
							for (String _s : _string) {
								for (GroupBean _bean : _groupList) {
									if (_bean.getId().equalsIgnoreCase(_s) || _bean.getName().equalsIgnoreCase(_s)) {
										_ruleGroupList.add(_bean);
										break;
									}
								}
							}
							if (_ruleGroupList.size() >0) {
								nodeBean.setGroupList(_ruleGroupList);
							}
						}
					}else {
						if (_string != null && _string.length >0) {
							/*执行节点规则引擎筛选工作组,默认筛选地市 编码 */
							String _cityId = ruleBean.getParaTwo();
							if (paramJson.containsKey(ruleBean.getParaTwo())) {
								_cityId = paramJson.get(ruleBean.getParaTwo());
							}
							final String _tempId = _cityId;
							List<GroupBean> _ruleGroupList = _groupList.stream().filter(_groupBean -> _groupBean!=null && _tempId.equalsIgnoreCase(_groupBean.getCityId())).collect(Collectors.toList());
							if (_ruleGroupList != null && _ruleGroupList.size()>0)
								nodeBean.setGroupList(_ruleGroupList);
						}
					}
					break;
				}
			}
		}
		if (nodeBean.getGroupList() == null || nodeBean.getGroupList().size() == 0)
			nodeBean.setGroupList(_groupList);		
	}
	/**
	 * 寻找流程的当前节点操作的行为
	 */
	public void getCurrentNodeToActs(String workCaseId,String serviceId,
			Map<String,String> paramJson,String flowId,NodeBean currentNode,
			List<ActBean> actsList,List<NodeBean> nodesList){
		if (currentNode != null && actsList != null && nodesList != null) {
			for (ActBean _bean : actsList) {
				if ( _bean != null && StringUtils.isNotEmpty(_bean.getFromNode()) 
						&& _bean.getFromNode().equals(currentNode.getId())) {
					List<ActBean> _actsList = currentNode.getActList();
					if (_actsList == null)
						_actsList = new ArrayList<ActBean>(8);
					for (NodeBean _nodeBean: nodesList) {
						if ( _nodeBean != null && 
								StringUtils.isNotEmpty(_bean.getNextNodeId()) &&
								_bean.getNextNodeId().equals(_nodeBean.getId())) {
							//判断连线规则是否生效
							boolean flag = checkActRule(flowId,currentNode,_bean,paramJson);
							if (flag) {
								//根据规则引擎获取当前节点的工作组
								setNodeGroupIds(workCaseId,serviceId,paramJson,flowId,currentNode,_nodeBean);
								_bean.setNextNode(_nodeBean);
								_actsList.add(_bean);
								currentNode.setActList(_actsList);
							}
						}
					}
//					_actsList.add(_bean);
//					currentNode.setActList(_actsList);
				}
			}
		}
	}
	
	public double getNodeLimitDt(NodeBean node,String flowId,Map<String,String> paramJson) {
		if (node != null && StringUtils.isNotEmpty(node.getId()) ) {
			List<RuleBean> _ruleList = flowEngineDataService.getCurrentNodeRulesByType(flowId,node.getId(),"timeLimit");
			if (_ruleList != null && _ruleList.size() >0) {
				List<RuleBean> _ruleAct = _ruleList.stream().filter(ruleBean -> ruleBean != null && StringUtils.isNotEmpty(ruleBean.getParaThree()) 
						&& IsNumber.isNumeric(ruleBean.getParaThree()) && StringUtils.isNotEmpty(ruleBean.getType()) && ruleBean.getType().toLowerCase().equals("action")).collect(Collectors.toList());
				if (_ruleAct != null && _ruleAct.size()>0) {
					_ruleAct.sort((o1,o2) -> Integer.valueOf(o1.getParaThree()) -Integer.valueOf(o2.getParaThree()));
					for (RuleBean ruleBean :_ruleAct) {
						boolean _checked = helpUtilService.parseToExpression(ruleBean,_ruleList,paramJson);
						if (_checked && IsNumber.isDouble(ruleBean.getParaTwo())) {
							return Double.valueOf(ruleBean.getParaTwo());
						}
					}
				}
				
			}
			
		}
		return 6L;
	}
	
	public double getFlowLimitDt(NodeBean node,List<NodeBean> allNodes,String flowId,Map<String,String> paramJson) {
		if (node != null && StringUtils.isNotEmpty(node.getType()) && allNodes != null) {
			if (node.getType().contains("start")) {
				return getNodeLimitDt(node,flowId,paramJson);
			}else {
				List<NodeBean> _list = allNodes.stream().filter(nodebean -> nodebean !=null && StringUtils.isNotEmpty(nodebean.getType()) 
						&& nodebean.getType().contains("start") ).collect(Collectors.toList());
				NodeBean _node = _list != null && _list.size() >0 ?_list.get(0):null;
				return getNodeLimitDt(_node,flowId,paramJson);
			}
		}
			
		return 24L;
	}
	
	
	public void setLimitDt(NodeBean node,List<NodeBean> allNodes, String flowId,Map<String,String> paramJson) {
		node.setStepLimitDt(getNodeLimitDt(node,flowId,paramJson));
		node.setLimitDt(getFlowLimitDt(node,allNodes,flowId,paramJson));
	}
	
	@Autowired
	HelpUtilService helpUtilService;
	
	@Override
	public Map<String, Object> getCurrentStepInfo(String workCaseId, String flowId,String serviceId,String paramJson) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		try {
			List<NodeBean> _nodes = flowEngineDataService.getNodesByFlowId(flowId);
			List<ActBean> _lines = flowEngineDataService.getActsByFlowId(flowId);
			FlowInitBean _init = flowEngineDataService.getWorkFlowInitByFormId(workCaseId);
			String _nodeId = (getCaseByWCaseAndFlowId(workCaseId,flowId,_init) == 3 ? _init.getNodeId() : null);
			NodeBean currentNode = getCurrentNode(_nodeId,_nodes);
			if ( currentNode != null ) {
				currentNode.setDGroupId(_init != null ? _init.getGroupId() : null);
				Map<String,String> jsonMap = helpUtilService.getJsonStringToLevelMap(paramJson);
				jsonMap = flowEngineDataService.addOtherValue(workCaseId,serviceId,jsonMap);
				getCurrentNodeToActs(workCaseId,serviceId,
						jsonMap,flowId,currentNode,_lines,_nodes);
				setLimitDt(currentNode,_nodes,flowId,jsonMap);
				/*backForms*/
				setBackFormConf(currentNode,flowId,jsonMap);
				result.put("datas", currentNode);
				result.put("flag", true);
			}else {
				result.put("flag", false);
				result.put("msg", "实例表中的节点ID不存在!");
			}

			//getStepInfo(flowId,_nodeId,_nodes,_lines);
		} catch (Exception e) {
			log.error("获取工作流路径出错！flowId:"+flowId+",工单实例ID:"+workCaseId);
			result.put("flag", false);
			result.put("msg", "获取工作流路径出错！flowId:"+flowId+",工单实例ID:"+workCaseId);
		}
		
		/*
		 * switch (_flagNo) { case 1: break; case 2:
		 * 
		 * break; case 3: break; default: break; }
		 */
		return result;
	}
	
	public void setBackFormConf(NodeBean node, String flowId,Map<String,String> jsonMap) {
		if (node != null && StringUtils.isNotEmpty(node.getId())&& StringUtils.isNotEmpty(flowId)) {
			List<BackFormEleBean> _list = flowEngineDataService.getAllNodeBackFormSets(flowId,node.getId());
			List<RuleBean> _rulesList = flowEngineDataService.getAllNodeBackFormRules(flowId,node.getId());
			if (_list != null) {
				_list.forEach(l -> setBackFormsRuls(l,_rulesList,jsonMap));
			}
			List<BackFormEleBean> _bfs = _list.stream().filter(backfEleBean -> backfEleBean != null 
					&& StringUtils.isNotEmpty(backfEleBean.getEleId()) && !"dz".equalsIgnoreCase(backfEleBean.getEleId()) 
					&& !"jdzqx".equalsIgnoreCase(backfEleBean.getEleId()) ).collect(Collectors.toList());
			List<BackFormEleBean> _set = _list.stream().filter(backfEleBean -> backfEleBean != null 
					&& StringUtils.isNotEmpty(backfEleBean.getEleId()) && ("dz".equalsIgnoreCase(backfEleBean.getEleId()) 
					|| "jdzqx".equalsIgnoreCase(backfEleBean.getEleId())) ).collect(Collectors.toList());
			BackFormBean _bfBean = new BackFormBean() {{
				setId(_list != null && _list.size()>0 && StringUtils.isNotEmpty(_list.get(0).getBackFormId())?_list.get(0).getBackFormId():"");
				setElements(_bfs);
			}};
			node.setBackForms(_bfBean);
			node.setElements(_set);
		}
		
	}
	
	public void setBackFormsRuls(BackFormEleBean backFormEleBean,List<RuleBean> rulesList,Map<String,String> jsonMap) {
		if (backFormEleBean != null && StringUtils.isNotEmpty(backFormEleBean.getEleId()) && rulesList != null) {
			if (backFormEleBean.isSeeAble()) {
				backFormEleBean.setSeeRules(rulesList.stream().filter(rule -> rule != null 
											&& StringUtils.isNotEmpty(rule.getOpType()) && "isKj".equalsIgnoreCase(rule.getOpType()) 
											&& StringUtils.isNotEmpty(rule.getOrtherId()) && StringUtils.isNotEmpty(backFormEleBean.getEleId())
											&& rule.getOrtherId().equalsIgnoreCase(backFormEleBean.getEleId())).collect(Collectors.toList() ));
				if (backFormEleBean.getSeeRules() != null && backFormEleBean.getSeeRules().size() >0) {
					List<RuleBean> actions = backFormEleBean.getSeeRules().stream().filter(ruleBean -> ruleBean != null && StringUtils.isNotEmpty(ruleBean.getParaThree()) 
							&& IsNumber.isNumeric(ruleBean.getParaThree()) && StringUtils.isNotEmpty(ruleBean.getType()) && ruleBean.getType().toLowerCase().equals("action")).collect(Collectors.toList());
					if (actions != null && actions.size()>0) {
						actions.sort((o1,o2) -> Integer.valueOf(o1.getParaThree()) -Integer.valueOf(o2.getParaThree()));
						boolean _checked = false;
						for (RuleBean ruleBean :actions) {
							_checked = helpUtilService.parseToExpression(ruleBean,backFormEleBean.getSeeRules(),jsonMap);
							if (_checked) {
								backFormEleBean.setSeeAble(Boolean.valueOf(ruleBean.getParaTwo()));
								break;
							}
						}
						if (!_checked)
							backFormEleBean.setSeeAble(_checked);
					}
				}
			}
			if (backFormEleBean.isMuseAble()) {
				backFormEleBean.setMuseRules(rulesList.stream().filter(rule -> rule != null 
											&& StringUtils.isNotEmpty(rule.getOpType()) && "isBt".equalsIgnoreCase(rule.getOpType()) 
											&& StringUtils.isNotEmpty(rule.getOrtherId()) && StringUtils.isNotEmpty(backFormEleBean.getEleId())
											&& rule.getOrtherId().equalsIgnoreCase(backFormEleBean.getEleId())).collect(Collectors.toList() ));
				if (backFormEleBean.getMuseRules() != null && backFormEleBean.getMuseRules().size() >0) {
					List<RuleBean> actions = backFormEleBean.getMuseRules().stream().filter(ruleBean -> ruleBean != null && StringUtils.isNotEmpty(ruleBean.getParaThree()) 
							&& IsNumber.isNumeric(ruleBean.getParaThree()) && StringUtils.isNotEmpty(ruleBean.getType()) && ruleBean.getType().toLowerCase().equals("action")).collect(Collectors.toList());
					if (actions != null && actions.size()>0) {
						actions.sort((o1,o2) -> Integer.valueOf(o1.getParaThree()) -Integer.valueOf(o2.getParaThree()));
						for (RuleBean ruleBean :actions) {
							boolean _checked = helpUtilService.parseToExpression(ruleBean,backFormEleBean.getMuseRules(),jsonMap);
							if (_checked) {
								backFormEleBean.setMuseAble(Boolean.valueOf(ruleBean.getParaTwo()));
							}
						}
					}
				}
			}
		}
		
	}
	
	@Autowired
	FlowEngineDriverService flowEngineDriverService;

	@Override
	public Map<String, Object> toNextStep(String staffno, String workCaseId,
			String flowId, String nodeId,String lineId, String serviceId,
			String userGroupId, String paramJson) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		try {
			List<NodeBean> _nodes = flowEngineDataService.getNodesByFlowId(flowId);
			List<ActBean> _lines = flowEngineDataService.getActsByFlowId(flowId);
			FlowInitBean _init = flowEngineDataService.getWorkFlowInitByFormId(workCaseId);
			int _flag = getCaseByWCaseAndFlowId(workCaseId,flowId,_init);
			String _nodeId = (_flag == 3 ? _init.getNodeId() : null);
			NodeBean currentNode = getCurrentNode(_nodeId,_nodes);
			if ( currentNode != null ) {
				currentNode.setDGroupId(_init != null ? _init.getGroupId() : null);
				Map<String,String> jsonMap = helpUtilService.getJsonStringToLevelMap(paramJson);
				jsonMap = flowEngineDataService.addOtherValue(workCaseId,serviceId,jsonMap);
				getCurrentNodeToActs(workCaseId,serviceId,
						jsonMap,flowId,currentNode,_lines,_nodes);
				setLimitDt(currentNode,_nodes,flowId,jsonMap);
				//校验及驱动
				result = flowEngineDriverService.checkAndDriverToNext(_flag,_init,currentNode,
						staffno,userGroupId,flowId,nodeId,lineId,workCaseId,
						serviceId,paramJson);
				NodeBean nextNode = getCurrentNode(nodeId,_nodes);
				if (nextNode != null) {
					setLimitDt(nextNode,_nodes,flowId,jsonMap);
				}
				result.put("limitDt", nextNode.getLimitDt());
				result.put("stepLimitDt", nextNode.getStepLimitDt());
				try {
					String[] _string = flowEngineDataService.getWBExtend(flowId, nextNode.getId(),userGroupId);
					if (_string != null && _string.length ==2 && StringUtils.isNotEmpty(_string[0]) 
							&& StringUtils.isNotEmpty(_string[1]) ) {
						WbExtendDao d = BeanFactory.getBean(WbExtendDao.class);
						d.send(_string[0],_string[1],workCaseId,serviceId);
					}
				} catch (Exception e) {
					log.error("执行工作流执行外部接口出错！flowId:"+flowId+",工单实例ID:"+workCaseId);
				}
			}else {
				result.put("flag", false);
				result.put("msg", "实例表中的节点ID不存在!");
			}
		} catch (Exception e) {
			log.error("执行工作流路径出错！flowId:"+flowId+",工单实例ID:"+workCaseId);
			result.put("flag", false);
			result.put("msg", "执行工作流路径出错！flowId:"+flowId+",工单实例ID:"+workCaseId);
		}

		return result;
	}

	@Override
	public Map<String, Object> getFlowNodeGroup(String staffno, String workCaseId, String flowId, String nodeId,
			String serviceId, String paramJson) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		try {
			List<NodeBean> _nodes = flowEngineDataService.getNodesByFlowId(flowId);
			NodeBean currentNode = null;
			if (_nodes != null) {
				List<NodeBean> _findNodes = _nodes.stream().filter(nodeBean -> nodeId.equalsIgnoreCase(nodeBean.getId())).collect(Collectors.toList());
				currentNode = _findNodes != null && _findNodes.size() >0 ?_findNodes.get(0):null;
				if (currentNode != null) {
					Map<String,String> jsonMap = helpUtilService.getJsonStringToLevelMap(paramJson);
					jsonMap = flowEngineDataService.addOtherValue(workCaseId,serviceId,jsonMap);
					setNodeGroupIds(workCaseId,serviceId,jsonMap,flowId,currentNode,currentNode);
					result.put("data", currentNode);
				}
			}
		} catch (Exception e) {
			log.error("获取工作流指定节点出错！flowId:"+flowId+",工单实例ID:"+workCaseId);
			result.put("flag", false);
			result.put("msg", "执行工作流路径出错！flowId:"+flowId+",工单实例ID:"+workCaseId);
		}

		return result;
	}
	
	@Override
	public Map<String, Object> setBackOption(String staffno, String workCaseId,int step) {
		Map<String, Object> result = new LinkedHashMap<String, Object>() ;
		if (StringUtils.isNotEmpty(staffno) && StringUtils.isNotEmpty(workCaseId)) {
			String[] formIds = workCaseId.contains(",") ? workCaseId.split(",") : new String[] {workCaseId};
			for (String _formId : formIds) {
				result.put(_formId, flowEngineDriverService.setStepToHistory(staffno,_formId,step));
			}
		}
		return result;
	}

}
