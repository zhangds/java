/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hb.kfcenter.kfCore.models.JdbcTemplate;
import com.hb.kfcenter.kfFlow.engine.bean.ActBean;
import com.hb.kfcenter.kfFlow.engine.bean.FlowInitBean;
import com.hb.kfcenter.kfFlow.engine.bean.GroupBean;
import com.hb.kfcenter.kfFlow.engine.bean.NodeBean;
import com.hb.kfcenter.kfFlow.engine.service.FlowEngineDriverService;
import com.hb.kfcenter.kfFlow.engine.util.SnowflakeIdWorker;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangds
 *
 */
@Component
@Slf4j
public class FlowEngineDriverDao implements FlowEngineDriverService {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	/**
	 * 	3:原流程驱动
	 *  2:重启新流程
	 *  1:新建流程实例
	 */
	public Object[] checked(int flag, NodeBean currentNode, String nodeId, String lineId,String userGroupId) {
		NodeBean nextNode = null;
		GroupBean nextGroup = null ;
		if ( currentNode != null ) {
			/*新建或者重启流程不做校验,直接初始化*/
			List<ActBean> actList = currentNode.getActList();
//			if ( flag == 1 || flag == 2 ) {
//				ActBean actBean = (actList != null && actList.size() > 0 && actList.get(0) != null ) ? actList.get(0) : null ;
//				lineId = actBean != null && StringUtils.isNotEmpty(actBean.getId())? actBean.getId() : null ;
//				nextNode = (actBean != null && actBean.getNextNode() != null ) ? actBean.getNextNode() : null ;
//				nodeId = (nextNode != null && StringUtils.isNotEmpty(nextNode.getId()))? nextNode.getId(): null;
//				List<GroupBean> groupList = nextNode != null && nextNode.getGroupList() != null ? nextNode.getGroupList() : null;
//				nextGroup = groupList != null && groupList.size()>0 && groupList.get(0) != null ? groupList.get(0) : null;
//			}else {
				if ( StringUtils.isNotEmpty(nodeId) && StringUtils.isNotEmpty(lineId) &&
				/* StringUtils.isNotEmpty(userGroupId) && */ actList != null ) {
					final String _tempLineId = lineId;
					List<ActBean> filteredActList = actList.stream().filter(actBean -> actBean!= null && _tempLineId.equalsIgnoreCase(actBean.getId()) ).collect(Collectors.toList());
					ActBean actBean = (filteredActList != null && filteredActList.size() > 0 && filteredActList.get(0) != null ) ? filteredActList.get(0) : null ;
					nextNode = (actBean != null && actBean.getNextNode() != null ) ? actBean.getNextNode() : null ;
					String _nodeId = (nextNode != null && StringUtils.isNotEmpty(nextNode.getId()))? nextNode.getId(): null;
					/*校验nodeId*/
					nodeId = _nodeId == null || !_nodeId.equalsIgnoreCase(nodeId) ? null : nodeId ;
					List<GroupBean> groupList = nextNode != null && nextNode.getGroupList() != null ? nextNode.getGroupList() : null;
					final String _tempUserGroupId = userGroupId;
					/*校验userGroupId*/
					groupList = groupList.stream().filter(groupBean -> groupBean!=null && _tempUserGroupId.equalsIgnoreCase(groupBean.getId()) ).collect(Collectors.toList());
					nextGroup = groupList != null && groupList.size()>0 && groupList.get(0) != null ? groupList.get(0) : null;
				}
//			}
		}
		return new Object[] {nextNode,nextGroup};
	}
	@Override
	public Map<String, Object> checkAndDriverToNext(int flag,FlowInitBean flowInit, NodeBean currentNode, String staffno, String userGroupId,
			String flowId, String nodeId, String lineId, String workCaseId, String serviceId, String paramJson) {
		boolean backFlag = false ;
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Object[] _resultObject = checked( flag, currentNode, nodeId, lineId, userGroupId);
			NodeBean nextNode = _resultObject != null && _resultObject.length ==2 ?(NodeBean)_resultObject[0]: null;
			GroupBean nextGroup =  _resultObject != null && _resultObject.length ==2 ?(GroupBean)_resultObject[1]: null;
			if ( nextNode != null ) {
				String _states = "1";
				if ( StringUtils.isNotEmpty(nextNode.getType()) && 
						nextNode.getType().contains("end")) { /* 增加结尾处理 */
					nextGroup = new GroupBean() {{
						setId("");
						setName("");
					}};
					_states = "0";
				}
				if ( nextGroup != null ) {
					backFlag = insertDriverTable(_states,flag,flowId,flowInit,staffno,workCaseId,serviceId,nextNode,nextGroup);
					map.put("nodeId", nextNode!=null && StringUtils.isNotEmpty(nextNode.getId())?nextNode.getId():"");
					//map.put("limitDt", 4);
				}
			}
			
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			backFlag = false ;
		}
		map.put("flag",backFlag);
		return map;
	}
	
	private final static String INSERT_WKINIT_SQL = "INSERT INTO T_WORKFLOW_INIT(INIT_ID, WK_ID, FORM_ID, STEP_ID, SERVICE_ID,GROUP_ID, STATES, STATES_DT ) VALUES(?, ?, ?, ?, ?,?, ?, sysdate) ";
	private final static String UPDATE_WKINIT_SQL = "UPDATE T_WORKFLOW_INIT SET WK_ID =? , STEP_ID =? , SERVICE_ID =?, GROUP_ID =? ,STATES =? , STATES_DT = sysdate WHERE FORM_ID =?";
	private final static String INSERT_WKSTEP_SQL = "INSERT INTO T_WORKFLOW_NODE_STEPLOG(INIT_ID, FORMID, STEPID, USERID, STATE, TAG, GROUPID) VALUES(?, ?, ?, ? , ?, ?, ?)";
	/**
	 * 	3:原流程驱动
	 *  2:重启新流程
	 *  1:新建流程实例
	 */
	@Transactional
	private boolean insertDriverTable(String state,int flag,String flowId,FlowInitBean flowInit,String staffno,
			String workCaseId, String serviceId,NodeBean nextNode,GroupBean nextGroup) {
		int count = -1 ;
		String _flowInit = flowInit != null && StringUtils.isNotEmpty(flowInit.getInitId()) ? flowInit.getInitId() : null;
		if (flag == 1) {
			if ( nextNode != null && nextGroup != null ) {
				_flowInit = String.valueOf(SnowflakeIdWorker.generateId());
				count = jdbcTemplate.update(INSERT_WKINIT_SQL,new Object[] {
						_flowInit,flowId,workCaseId,nextNode.getId(),serviceId,
						nextGroup.getId(),state
				});
			}
		}else {
			count = jdbcTemplate.update(UPDATE_WKINIT_SQL,new Object[] {
					flowId,	nextNode.getId(),serviceId,
					nextGroup != null && StringUtils.isNotBlank(nextGroup.getId())?nextGroup.getId():" ",
					state,workCaseId
			});
		}
		if (count >=0) {
			/* 插入步骤日志表 */
			/* state=="0" 结束工单 */
			/** flag
			 * 	3:原流程驱动
			 *  2:重启新流程
			 *  1:新建流程实例
			 *  _flowInit:流程实例id
			 *  workCaseId:工单id
			 *  nextNode.getId():当前节点id
			 *  staffno当前操作人员id
			 *  INIT_ID, FORMID, STEPID, USERID, STATE, TAG
			 */
			String tag = "原流程";
			if (flag == 1) {
				tag = "新建";
			}else if (flag == 2) {
				tag = "改流程";
			}
			jdbcTemplate.update(INSERT_WKSTEP_SQL,new Object[] {
					_flowInit,workCaseId,nextNode.getId(),
					staffno,StringUtils.isNotEmpty(state)&& "0".equals(state) ? "完结" : "活动",
					tag,nextGroup != null && StringUtils.isNotBlank(nextGroup.getId())?nextGroup.getId():" "
			});
			return true;
		}
		return false;
	}
	
	private final static String SELECT_HISTORY_SQL = "select * from (select STEPID,USERID, GROUPID,STATE,TAG from T_WORKFLOW_NODE_STEPLOG a where a.FORMID=? order by CREATE_DT desc) b where ROWNUM<=?";
	private Map<String,String> getHistory(String workCaseId,int step) {
		return jdbcTemplate.query(SELECT_HISTORY_SQL,new Object[] {workCaseId,step+1},
				new ResultSetExtractor<Map<String,String>>(){
					@Override
					public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
						int _count =0;
						Map<String, String> map = new HashMap<String, String>(3);
						while (rs.next()) {
							_count ++;
							if (_count == step+1) {
								String _state = rs.getString("STATE");
								String _tag = rs.getString("TAG");
								if ("活动".equalsIgnoreCase(_state) && StringUtils.isNotEmpty(_tag) && (_tag.contains("原") || _tag.contains("新"))) {
									map.put("STEPID", rs.getString("STEPID"));
									map.put("USERID", rs.getString("USERID"));
									map.put("GROUPID", rs.getString("GROUPID"));
								}
							}
						}
						return map;
					}
		});
	}
	
	private final static String UPDATE_FLINIT_SQL = "update T_WORKFLOW_INIT set STEP_ID=?,GROUP_ID=?,USER_ID=?,STATES_DT=sysdate where FORM_ID =?";
	private final static String INSERT_STEPTOHIS_SQL = "INSERT INTO T_WORKFLOW_NODE_STEPLOG(INIT_ID, FORMID, STEPID, USERID, STATE, TAG, GROUPID) VALUES((select distinct init_id from T_WORKFLOW_NODE_STEPLOG where formid=?),?,?, ?, '活动' , '原流程', ?)";
	public boolean setStepToHistory(String staffno, String workCaseId,int step) {
		boolean flag = false;
		try {
			if (StringUtils.isNotEmpty(staffno) && StringUtils.isNotEmpty(workCaseId) && step >0) {
				Map<String,String> map = getHistory(workCaseId,step);
				if (map != null && map.containsKey("USERID") && staffno.equals(map.get("USERID")) &&
						map.containsKey("STEPID") && StringUtils.isNotEmpty(map.get("STEPID")) &&
						map.containsKey("GROUPID") && StringUtils.isNotEmpty(map.get("GROUPID")) ) {
					jdbcTemplate.update(UPDATE_FLINIT_SQL,new Object[] {map.get("STEPID"),map.get("GROUPID"),staffno,workCaseId});
					jdbcTemplate.update(INSERT_STEPTOHIS_SQL,new Object[] {workCaseId,workCaseId,map.get("STEPID"),staffno,map.get("GROUPID")});
					flag = true;
				}
			}
		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return flag;
	}
	
}
