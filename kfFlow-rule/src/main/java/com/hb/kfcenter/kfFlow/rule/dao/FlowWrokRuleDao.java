/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.hb.kfcenter.kfCore.models.JdbcTemplate;
import com.hb.kfcenter.kfFlow.rule.bean.LineBean;
import com.hb.kfcenter.kfFlow.rule.bean.ParamBean;
import com.hb.kfcenter.kfFlow.rule.bean.RuleBean;
import com.hb.kfcenter.kfFlow.rule.service.FlowWrokRuleService;

/**
 * @author zhangds
 *
 */
@Component
public class FlowWrokRuleDao implements FlowWrokRuleService{

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final String INSERT_RULE_SQL = "INSERT INTO T_WORKFLOW_RULE_NEW(WK_ID, NODE_ID, OPTYPE, CREATER, CREATE_DATE, RULEID, RULEPID, RULETYPE, ORTHER_ID) VALUES(?, ?, ?, ?, sysdate , ?, ?, ?, ? )";
	@Override
	public boolean saveRule(String staffno, String flowId,
			String nodeId, String ruleId,
			String rulePid,String lineId, String opType,
			String ruleType) {
		try {
			jdbcTemplate.update(INSERT_RULE_SQL,
					new Object[] {flowId, nodeId, opType, staffno 
							, ruleId, rulePid,  ruleType, lineId});
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private static final String SELECT_CURRENT_RULESBYNODEID_SQL = "SELECT RULEID,RULEPID,para1,para2,para3,RULETYPE FROM T_WORKFLOW_RULE_NEW WHERE WK_ID =? AND NODE_ID =? AND OPTYPE =? AND STATES ='00A'";
	private static final String SELECT_CURRENT_RULESBYLINEID_SQL = "SELECT RULEID,RULEPID,para1,para2,para3,RULETYPE FROM T_WORKFLOW_RULE_NEW WHERE WK_ID =? AND NODE_ID =? AND OPTYPE =? and ORTHER_ID=? AND STATES ='00A'";
	//@Override
	public List<RuleBean> getCurrentRules(String flowId, String nodeId,String lineId, String opType) {
		return jdbcTemplate.query(StringUtils.isEmpty(lineId)?SELECT_CURRENT_RULESBYNODEID_SQL:SELECT_CURRENT_RULESBYLINEID_SQL, 
				StringUtils.isEmpty(lineId)?new Object[] {flowId,nodeId,opType}:new Object[] {flowId,nodeId,opType,lineId},
				new RowMapper<RuleBean>() {

					@Override
					public RuleBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new RuleBean() {{
							setId(rs.getString("RULEID"));
							setPId(rs.getString("RULEPID"));
							setType(rs.getString("RULETYPE"));
							setParaOne(rs.getString("PARA1"));
							setParaTwo(rs.getString("PARA2"));
							setParaThree(rs.getString("PARA3"));
						}};
					}
			
		});
	}
	
	@Override
	public RuleBean getCurrentRule(String flowId, String nodeId,String lineId, String ruleId, String opType) {
		List<RuleBean> _list = getCurrentRules(flowId,nodeId,lineId,opType);
		RuleBean result = null;
		if (StringUtils.isNotEmpty(ruleId) &&
				_list != null && _list.size()>0) {
			for (RuleBean _bean : _list) {
				if (ruleId.equals(_bean.getId())) {
					result = _bean;
					break;
				}
			}
		}
		return result;
	}
	
	private static final String DEL_CURRENT_RULE_SQL = 
			"DELETE FROM T_WORKFLOW_RULE_NEW WHERE "
			+ "WK_ID=? AND NODE_ID =? AND OPTYPE =? "
			+ "AND RULEID IN (SELECT TO_NCHAR(?) AS RULEID "
			+ "FROM dual UNION ALL "
			+ "select RULEID from T_WORKFLOW_RULE_NEW a "
			+ "where states='00A' AND WK_ID=? AND NODE_ID =? "
			+ "AND OPTYPE ='ruleNode' start with RULEPID =? "
			+ "connect by prior RULEID =RULEPID)";
	@Override
	public boolean deleCurrentRule( String flowId, String nodeId, String ruleId, String opType) {
		
		try {
			jdbcTemplate.update(DEL_CURRENT_RULE_SQL, 
					new Object[] {flowId,nodeId,opType,ruleId,flowId,nodeId,ruleId});
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private static final String SELECT_BASEFORMS_SQL = "SELECT id ,name,COL_NAME FROM (SELECT DISTINCT to_char(ELE_ID) id, to_char(ELE_DESC) name,TO_CHAR(COL_NAME) COL_NAME, min(order_id) order_id FROM T_TB_BSC_TPL_DTL WHERE ELE_DESC LIKE ''%{0}%'' GROUP BY ele_id, ele_desc,COL_NAME ORDER BY order_id ASC) GROUP BY id ,name,COL_NAME";
	private List<ParamBean> getBaseFormsBykeyWorks(String form,String keyword){
		return jdbcTemplate.query(MessageFormat.format(SELECT_BASEFORMS_SQL,keyword),
				new RowMapper<ParamBean>() {

					@Override
					public ParamBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new ParamBean() {{
							setKeyId(rs.getString("ID"));
							setKeyValue(rs.getString("NAME"));
							setForms(form);
							setFieldName(rs.getString("COL_NAME"));
						}};
					}
			
		});
	}
	
	@SuppressWarnings("serial")
	@Override
	public List<ParamBean> getParamsByType(String flowId,String nodeId,
			String ruleId,String type,String keyword){
		List<ParamBean> result = null ;
		
		if (StringUtils.isNotEmpty(type)){
			if ("baseForms".equalsIgnoreCase(type)) {
				result = getBaseFormsBykeyWorks(type,keyword);
			}else if ("services".equalsIgnoreCase(type)) {
				result = new ArrayList<ParamBean>(2) {{
					add(new ParamBean() {{
						setKeyId("SRTYPE_ID");
						setKeyValue("服务请求ID");
						setForms(type);
						setFieldName("SRTYPE_ID");
					}});
					add(new ParamBean() {{
						setKeyId("NAME");
						setKeyValue("服务请求名");
						setForms(type);
						setFieldName("NAME");
					}});
				}};
			}else if ("preNode".equalsIgnoreCase(type)) {
				result = new ArrayList<ParamBean>(4) {{
					add(new ParamBean() {{
						setKeyId("NODE_ID");
						setKeyValue("节点ID");
						setForms(type);
						setFieldName("NODE_ID");
					}});
					add(new ParamBean() {{
						setKeyId("NODE_NAME");
						setKeyValue("节点名称");
						setForms(type);
						setFieldName("NODE_NAME");
					}});
					add(new ParamBean() {{
						setKeyId("GROUP_ID");
						setKeyValue("工作组ID");
						setForms(type);
						setFieldName("GROUP_ID");
					}});
					add(new ParamBean() {{
						setKeyId("GROUP_NAME");
						setKeyValue("工作组名");
						setForms(type);
						setFieldName("GROUP_NAME");
					}});
				}};
			}else if ("systems".equalsIgnoreCase(type)) {
				result = new ArrayList<ParamBean>(2) {{
					add(new ParamBean() {{
						setKeyId("TRUE");
						setKeyValue("变量真");
						setForms(type);
						setFieldName("TRUE");
					}});
				}};
			}
		}
		return result;
	}

	private static final String UPDATE_NODERULEPARAM_SQL="UPDATE T_WORKFLOW_RULE_NEW SET PARA1=?, PARA2=? ,PARA3=? WHERE WK_ID=? AND NODE_ID=? AND OPTYPE=? AND RULETYPE=? AND RULEID=?";
	@Override
	public boolean saveOneRuleParam(String flowId, String nodeId,
			String ruleId,String opType,String ruleType, String showParam,
			String realParam,String orderNo) {
		if ( StringUtils.isNotEmpty(flowId) &&
				StringUtils.isNotEmpty(nodeId) &&
				StringUtils.isNotEmpty(ruleId) &&
				StringUtils.isNotEmpty(ruleType) &&
				StringUtils.isNotEmpty(showParam) &&
				StringUtils.isNotEmpty(realParam) 
				) {
			jdbcTemplate.update(UPDATE_NODERULEPARAM_SQL,
					new Object[] {showParam,realParam,orderNo,flowId,nodeId,opType,
							ruleType,ruleId});
			return true;
		}
		return false;
	}

	private static final String SELECT_LINEACTRULE_SQL="SELECT a.ACT_ID,a.ACT_NAME,(SELECT NODE_NAME FROM T_WORKFLOW_NODES b WHERE b.NODE_ID=a.PRE_NODE_ID AND b.WK_ID =a.WK_ID ) formNode,(SELECT NODE_NAME FROM T_WORKFLOW_NODES c WHERE c.NODE_ID=a.NEXT_NODE_ID AND c.WK_ID =a.WK_ID ) nextNode,(SELECT decode(count(1),0,'N','Y')  FROM T_WORKFLOW_RULE_NEW d WHERE d.WK_ID =a.WK_ID AND d.NODE_ID =a.PRE_NODE_ID AND d.OPTYPE='actLine' AND ORTHER_ID = a.ACT_ID AND rownum <=1) isSet FROM T_WORKFLOW_ACT a WHERE a.WK_ID =? AND a.PRE_NODE_ID=?";
	@Override
	public List<LineBean> getCurrentNodeToLineAct(String flowId, String nodeId) {
		return jdbcTemplate.query(SELECT_LINEACTRULE_SQL, new Object[] {flowId,nodeId},
				new RowMapper<LineBean>() {

					@Override
					public LineBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new LineBean() {{
							setLineId(rs.getString("ACT_ID"));
							setLineName(rs.getString("ACT_NAME"));
							setFormNodeName(rs.getString("FORMNODE"));
							setToNodeName(rs.getString("NEXTNODE"));
							if ("Y".equals(rs.getString("ISSET")) ) {
								setSetFlag(true);
							}
						}};
					}
			
		});
		
	}

	@Deprecated
	public List<RuleBean> getCurrentRulesByEle(String flowId, String nodeId, String lineId, String opType) {
		List<RuleBean> result = null;
		if (StringUtils.isEmpty(lineId) && StringUtils.isNotEmpty(opType)) {
			result = this.getCurrentRules(flowId, nodeId,null, opType);
		}else if (StringUtils.isNotEmpty(lineId) && StringUtils.isNotEmpty(opType)
				&& !"ruleLine".equalsIgnoreCase(opType)) {
			result = this.getCurrentRules(flowId, nodeId, lineId, opType);
		}
		return result;
	}

	private final static String SELECT_TIMELIMIT_ISSET_SQL = "select a.wk_id,a.node_id,a.optype,(SELECT NODE_NAME FROM T_WORKFLOW_NODES b WHERE b.NODE_ID=a.NODE_ID AND b.WK_ID =a.WK_ID ) Nodename,(SELECT decode(count(1),0,'N','Y') FROM T_WORKFLOW_RULE_NEW d WHERE d.WK_ID =a.WK_ID AND d.NODE_ID =a.NODE_ID AND d.OPTYPE=a.optype AND rownum <=1) isSet from (select ? wk_id,? node_id,? optype from dual) a";
	@Override
	public String[] getTimeLimitList(String flowId, String nodeId) {
		String opType = "timeLimit";
		return jdbcTemplate.queryForObject(SELECT_TIMELIMIT_ISSET_SQL,new Object[] {flowId,nodeId,opType},
				new RowMapper<String[]>() {
					@Override
					public String[] mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new String[] {
								rs.getString("NODE_ID"),
								rs.getString("NODENAME"),
								rs.getString("OPTYPE"),
								rs.getString("ISSET")};
					}
				});
	}

}
