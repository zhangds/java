/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.hb.kfcenter.kfCore.models.JdbcTemplate;
import com.hb.kfcenter.kfFlow.engine.bean.ActBean;
import com.hb.kfcenter.kfFlow.engine.bean.BackFormEleBean;
import com.hb.kfcenter.kfFlow.engine.bean.FlowInitBean;
import com.hb.kfcenter.kfFlow.engine.bean.GroupBean;
import com.hb.kfcenter.kfFlow.engine.bean.NodeBean;
import com.hb.kfcenter.kfFlow.engine.bean.RuleBean;
import com.hb.kfcenter.kfFlow.engine.service.FlowEngineDataService;

/**
 * @author zhangds
 *
 */
@Component
public class FlowEngineDataDao implements FlowEngineDataService {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private class RuleBeanRowMapper implements RowMapper<RuleBean>{

		@Override
		public RuleBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new RuleBean() {{
				setId(rs.getString("RULEID"));
				setPId(rs.getString("RULEPID"));
				String _type = rs.getString("RULETYPE");
				if (StringUtils.isNotEmpty(_type)) {
					_type = _type.replace("icon", "").toLowerCase();
				}
				setType(_type);
				setParaOne(rs.getString("PARA1"));
				setParaTwo(rs.getString("PARA2"));
				setParaThree(rs.getString("PARA3"));
				setOrtherId(rs.getString("ORTHER_ID"));
				setOpType(rs.getString("OPTYPE"));
			}};
		}
		
	}
	
	private final static String SELECT_NODERULESBYFLOWIDANDTYPEID_SQL = "SELECT RULEID ,RULEPID ,RULETYPE ,PARA1 ,PARA2 ,PARA3,ORTHER_ID,OPTYPE FROM T_WORKFLOW_RULE_NEW WHERE wk_id=? AND NODE_ID=? AND OPTYPE=? ORDER BY RULEID ASC";
	private final static String SELECT_ACTRULESBYFLOWIDANDTYPEID_SQL = "SELECT RULEID ,RULEPID ,RULETYPE ,PARA1 ,PARA2 ,PARA3,ORTHER_ID,OPTYPE FROM T_WORKFLOW_RULE_NEW WHERE wk_id=? AND ORTHER_ID =? AND OPTYPE=? ORDER BY RULEID ASC";
	public List<RuleBean> getRulesById(String flowId,String nodeId,String type){
		//ruleNode,ruleLine
		String _sql = SELECT_NODERULESBYFLOWIDANDTYPEID_SQL;
		if (StringUtils.isNotEmpty(type) && "ruleLine".equalsIgnoreCase(type)) {
			_sql = SELECT_ACTRULESBYFLOWIDANDTYPEID_SQL;
		}
		return jdbcTemplate.query(_sql, new Object[] {flowId,nodeId,type},
				new RuleBeanRowMapper());
	}
	
	private final static String SELECT_NODESBYFLOWID_SQL = "select NODE_ID as id,NODE_NAME as name,NODE_TYPE as type from T_WORKFLOW_NODES where wk_id=?";
	
	@Override
	public List<NodeBean> getNodesByFlowId(String flowId) {
		if (StringUtils.isNotEmpty(flowId)) {
			return jdbcTemplate.query(SELECT_NODESBYFLOWID_SQL,new Object[] {flowId},
					new RowMapper<NodeBean>() {

						@Override
						public NodeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new NodeBean() {{
								setId(rs.getString("ID"));
								setName(rs.getString("NAME"));
								setType(rs.getString("TYPE"));
								setRuleList(getRulesById(flowId,rs.getString("ID"),"ruleNode"));
							}};
						}
				
			});
		}
		return null;
	}

	private static final String SELECT_ACTBYFLOWID_SQL = "select ACT_ID as id,ACT_NAME as name,PRE_NODE_ID as pre,NEXT_NODE_ID as next from T_WORKFLOW_ACT where wk_id=?";
	@Override
	public List<ActBean> getActsByFlowId(String flowId) {
		if (StringUtils.isNotEmpty(flowId)) {
			return jdbcTemplate.query(SELECT_ACTBYFLOWID_SQL, new Object[] {flowId},
					new RowMapper<ActBean>() {

						@Override
						public ActBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new ActBean() {{
								setId(rs.getString("ID"));
								setName(rs.getString("NAME"));
								setFromNode(rs.getString("PRE"));
								setNextNodeId(rs.getString("NEXT"));
								setRuleList(getRulesById(flowId,rs.getString("ID"),"ruleLine"));
							}};
						}});
		}
		return null;
	}
	
	private static final String SELECT_FLOWINITBYFORMID_SQL = "SELECT INIT_ID as ID,WK_ID as WKID,STEP_ID as STEPID,SERVICE_ID as SERVICEID,GROUP_ID as GROUPID FROM T_WORKFLOW_INIT WHERE FORM_ID=?";// AND STATES !=0";
	@Override
	public FlowInitBean getWorkFlowInitByFormId(String formId) {
		return jdbcTemplate.query(SELECT_FLOWINITBYFORMID_SQL,
				new Object[] {formId}, new ResultSetExtractor<FlowInitBean>() {

					@Override
					public FlowInitBean extractData(ResultSet rs) throws SQLException, DataAccessException {
						FlowInitBean _bean = null;
						while (rs.next()) { 
							_bean = new FlowInitBean() {{
								setInitId(rs.getString("ID"));
								setFlowId(rs.getString("WKID"));
								setNodeId(rs.getString("STEPID"));
								setServiceId(rs.getString("SERVICEID"));
								setGroupId(rs.getString("GROUPID"));
							}};
							break;
						}
						return _bean;
					}
			
		});
	}
	
	private final static String SELECT_WKGROUPSBYWKIDANDNODEID_SQL = "SELECT a.WK_ID,a.NODE_ID,a.GROUP_ID,b.GROUP_NAME,b.CITY_ID,a.STATES FROM T_WORKFLOW_WORKGROUPSET_NEW a,T_WORKFLOW_WORKGROUP b WHERE a.GROUP_ID = b.GROUP_ID AND a.WK_ID=? AND a.NODE_ID=? AND STATES='00A'";
	@Override
	public List<GroupBean> getCurrentNodeWKGroupsByFlowIdAndNodeId(String flowId, String nodeId) {
		return jdbcTemplate.query(SELECT_WKGROUPSBYWKIDANDNODEID_SQL, new Object[] {flowId,nodeId},
				new RowMapper<GroupBean>() {

					@Override
					public GroupBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new GroupBean() {{
							String _groupId = rs.getString("GROUP_ID");
							setId(_groupId);
							setName(rs.getString("GROUP_NAME"));
							setExtendList(getExtendListByGroupId(_groupId));
							setCityId(rs.getString("CITY_ID"));
						}};
					}
			
		});
	}
	
	private final static String SELECT_WKGROUPEXTENDSBYGROUPID_SQL = "select DISTINCT ATTR_ID ,ATTR_VALUE ,STATIC_NAME from T_GROUP_ATTR a,T_STATIC_DATA b WHERE a.GROUP_ID =? AND a.ATTR_ID = b.STATIC_CODE AND b.TABLE_NAME ='SYSTEM' AND b.COL_NAME='工作组属性' AND b.STATUS=1";
	private List<String[]> getExtendListByGroupId(String groupId){
		return jdbcTemplate.query(SELECT_WKGROUPEXTENDSBYGROUPID_SQL, new Object[] {groupId},
				new RowMapper<String[]>() {

					@Override
					public String[] mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new String[]{ rs.getString("ATTR_ID"),rs.getString("STATIC_NAME"),rs.getString("ATTR_VALUE")};
					}
					
				});
	}
	
	private final static String SELECT_CURRENTNODEBYTYPE_SQL = "SELECT RULEID,RULEPID,RULETYPE,PARA1,PARA2,PARA3,ORTHER_ID,OPTYPE FROM T_WORKFLOW_RULE_NEW WHERE WK_ID=? AND NODE_ID=? AND OPTYPE=? order by PARA3 asc";
	@Override
	public List<RuleBean> getCurrentNodeRulesByType(String flowId, String nodeId,String optype) {
		return jdbcTemplate.query(SELECT_CURRENTNODEBYTYPE_SQL, new Object[] {flowId,nodeId,optype},
				new RuleBeanRowMapper());
	}
	
	private static final String SELECT_SERVICEPARAM_SQL = "select SRTYPE_ID,NAME from T_FLOW_SERVICEREQUESTTYPE where STATUS='01' and SRTYPE_ID=?";
	private Map<String,String> getServicesParam(String serviceId) {
		if (StringUtils.isNotEmpty(serviceId)) {
			try {
				return jdbcTemplate.query(SELECT_SERVICEPARAM_SQL, new Object[] {serviceId},
						new ResultSetExtractor<Map<String,String>>() {

							@Override
							public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
								Map<String,String> resultMap = new LinkedHashMap<String, String>(2);
								while (rs.next()) { 
									resultMap.put("services.SRTYPE_ID", rs.getString("SRTYPE_ID"));
									resultMap.put("services.NAME", rs.getString("NAME"));
								}
								return resultMap;
							}
					
				});
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	private static final String SELECT_PRENODEGROUP_SQL = "select * from ( select STEPID,USERID,(select NODE_NAME from T_WORKFLOW_NODES where NODE_ID=a.STEPID and WK_ID in (select WK_ID from T_WORKFLOW_INIT where FORM_ID=a.FORMID)) node_name,GROUPID,(select group_name from T_WORKFLOW_WORKGROUP c where c.GROUP_ID=a.GROUPID) group_name,(select CITY_ID from T_WORKFLOW_WORKGROUP c where c.GROUP_ID=a.GROUPID) group_city from T_WORKFLOW_NODE_STEPLOG a where a.FORMID=? order by CREATE_DT desc) b where ROWNUM<=2";
	
	private Map<String,String> getHistoryNodeParam(String workCaseId) {
		if (StringUtils.isNotEmpty(workCaseId)) {
			try {
				return jdbcTemplate.query(SELECT_PRENODEGROUP_SQL,new Object[] {workCaseId},
						new ResultSetExtractor<Map<String,String>>(){

							@Override
							public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
								Map<String,String> resultMap = new LinkedHashMap<String, String>(5);
								int count = 1;
								while (rs.next()) {
									if (count == 1) {
										count ++;
									}else {
										resultMap.put("preNode.NODE_ID", rs.getString("STEPID"));
										resultMap.put("preNode.NODE_NAME", rs.getString("NODE_NAME"));
										resultMap.put("preNode.GROUP_ID", rs.getString("GROUPID"));
										resultMap.put("preNode.GROUP_NAME", rs.getString("GROUP_NAME"));
										resultMap.put("preNode.GROUP_CITY", rs.getString("GROUP_CITY"));
										resultMap.put("preNode.USERID", rs.getString("USERID"));
									}
								}
								return resultMap;
							}
					
				});
			} catch (Exception e) {
			}
		}
		return null;
	}
	@Override
	public Map<String, String> addOtherValue(String workCaseId,String serviceId,Map<String,String>jsonMap) {
		if (jsonMap ==null ||jsonMap.size()==0)
			jsonMap = new LinkedHashMap<String, String>(5);
		Map<String, String> _temp = getServicesParam(serviceId);
		if (_temp != null && _temp.size()>0)
			jsonMap.putAll(_temp);
		_temp = getHistoryNodeParam(workCaseId);
		if (_temp != null && _temp.size()>0)
			jsonMap.putAll(_temp);
		jsonMap.put("systems.TRUE", "true");
		return jsonMap;
	}
	
	private static final String SELECT_BACKFORMSET_SQL = "select OPTYPE,BACKFORM_ID,ELE_ID,ELE_NAME,SEE_ABLE,MUST_ABLE  from T_WORKFLOW_BACKFORMS_RULE where WK_ID=? and NODE_ID=? and STATES='00A'";
	@Override
	public List<BackFormEleBean> getAllNodeBackFormSets(String flowId, String nodeId) {
		if (StringUtils.isNotEmpty(flowId) && StringUtils.isNotEmpty(nodeId))
			return jdbcTemplate.query(SELECT_BACKFORMSET_SQL,new Object[] {flowId,nodeId},
					new RowMapper<BackFormEleBean>() {

						@Override
						public BackFormEleBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new BackFormEleBean() {{
								setEleId(rs.getString("ELE_ID"));
								setEleName(rs.getString("ELE_NAME"));
								String _check = rs.getString("SEE_ABLE");
								setSeeAble("Y".equalsIgnoreCase(_check)?true:false);
								_check = rs.getString("MUST_ABLE");
								setMuseAble("Y".equalsIgnoreCase(_check)?true:false);
								setOpType(rs.getString("OPTYPE"));
								setBackFormId(rs.getString("BACKFORM_ID"));
							}};
						}
				
				});
		return null;
	}
	
	private static final String SELECT_NODEBACKFORMRULES_SQL = "select RULEID,RULEPID,RULETYPE,PARA1,PARA2,PARA3,ORTHER_ID,OPTYPE from T_WORKFLOW_RULE_NEW where WK_ID=? and NODE_ID=? and OPTYPE in ('isKj','isBt') order by ORTHER_ID,para3,RULEID asc";
	@Override
	public List<RuleBean> getAllNodeBackFormRules(String flowId, String nodeId) {
		if (StringUtils.isNotEmpty(flowId) && StringUtils.isNotEmpty(nodeId))
			return jdbcTemplate.query(SELECT_NODEBACKFORMRULES_SQL, new Object[] {flowId,nodeId},
					new RuleBeanRowMapper());
		return null;
	}
	
	private static final String SELECT_WBEXTEND_SQL = "select CLASSZ,MOTHOD,PD_WK_ID from T_WORKFLOW_WBEXTEND_SET where WK_ID=? AND NODE_ID=? and STATES='00A'";
	@Override
	public String[] getWBExtend(String flowId, String nodeId,String userGroupId) {
		return jdbcTemplate.query(SELECT_WBEXTEND_SQL,new Object[] {flowId,nodeId},
				new ResultSetExtractor<String[]>() {

					@Override
					public String[] extractData(ResultSet rs) throws SQLException, DataAccessException {
						String[] _string = new String[2];
						while (rs.next()) {
							String _pd = rs.getString("PD_WK_ID");
							if (StringUtils.isNotEmpty(_pd) && StringUtils.isNotEmpty(userGroupId)) {
								String[] _strs = _pd.split(";");
								List<String> list = Arrays.asList(_strs).stream().filter(oneGroup -> userGroupId.equalsIgnoreCase(oneGroup)).collect(Collectors.toList());
								if ("*".equals(_pd) || (list != null && list.size()>0)) {
									_string[0] = rs.getString("CLASSZ");
									_string[1] = rs.getString("MOTHOD");
								}
							}else {
								_string[0] = rs.getString("CLASSZ");
								_string[1] = rs.getString("MOTHOD");
							}
						}
						return _string;
					}
			
		});
	}
	
}
