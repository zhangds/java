/**
 * 
 */
package com.hb.kfcenter.kfFlow.design.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.kfcenter.kfCore.models.JdbcTemplate;
import com.hb.kfcenter.kfCore.util.TaskDate;
import com.hb.kfcenter.kfFlow.design.bean.AreaBean;
import com.hb.kfcenter.kfFlow.design.bean.LineBean;
import com.hb.kfcenter.kfFlow.design.bean.NodeBean;
import com.hb.kfcenter.kfFlow.design.bean.WorkFlowBean;
import com.hb.kfcenter.kfFlow.design.service.FlowManagerService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangds
 *
 */
@Component
@Slf4j
public class FlowManagerDao implements FlowManagerService{

	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("serial")
	private static Map<String,String> FlowStateMap = new LinkedHashMap<String, String>(){{
		put("A", "新增");
		put("U", "修改");
		put("D", "发布");
	}};

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static String GETFLOWLIST_SQL = "select wk_id, wk_name, wk_remark, creater_id, to_char(create_dt,''yyyy-MM-dd HH24:mi:ss'') create_dt," + 
			" curr_version,def_form,wk_state,updater_id " + 
			"from T_WORKFLOW where wk_id like ''%{0}%'' and wk_name like ''%{1}%'' and wk_remark like ''%{2}%'' order by create_dt desc";
	
	@Override
	public int getAllCounts(String staffno, String wkId, String wkName, String wkRemark) {
		return jdbcTemplate.queryForObject(jdbcTemplate.getSqlPageHelper().getCountSQL(MessageFormat.format(GETFLOWLIST_SQL,wkId,wkName,wkRemark)),
				Integer.class);
	}
	
	@Override
	public List<WorkFlowBean> getCurrentPageDatas(int currentNum,int pageSize, String wkId, String wkName,String staffno,String wkRemark){
		String _sql = jdbcTemplate.getSqlPageHelper().getLimitSQL(MessageFormat.format(GETFLOWLIST_SQL,wkId,wkName,wkRemark), pageSize, currentNum,
				"create_dt desc");
		if (log.isDebugEnabled())
			log.info(_sql);
		return jdbcTemplate.query(_sql, new ResultSetExtractor<List<WorkFlowBean>>() {

			@Override
			public List<WorkFlowBean> extractData(ResultSet rs) throws SQLException, DataAccessException {

				List<WorkFlowBean> _list = new ArrayList<WorkFlowBean>();
				while (rs.next()) {
					WorkFlowBean _pojo = new WorkFlowBean() {
						{
							setWkId(rs.getString("WK_ID"));
							setWkName(rs.getString("WK_NAME"));
							setWkRemark(rs.getString("WK_REMARK"));
							setCreaterId(rs.getString("CREATER_ID"));
							setCreateDt(rs.getString("CREATE_DT"));
							setCurrVersion(rs.getString("CURR_VERSION"));
							setDefForm(rs.getString("DEF_FORM"));
							if (FlowStateMap.containsKey(rs.getString("WK_STATE"))) {
								setWkState(FlowStateMap.get(rs.getString("WK_STATE")));
							}else {
								setWkState(rs.getString("WK_STATE"));
							}
							setUpdaterId(rs.getString("UPDATER_ID"));
						}
					};
					_list.add(_pojo);
				}
				return (_list.size() == 0 ? null : _list);
			
			}
			
		});
	}

	private static String GET_FLOWBYFLOWID_SQL = "select wk_id, wk_name, wk_remark, creater_id, to_char(create_dt,'yyyy-MM-dd HH24:mi:ss') create_dt, curr_version,def_form,wk_state,updater_id from T_WORKFLOW where wk_id=?";
	@Override
	public WorkFlowBean getCurrentWorkFlowInfo(String flowId) {
		return jdbcTemplate.query(GET_FLOWBYFLOWID_SQL, new Object[] { flowId }, new ResultSetExtractor<WorkFlowBean>() {

			@Override
			public WorkFlowBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				WorkFlowBean _dao = null;
				while (rs.next()) {
					_dao = new WorkFlowBean() {
						{
							setWkId(rs.getString("WK_ID"));
							setWkName(rs.getString("WK_NAME"));
							setWkRemark(rs.getString("WK_REMARK"));
							setCreaterId(rs.getString("CREATER_ID"));
							setCreateDt(rs.getString("CREATE_DT"));
							setCurrVersion(rs.getString("CURR_VERSION"));
							setDefForm(rs.getString("DEF_FORM"));
							setWkState(rs.getString("WK_STATE"));
							setUpdaterId(rs.getString("UPDATER_ID"));
						}
					};
					break;
				}
				return _dao;
			}

		});
	}

	@Override
	public WorkFlowBean getDefaultNewWorkFlowInfo(String staffno) {
		return new WorkFlowBean() {
			{
				setWkId("");
				setWkName("");
				setWkRemark("");
				setCreaterId(staffno);
				setCreateDt(TaskDate.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				setCurrVersion("V1.0");
				setDefForm("");
				setWkState("A");
				setUpdaterId(staffno);
			}
		};
	}

	@Override
	public Map<String, String> getAllStates() {
		return FlowStateMap;
	}

	@Override
	public boolean saveWorkFlow(WorkFlowBean dao) {
		boolean flag = false;
		try {
			if (dao != null) {

				StringBuffer flow_sql = new StringBuffer();
				flow_sql.append("select count(1) from T_WORKFLOW where wk_id = ? ");
				int flow_count = jdbcTemplate.queryForObject(flow_sql.toString(), new Object[] { dao.getWkId() },
						Integer.class);
				if (flow_count > 0) {
					flag = false;
				} else {
					StringBuffer sql = new StringBuffer();
					sql.append(
							"insert into T_WORKFLOW(wk_id, wk_name, wk_remark, creater_id, create_dt, curr_version, def_form, wk_state, updater_id) values(?, ?, ?, ?, SYSDATE , ?, ?, ?, ?)");
					jdbcTemplate.update(sql.toString(),
							new Object[] { dao.getWkId(), dao.getWkName(), dao.getWkRemark(), dao.getCreaterId(),
									dao.getCurrVersion(), dao.getDefForm(), dao.getWkState(), dao.getUpdaterId() });
					flag = true;
				}

			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean updateWorkFlow(WorkFlowBean dao) {
		boolean flag = false;
		try {
			if (dao != null) {
				StringBuffer insertWorkSql = new StringBuffer();
				insertWorkSql.append("insert into T_WORKFLOW_HIS select * from T_WORKFLOW where wk_id=?");
				jdbcTemplate.update(insertWorkSql.toString(), new Object[] { dao.getWkId() });
				StringBuffer updateWorkSql = new StringBuffer();
				updateWorkSql.append(
						"update T_WORKFLOW set wk_name=?, wk_remark=?, create_dt=SYSDATE, curr_version=?, def_form=?, wk_state=?, updater_id=? where wk_id=?");
				jdbcTemplate.update(updateWorkSql.toString(), new Object[] { dao.getWkName(), dao.getWkRemark(),

						dao.getCurrVersion(), dao.getDefForm(), dao.getWkState(), dao.getUpdaterId(), dao.getWkId() });
				flag = true;
			}
		} catch (DataAccessException e) {
			flag = false;
			log.error(e.getMessage());
		}
		return flag;
	}

	private String GET_FLOWID_NODES = "select node_id, wk_id, node_name, node_type, top, left, width, height from T_WORKFLOW_NODES where wk_id=?";
	private String GET_FLOWID_LINES = "Select act_id, wk_id, act_name, pre_node_id, next_node_id, type from T_WORKFLOW_ACT where wk_id=?";
	private String GET_FLOWID_AREAS = "select area_id, wk_id, area_name, color, top, left, width, height from T_WORKFLOW_AREA where wk_id=?";

	@Override
	public String getFlowJson(String flowId) {
		List<NodeBean> _nodeList = null;
		List<LineBean> _lineList = null;
		List<AreaBean> _areaList = null;
		try {
			_nodeList = jdbcTemplate.query(GET_FLOWID_NODES, new Object[] { flowId },
					new ResultSetExtractor<List<NodeBean>>() {

						@Override
						public List<NodeBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
							List<NodeBean> list = new ArrayList<NodeBean>();
							while (rs.next()) {
								NodeBean _pojo = new NodeBean() {
									{
										setKey(rs.getString("NODE_ID"));
										setFlowId(rs.getString("WK_ID"));
										setName(rs.getString("NODE_NAME"));
										setType(rs.getString("NODE_TYPE"));
										setTop(rs.getInt("TOP"));
										setLeft(rs.getInt("LEFT"));
										setWidth(rs.getInt("WIDTH"));
										setHeight(rs.getInt("HEIGHT"));
									}
								};
								list.add(_pojo);
							}
							return (list.size() > 0 ? list : null);
						}

					});
		} catch (Exception e) {
			log.error(flowId + ",nodes提取失败!");
		}
		try {
			_lineList = jdbcTemplate.query(GET_FLOWID_LINES, new Object[] { flowId },
					new ResultSetExtractor<List<LineBean>>() {

						@Override
						public List<LineBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
							List<LineBean> list = new ArrayList<LineBean>();
							while (rs.next()) {
								LineBean _pojo = new LineBean() {
									{
										setKey(rs.getString("ACT_ID"));
										setFlowId(rs.getString("WK_ID"));
										setName(rs.getString("ACT_NAME"));
										setFrom(rs.getString("PRE_NODE_ID"));
										setTo(rs.getString("NEXT_NODE_ID"));
										setType(rs.getString("TYPE"));
									}
								};
								list.add(_pojo);
							}
							return (list.size() > 0 ? list : null);
						}

					});
		} catch (Exception e) {
			log.error(flowId + ",lines提取失败!");
		}
		try {
			_areaList = jdbcTemplate.query(GET_FLOWID_AREAS, new Object[] { flowId },
					new ResultSetExtractor<List<AreaBean>>() {

						@Override
						public List<AreaBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
							List<AreaBean> list = new ArrayList<AreaBean>();
							while (rs.next()) {
								AreaBean _pojo = new AreaBean() {
									{
										setKey(rs.getString("AREA_ID"));
										setFlowId(rs.getString("WK_ID"));
										setName(rs.getString("AREA_NAME"));
										setColor(rs.getString("COLOR"));
										setTop(rs.getInt("TOP"));
										setLeft(rs.getInt("LEFT"));
										setWidth(rs.getInt("WIDTH"));
										setHeight(rs.getInt("HEIGHT"));
									}
								};
								list.add(_pojo);
							}
							return (list.size() > 0 ? list : null);
						}

					});
		} catch (Exception e) {
			log.error(flowId + ",areas提取失败!");
		}
		StringBuffer sbf = new StringBuffer("{\"title\":\"");// {"title":"newFlow_1"
		sbf.append(flowId);
		sbf.append("\",");
		int count = 1;
		if (_nodeList != null) {
			String _s = StringUtils.join(_nodeList, ",");
			sbf.append("\"nodes\":{").append(_s).append("},");
			count = _nodeList.size() + count;
		} else {
			sbf.append("\"nodes\":{},");
		}
		if (_lineList != null) {
			String _s = StringUtils.join(_lineList, ",");
			sbf.append("\"lines\":{").append(_s).append("},");
			count = _lineList.size() + count;
		} else {
			sbf.append("\"lines\":{},");
		}
		if (_areaList != null) {
			String _s = StringUtils.join(_areaList, ",");
			sbf.append("\"areas\":{").append(_s).append("},");
		} else {
			sbf.append("\"areas\":{},");
		}
		sbf.append("\"initNum\":" + count + "}");
		return sbf.toString();
	}

	@Override
	public boolean saveDesignWorkflow(String flowId,String flowJson,String staffno){
		boolean flag = false;
		try {
			if (flowJson != null && !"".equals(flowJson.trim())) {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readValue(flowJson.trim(), JsonNode.class);
				/*
				 * if (jsonNode != null && jsonNode.has("title")) { jsonNode.get("title"); }
				 */
				if (jsonNode != null) {
					List<NodeBean> _nodeList = null;
					List<LineBean> _lineList = null;
					List<AreaBean> _areaList = null;
					if (jsonNode.has("nodes")) {
						_nodeList = new ArrayList<NodeBean>(50);
						JsonNode _nodes = jsonNode.get("nodes");
						Iterator<Map.Entry<String, JsonNode>> _it = _nodes.fields();
						while(_it.hasNext()) {
							Map.Entry<String, JsonNode> _map =_it.next();
							String _key = _map.getKey();
							NodeBean _bean =objectMapper.treeToValue(_map.getValue(),NodeBean.class);
							_bean.setKey(_key);
							_bean.setFlowId(flowId);
							_nodeList.add(_bean);
						}
					}
					if (jsonNode.has("lines")) {
						_lineList = new ArrayList<LineBean>(100);
						JsonNode _nodes = jsonNode.get("lines");
						Iterator<Map.Entry<String, JsonNode>> _it = _nodes.fields();
						while(_it.hasNext()) {
							Map.Entry<String, JsonNode> _map =_it.next();
							String _key = _map.getKey();
							LineBean _bean =objectMapper.treeToValue(_map.getValue(),LineBean.class);
							_bean.setKey(_key);
							_bean.setFlowId(flowId);
							if (_bean.getType() != null && "tb".equals(_bean.getType()))
								_bean.setType("sl");
							_lineList.add(_bean);
						}
					}
					if (jsonNode.has("areas")) {
						_areaList = new ArrayList<AreaBean>(10);
						JsonNode _nodes = jsonNode.get("areas");
						Iterator<Map.Entry<String, JsonNode>> _it = _nodes.fields();
						while(_it.hasNext()) {
							Map.Entry<String, JsonNode> _map =_it.next();
							String _key = _map.getKey();
							AreaBean _bean =objectMapper.treeToValue(_map.getValue(),AreaBean.class);
							_bean.setKey(_key);
							_bean.setFlowId(flowId);
							_areaList.add(_bean);
						}
					}
					if (log.isDebugEnabled())
						log.info("nodes="+_nodeList.size()+"/n"
								+"lines="+_lineList.size()+"/n"
								+"areas="+_areaList.size()+"/n");
					flag = saveDesignWorkflowElement(flowId,_nodeList,_lineList,_areaList);
				}
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.error(e.getLocalizedMessage());
			flag = false;
		}
		
		return flag;
	}
	
	private String DELETE_NODE_SQL = "delete from T_WORKFLOW_NODES where wk_id=''{0}''";
	private String DELETE_LINE_SQL = "delete from T_WORKFLOW_ACT where wk_id=''{0}''";
	private String DELETE_AREA_SQL = "delete from T_WORKFLOW_AREA where wk_id=''{0}''";
	private String INSERT_NODE_SQL = "insert into T_WORKFLOW_NODES(node_id, wk_id, node_name, node_type, top, left, width, height) values(''{0}'',''{1}'',''{2}'',''{3}'',{4},{5},{6},{7})";
	private String INSERT_LINE_SQL = "insert into T_WORKFLOW_ACT(act_id, wk_id, act_name, pre_node_id, next_node_id, type) values(''{0}'',''{1}'',''{2}'',''{3}'',''{4}'',''{5}'')";
	private String INSERT_AREA_SQL = "insert into T_WORKFLOW_AREA(area_id, wk_id, area_name, color, top, left, width, height) values(''{0}'',''{1}'',''{2}'',''{3}'',{4},{5},{6},{7})";
	
	public boolean saveDesignWorkflowElement(String flowId,
			List<NodeBean> nodeList,List<LineBean> lineList,
			List<AreaBean> areaList){
		if ( StringUtils.isNotEmpty(flowId) && nodeList != null
				&& lineList != null && areaList != null) {
			String[] _sqls = new String[3+nodeList.size()+lineList.size()
			+areaList.size()];
			_sqls[0] = MessageFormat.format(DELETE_NODE_SQL, flowId);
			_sqls[1] = MessageFormat.format(DELETE_LINE_SQL, flowId);
			_sqls[2] = MessageFormat.format(DELETE_AREA_SQL, flowId);
			int _count = 2;
			for (NodeBean _pojo : nodeList) {
				_count += 1;
				_sqls[_count] = MessageFormat.format(INSERT_NODE_SQL, _pojo.getKey(), _pojo.getFlowId(), _pojo.getName(),
						_pojo.getType(), _pojo.getTop(), _pojo.getLeft(), _pojo.getWidth(), _pojo.getHeight());
			}
			for (LineBean _pojo : lineList) {
				_count += 1;
				_sqls[_count] = MessageFormat.format(INSERT_LINE_SQL, _pojo.getKey(), _pojo.getFlowId(), _pojo.getName(),
						_pojo.getFrom(), _pojo.getTo(), _pojo.getType());
			}
			for (AreaBean _pojo : areaList) {
				_count += 1;
				_sqls[_count] = MessageFormat.format(INSERT_AREA_SQL, _pojo.getKey(), _pojo.getFlowId(), _pojo.getName(),
						_pojo.getColor(), _pojo.getTop(), _pojo.getLeft(), _pojo.getWidth(), _pojo.getHeight());
			}
			//log.info(_sqls.length+"");
			jdbcTemplate.batchUpdate(_sqls);
			return true;
		}
		return false;
	}

	private static final String CHECK_FLOW_SQL = "select count(1) from T_WORKFLOW where wk_id=?";
	private static final String INSERT_FLOW_SQL = "insert into T_WORKFLOW select ''{0}'' as wk_id, wk_name, wk_remark,''{1}'' as creater_id,sysdate as create_dt,''V1.0'' curr_version, def_form,wk_state,''{1}'' as updater_id from T_WORKFLOW where wk_id=''{2}''";
	private static final String DELETE_WORKSET_SQL ="delete from T_WORKFLOW_WORKGROUPSET_NEW where WK_ID=''{0}''";
	private static final String INSERT_COPY_FLOWNODE_SQL = "insert into T_WORKFLOW_NODES select node_id,''{0}'' as wk_id, node_name, node_type, top, left, width, height from T_WORKFLOW_NODES where WK_ID=''{1}''";
	private static final String INSERT_COPY_FLOWLINE_SQL = "insert into T_WORKFLOW_ACT select act_id,''{0}'' as wk_id, act_name, pre_node_id, next_node_id, type from T_WORKFLOW_ACT where WK_ID=''{1}''";
	private static final String INSERT_COPY_FLOWAREA_SQL = "insert into T_WORKFLOW_AREA select area_id,''{0}'' as wk_id, area_name, color, top, left, width, height from T_WORKFLOW_AREA where WK_ID=''{1}''";
	private static final String INSERT_COPY_FLOWWORKGROUP_SQL = "insert into T_WORKFLOW_WORKGROUPSET_NEW select ''{0}'' as wk_id, node_id, group_id, creater, create_date, states from T_WORKFLOW_WORKGROUPSET_NEW where WK_ID=''{1}''";
	@Override
	public Map<String,Object> copyOldFlowToNewFlow(String flowId,String oFlowId, String staffno) {
		Map<String,Object> result = new HashMap<String, Object>(10);
		try {
			int count = jdbcTemplate.queryForObject(CHECK_FLOW_SQL, new Object[] {flowId}, Integer.class);
			if (count >0) {
				result.put("flag", false);
				result.put("msg", "新建的流程ID已经存在!");
			}else {
				String[] _sqls = new String[9];
				_sqls[0] = MessageFormat.format(INSERT_FLOW_SQL, flowId,staffno,oFlowId);
				_sqls[1] = MessageFormat.format(DELETE_NODE_SQL, flowId);
				_sqls[2] = MessageFormat.format(DELETE_LINE_SQL, flowId);
				_sqls[3] = MessageFormat.format(DELETE_AREA_SQL, flowId);
				_sqls[4] = MessageFormat.format(DELETE_WORKSET_SQL, flowId);
				_sqls[5] = MessageFormat.format(INSERT_COPY_FLOWNODE_SQL, flowId,oFlowId);
				_sqls[6] = MessageFormat.format(INSERT_COPY_FLOWLINE_SQL, flowId,oFlowId);
				_sqls[7] = MessageFormat.format(INSERT_COPY_FLOWAREA_SQL, flowId,oFlowId);
				_sqls[8] = MessageFormat.format(INSERT_COPY_FLOWWORKGROUP_SQL, flowId,oFlowId);
				jdbcTemplate.batchUpdate(_sqls);
				result.put("flag", true);
				result.put("msg", "复制成功!");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.error(e.getLocalizedMessage());
			result.put("flag", false);
			result.put("msg", "复制过程中发生错误!");
		}
		return result;
	}

}
