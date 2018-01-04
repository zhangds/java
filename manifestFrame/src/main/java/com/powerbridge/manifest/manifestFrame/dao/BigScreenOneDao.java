/**   
 * @Title: BigScreenOneDao.java 
 * @Package com.powerbridge.manifest.manifestFrame.dao 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月4日 上午10:24:22 
 * @version V1.0  
 */
package com.powerbridge.manifest.manifestFrame.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
		try {
			qn_entry = primaryJdbcTemplate.queryForObject(QN_ENTRY_SQL, String.class,new Object[]{_YYYY});
			qn_decl = primaryJdbcTemplate.queryForObject(QN_DECL_SQL, String.class,new Object[]{_YYYY,_YYYY});
			dr_entry = primaryJdbcTemplate.queryForObject(DR_ENTRY_SQL, String.class,new Object[]{_YYYYMMDD});
			dr_decl = primaryJdbcTemplate.queryForObject(DR_DECL_SQL, String.class,new Object[]{_YYYYMMDD,_YYYYMMDD});
			dr_trade_num = primaryJdbcTemplate.queryForObject(TRADE_NUM_SQL, String.class);
			qs_ship = primaryJdbcTemplate.queryForObject(QS_SHIP_SQL, String.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			qn_entry = "5";
			qn_decl = "5";
			dr_entry = "5";
			dr_decl = "5";
			dr_trade_num = "5";
			qs_ship = "5";
		}
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("qn_entry", qn_entry);
		map.put("qn_decl", qn_decl);
		map.put("dr_entry", dr_entry);
		map.put("dr_decl", dr_decl);
		map.put("dr_trade_num", dr_trade_num);
		map.put("qs_ship", qs_ship);
		return map;
	}
}
