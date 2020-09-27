/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.hb.kfcenter.kfCore.models.JdbcTemplate;
import com.hb.kfcenter.kfFlow.rule.bean.WorkGropBean;
import com.hb.kfcenter.kfFlow.rule.service.FlowWorkGropService;
import java.util.Map;

/**
 * @author zhangds
 *
 */
@Component
public class FlowWorkGropDao implements FlowWorkGropService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final String SELECT_NOKEYWORDS_SQL = "select group_id as id, group_name as name, super_id as pid from T_workflow_workGROUP a where status='01' start with super_id=0 connect by prior group_id=super_id";
	private static final String SELECT_KEYWORDS_SQL = "select distinct group_id as id, group_name as name, super_id as pid from (select group_id, group_name, super_id from T_workflow_workGROUP a where status=''01'' start with group_id in ( select group_id from T_workflow_workGROUP c where c.group_name like ''%{0}%'') connect by prior super_id=group_id)";
	
	@Override
	public List<WorkGropBean> getWorkGroupBykeyWord(String keyWords) {
		String _sql = SELECT_NOKEYWORDS_SQL ;
		if ( !StringUtils.isEmpty(keyWords) ) {
			_sql = MessageFormat.format(SELECT_KEYWORDS_SQL, keyWords);
		}
		return jdbcTemplate.query(_sql, new ResultSetExtractor<List<WorkGropBean>>() {

			@Override
			public List<WorkGropBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<WorkGropBean> result = new ArrayList<WorkGropBean>(5000);
				while (rs.next()) {
					WorkGropBean _pojo = new WorkGropBean() {
						{
							setId(rs.getString("ID"));
							setPid(rs.getString("PID"));
							setName(rs.getString("NAME"));
						}
					};
					result.add(_pojo);
				}
				return (result.size() == 0 ? null : result);
			}
			
		});
	}

	private static final String CURRENT_NODETOGROUP_SQL = "SELECT a.WK_ID ,a.NODE_ID ,a.GROUP_ID,b.GROUP_NAME ,a.CREATER ,a.states FROM T_WORKFLOW_WORKGROUPSET_NEW a,T_WORKFLOW_WORKGROUP b WHERE a.GROUP_ID = b.GROUP_ID AND a.states = '00A' AND a.WK_ID =? AND a.NODE_ID =?";
	
	@Override
	public List<WorkGropBean> getCurrentFlowAndNodeIdByWrokGroups(String flowId, String nodeId) {
		if (StringUtils.isNotEmpty(flowId) &&
				StringUtils.isNotEmpty(nodeId)) {
			return jdbcTemplate.query(CURRENT_NODETOGROUP_SQL, new Object[] {flowId,nodeId}, 
					new ResultSetExtractor<List<WorkGropBean>>() {

						@Override
						public List<WorkGropBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
							List<WorkGropBean> _list = new ArrayList<WorkGropBean>(100);
							WorkGropBean _bean  = null;
							while (rs.next()) {
								_bean = new WorkGropBean() {{
									setId(rs.getString("GROUP_ID"));
									setName(rs.getString("GROUP_NAME"));
									setFlowId(flowId);
									setNodeId(nodeId);
									setCreater(rs.getString("CREATER"));
									setStates(rs.getString("STATES"));
								}};
								_list.add(_bean);
							}
							return (_list.size() == 0 ? null : _list);
						}
				
			});
		}
		return null;
	}

	private static final String INSERT_NODEWROKGROUPS = "INSERT INTO HBKF_ZHFW.T_WORKFLOW_WORKGROUPSET_NEW(WK_ID, NODE_ID, GROUP_ID, CREATER, CREATE_DATE, STATES) VALUES(?, ?, ?, ?, sysdate , '00A')";
	@Override
	public Map<String, String> saveCurrentNodeWrokGroups(String staffno,String flowId, String nodeId, String groupIds) {
		Map<String, String> map = new HashMap<String, String>(2);
		try {
			if (StringUtils.isNotEmpty(flowId) &&
					StringUtils.isNotEmpty(nodeId) &&
					StringUtils.isNotEmpty(groupIds)) {
				String[] _groupIds = groupIds.split(",");
				
				jdbcTemplate.batchUpdate(INSERT_NODEWROKGROUPS,new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, flowId);
						ps.setString(2, nodeId);
						ps.setString(3, _groupIds[i]);
						ps.setString(4, staffno);
					}

					@Override
					public int getBatchSize() {
						return _groupIds.length;
					}
					
				});
				
				map.put("flag", String.valueOf(true));
			}
		} catch (Exception e) {
			map.put("flag", String.valueOf(false));
		}
		return map;
	}
	@Override
	public Map<String, String> saveDeletetNodeWrokGroups(String staffno, String flowId, String nodeId,
			String groupIds) {
		Map<String, String> map = new HashMap<String,String>();
		String sql = "update HBKF_ZHFW.T_WORKFLOW_WORKGROUPSET_NEW set STATES = ''00X'' where WK_ID = ''{0}'' and  NODE_ID = ''{1}'' and  GROUP_ID in ({2})";
		try {
			int num =jdbcTemplate.update(MessageFormat.format(sql, flowId,nodeId,groupIds));
			if (num > 0) {
				map.put("flag", String.valueOf(true));
			}else{
				map.put("flag", String.valueOf(false));
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", String.valueOf(false));
		}
		return map;
	}

}
