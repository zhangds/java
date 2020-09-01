/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.kfcenter.kfCore.models.Configuration;
import com.hb.kfcenter.kfCore.models.JdbcTemplate;
import com.hb.kfcenter.kfFlow.rule.bean.BackFormBean;
import com.hb.kfcenter.kfFlow.rule.service.BackFormService;
import com.hb.kfcenter.kfFlow.rule.util.HttpUtil;

/**
 * @author zhangds
 *
 */
@Component
public class BackFormDao implements BackFormService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static String BACKFORM_LIST_URL = null;
	private static String BACKFORM_INFO_URL = null;
	
	@Override
	public List<String[]> getAllBackFormList() throws Exception{
		List<String[]> resultList = new ArrayList<String[]>(100);
		String json = "{currentPage:\"1\",pageSize:\"100\",pageMethod:\"F\",sysSource:\"1\",tplTypeId:\"2\"}";
		BACKFORM_LIST_URL = Configuration.getInstance().getProperty("com.hb.kfcenter.kfFlow.rule.backFormListUrl");
		if (StringUtils.isNotEmpty(BACKFORM_LIST_URL)) {
			String result = HttpUtil.sendHttpPost(BACKFORM_LIST_URL, json);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(result);
			node = node.get("dataList");
			if (node != null) {
				Iterator<JsonNode> iterator = node.elements();
				while (iterator.hasNext()) {
				    JsonNode _stock = iterator.next();
				    String _name=_stock.get("tpl_name").asText();// 取节点文本
				    String _key=_stock.get("tpl_id").asText();
				    resultList.add(new String[] {_key,_name});
				}
			}
		}
		return resultList;
	}
	
	public List<BackFormBean> getBackFormById(String keyId) throws Exception{
		List<BackFormBean> resultList = new ArrayList<BackFormBean>(100);
		StringBuffer _json = new StringBuffer("{sysSource:\"1\",tplTypeId:\"2\",tplId:");
		_json.append(keyId);
		_json.append("}");
		BACKFORM_INFO_URL = Configuration.getInstance().getProperty("com.hb.kfcenter.kfFlow.rule.backFormInfoUrl");
		if (StringUtils.isNotEmpty(BACKFORM_INFO_URL)) {
			String result = HttpUtil.sendHttpPost(BACKFORM_INFO_URL, _json.toString());
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(result);
			if (node != null) {
				Iterator<JsonNode> iterator = node.elements();
				while (iterator.hasNext()) {
					JsonNode _stock = iterator.next();
					String _uid = _stock.get("id").asText();
					String _txt = _stock.get("txt").asText();
					resultList.add(new BackFormBean(){{
						setId(_uid);setName(_txt);setType("backForm");setIsKj("Y");setIsBt("Y");
					}});
				}
			}
		}
		return resultList;
	}

	private List<BackFormBean> getBackFormRule(String json) throws Exception{
		List<BackFormBean> result = null;
		if (StringUtils.isNotEmpty(json)) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			if (node != null) {
				result = new ArrayList<BackFormBean>(20);
				Iterator<JsonNode> iterator = node.elements();
				while (iterator.hasNext()) {
					JsonNode _stock = iterator.next();
					result.add(new BackFormBean() {{
						setId(_stock.get("id").asText());
						setName(_stock.get("name").asText());
						setIsKj(_stock.get("isKj").asText());
						setIsBt(_stock.get("isBt").asText());
					}});
				}
			}
		}
		return result;
	}
	
	private static final String SELECT_BACKFORMS_SQL = "select count(1) as num from T_WORKFLOW_BACKFORMS_RULE WHERE WK_ID=? AND NODE_ID=? and BACKFORM_ID=? and ROWNUM=1";
	private static final String DELETE_BACKFORMS_SQL = "delete from T_WORKFLOW_BACKFORMS_RULE where WK_ID=? and NODE_ID=?";
	private static final String DELETE_BACKFORMSRULES_SQL = "delete from T_WORKFLOW_RULE_NEW where WK_ID=? and NODE_ID=? and OPTYPE in ('isBt','isKj')";
	private static final String INSERT_BACKFORMS_SQL = "insert into T_WORKFLOW_BACKFORMS_RULE(wk_id, node_id, optype, backform_id, ele_id,ele_name, see_able, must_able,UPDATER,creater) values(?,?,?,?,?,?,?,?,?,?)";
	@Override
	public Map<String, Object> saveBackFormsRule(String staffno, String flowId, String nodeId, String backFormId,
			String backFromJson, String zrdxJson) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		try {
			if (StringUtils.isNotEmpty(flowId) && StringUtils.isNotEmpty(nodeId) && StringUtils.isNotEmpty(backFormId)) {
				List<BackFormBean> _backFormList = getBackFormRule(backFromJson);
				List<BackFormBean> _zrdxList = getBackFormRule(zrdxJson);
				if (_backFormList != null && _backFormList.size()>0 &&
						_zrdxList != null && _zrdxList.size()>0) {
					int _count = jdbcTemplate.queryForObject(SELECT_BACKFORMS_SQL,new Object[] {flowId,nodeId,backFormId},Integer.class);
					jdbcTemplate.update(DELETE_BACKFORMS_SQL, new Object[] {flowId,nodeId});
					if (_count == 0)
						jdbcTemplate.update(DELETE_BACKFORMSRULES_SQL, new Object[] {flowId,nodeId});
					jdbcTemplate.batchUpdate(INSERT_BACKFORMS_SQL, new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							BackFormBean _bean = null;
							if (i<_backFormList.size()) {
								_bean = _backFormList.get(i);
							}else {
								int _count = i - _backFormList.size();
								_bean = _zrdxList.get(_count);
								_bean.setType("zrdx");
							}
							if (_bean != null) {
								ps.setString(1, flowId);
								ps.setString(2, nodeId);
								ps.setString(3, _bean.getType());
								ps.setString(4, backFormId);
								ps.setString(5, _bean.getId());
								ps.setString(6, _bean.getName());
								ps.setString(7, _bean.getIsKj());
								ps.setString(8, _bean.getIsBt());
								ps.setString(9, staffno);
								ps.setString(10, staffno);
							}
						}
						@Override
						public int getBatchSize() {
							return _backFormList.size()+_zrdxList.size();
						}
					});
					result.put("flag",true);
				}
			}
		} catch (Exception e) {
		}
		if (!result.containsKey("flag"))
			result.put("flag",false);
		return result;
	}
	
	private static final String SELECT_CURRENTNODEBACKFORMRULES_SQL = "select OPTYPE as type,BACKFORM_ID as bkId,ELE_ID as id,ele_name as name,SEE_ABLE as isKj,MUST_ABLE as isBt  from T_WORKFLOW_BACKFORMS_RULE where WK_ID=? and NODE_ID=?";
	public List<BackFormBean> getCurrentNodeBackFormList(String flowId,String nodeId){
		return jdbcTemplate.query(SELECT_CURRENTNODEBACKFORMRULES_SQL, new Object[] {flowId,nodeId},
				new RowMapper<BackFormBean>() {
					@Override
					public BackFormBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new BackFormBean() {{
							setId(rs.getString("ID"));
							setName(rs.getString("NAME"));
							setIsKj(rs.getString("ISKJ"));
							setIsBt(rs.getString("ISBT"));
							setType(rs.getString("TYPE"));
							setBkId(rs.getString("BKID"));
						}};
					}
		});
	}

}
