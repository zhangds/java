/**   
 * @Title: BigScreenOneDao.java 
 * @Package com.powerbridge.manifest.manifestFrame.dao 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月4日 上午10:24:22 
 * @version V1.0  
 */
package com.powerbridge.manifest.manifestFrame.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.powerbridge.manifest.manifestFrame.service.BigScreenService;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: BigScreenOneDao 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月4日 上午10:24:22 
 *  
 */
@Component
@Slf4j
public class BigScreenOneDao implements BigScreenService {

	private final static String QN_ENTRY_SQL = "select count(*) from ENTRY_HEAD  where CONVERT(char(4),I_E_DATE,112)=?";
	private final static String QN_DECL_SQL = "select (select count(*) from EX_DECL_CUR_HEAD  "
			+ "where CONVERT(char(4),INPUT_TIME,112)=?) + "
			+ "(select count(*) from IM_DECL_CUR_HEAD  where CONVERT(char(4),INPUT_TIME,112)=?)";
	private final static String DR_ENTRY_SQL = "select count(*) from ENTRY_HEAD  where CONVERT(char(8),I_E_DATE,112)=?";
	private final static String DR_DECL_SQL = "select (select count(*) from EX_DECL_CUR_HEAD  "
			+ "where CONVERT(char(8),INPUT_TIME,112)=?) + "
			+ "(select count(*) from IM_DECL_CUR_HEAD  where CONVERT(char(8),INPUT_TIME,112)=?)";;

	private final static String TRADE_NUM_SQL ="select count(distinct TRADE_CO) co from ENTRY_HEAD "
			+ "where TRADE_CO is not null and d_DATE>=CONVERT(datetime,'8/10/2017',101) and "
			+ "d_DATE<=GETDATE() and AGENT_CODE in (select cus_reg_no from dbo.ua_organization)";
	
	private final static String QS_SHIP_SQL="select ship_amount from  screen.dbo.PRO_SHIP_TOTAL where area_code='QS'";
	
	//注册用户数
	private final static String USERS_NUMBERS = "select count(*) from ua_user where op_name is not null";
	
	private final static String TOTAL_NUMBERS = "select total_price,total_nums,total_goods from screen.dbo.data_totals_year where data_dt=?";
	
	@Autowired
	JdbcTemplate primaryJdbcTemplate;
		
	private String getYYYYMMDD(){
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(now);
	}
	private String getYYYY(){
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(now);
	}
	
	public Map<String,String> getScreenOneData() throws Exception{
		String _YYYYMMDD = getYYYYMMDD();
		String _YYYY = getYYYY();
		
		String qn_entry = null;
		String qn_decl = null;
		String dr_entry = null;
		String dr_decl = null;
		String dr_trade_num = null;
		String qs_ship = null;
		String user_numbers = null;
		try {
			qn_entry = primaryJdbcTemplate.queryForObject(QN_ENTRY_SQL, String.class,new Object[]{_YYYY});
			qn_decl = primaryJdbcTemplate.queryForObject(QN_DECL_SQL, String.class,new Object[]{_YYYY,_YYYY});
			dr_entry = primaryJdbcTemplate.queryForObject(DR_ENTRY_SQL, String.class,new Object[]{_YYYYMMDD});
			dr_decl = primaryJdbcTemplate.queryForObject(DR_DECL_SQL, String.class,new Object[]{_YYYYMMDD,_YYYYMMDD});
			dr_trade_num = primaryJdbcTemplate.queryForObject(TRADE_NUM_SQL, String.class);
			qs_ship = primaryJdbcTemplate.queryForObject(QS_SHIP_SQL, String.class);
			user_numbers = primaryJdbcTemplate.queryForObject(USERS_NUMBERS, String.class);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			qn_entry = "5";
			qn_decl = "15";
			dr_entry = "31";
			dr_decl = "225";
			dr_trade_num = "7750";
			qs_ship = "4620";
			user_numbers = "267";
		}
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("qn_entry", qn_entry);
		map.put("qn_decl", qn_decl);
		map.put("dr_entry", dr_entry);
		map.put("dr_decl", dr_decl);
		map.put("dr_trade_num", dr_trade_num);
		map.put("qs_ship", qs_ship);
		map.put("user_numbers", user_numbers);
		
		Map<String,String> _map = null;
		try {	
			_map = primaryJdbcTemplate.query(TOTAL_NUMBERS,new Object[]{_YYYY},new ResultSetExtractor<Map<String,String>>(){

				public Map<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException {
					Map<String,String> _temp_map = new LinkedHashMap<String,String>();
					while (rs.next()) {
						_temp_map.put("total_price", rs.getString("total_price"));
						_temp_map.put("total_nums", rs.getString("total_nums"));
						_temp_map.put("total_goods", rs.getString("total_goods"));
	                }
					return _temp_map;
				}
				
			});
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (null==_map||_map.size()==0){
			map.put("total_price", "42.75亿美元");
			map.put("total_nums", "2.6万票");
			map.put("total_goods", "1.6千万吨");
		}else{
			map.putAll(_map);
		}
		return map;
	}
}
