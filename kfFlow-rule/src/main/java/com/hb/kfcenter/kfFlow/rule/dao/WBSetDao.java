/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.hb.kfcenter.kfCore.models.JdbcTemplate;
import com.hb.kfcenter.kfFlow.rule.bean.WbBean;
import com.hb.kfcenter.kfFlow.rule.service.WBSetService;

/**
 * @author zhangds
 *
 */
@Component
public class WBSetDao implements WBSetService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final String SELECT_ALLWBCONFIGS_SQL = "select a.SYS_ID,b.SYS_NAME,b.WF_AUTO_DEAL,a.ACTION_NAME,a.ACTION_METHOD from T_MONITOR_SYS_FLOW_ACTION a left join T_MONITOR_SYS_INFO b on a.SYS_ID= b.SYS_ID and b.IF_WF_SEL='Y' and b.SYS_STATE='00A' order by SYS_ID";
	@Override
	public List<WbBean> getWbExtendConfig() {
		return jdbcTemplate.query(SELECT_ALLWBCONFIGS_SQL, new RowMapper<WbBean>() {

			@Override
			public WbBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new WbBean() {{
					setSysId(rs.getString("SYS_ID"));
					setSysName(rs.getString("SYS_NAME"));
					setClassZ(rs.getString("WF_AUTO_DEAL"));
					setMothod(rs.getString("ACTION_METHOD"));
					setMothodName(rs.getString("ACTION_NAME"));
				}};
			}
				
		});
	}
	
	private static final String DEL_WBEXTEND_SQL = "delete from T_WORKFLOW_WBEXTEND_SET where WK_ID=? AND NODE_ID=?";
	private static final String INSERT_WBEXTEND_SQL = "insert into T_WORKFLOW_WBEXTEND_SET(wk_id, node_id, sysid, classz, mothod) values(?,?,?,?,?)";
	@Override
	public Map<String, Object> setCurrentNodeWbExtendConfig(String flowId, String nodeId, String sysId, String mothodId,
			String classZ) {
		Map<String, Object> map = new LinkedHashMap<String, Object>(2);
		try {
			jdbcTemplate.update(DEL_WBEXTEND_SQL, new Object[] {flowId,nodeId});
			jdbcTemplate.update(INSERT_WBEXTEND_SQL, new Object[] {flowId, nodeId, sysId, classZ,mothodId});
			map.put("flag", true);
		} catch (Exception e) {
			map.put("flag", false);
		}
		return map;
	}
	
	private static final String SELECT_CURRENTNODEWBEXTEND_SQL = "select SYSID,MOTHOD from T_WORKFLOW_WBEXTEND_SET where WK_ID=? AND NODE_ID=?";
	@Override
	public String[] getCurrentNodeWbExtend(String flowId, String nodeId) {
		return jdbcTemplate.query(SELECT_CURRENTNODEWBEXTEND_SQL,new Object[] {flowId,nodeId},
				new ResultSetExtractor<String[]>() {

					@Override
					public String[] extractData(ResultSet rs) throws SQLException, DataAccessException {
						String[] _string = new String[2];
						while (rs.next()) {
							_string[0] = rs.getString("SYSID");
							_string[1] = rs.getString("MOTHOD");
						}
						return _string;
					}
			
		});
	}

}
