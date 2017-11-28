/**
 * 
 */
package com.powerbridge.manifest.manifestFrame.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.powerbridge.manifest.manifestFrame.service.DataSaveDbService;

import lombok.Data;

/**
 * @author dongshengzhang
 * @category 和业务相关的解析入库
 */
@ConfigurationProperties(prefix="com.powerbridge.manifest.manifestFrame.xml")
@Component
//@Repository
@Data
public class DataSaveDbDao implements DataSaveDbService{
	
	private final static Logger logger = LoggerFactory.getLogger(DataSaveDbDao.class);
	String pixNode;
	
	SimpleDateFormat sdfTime = new SimpleDateFormat( "yyyyMMddHHmmss" );
	SimpleDateFormat sdfDate = new SimpleDateFormat( "yyyyMMdd" );
	
	@SuppressWarnings("serial")
	public static final Map<String,String> realTableMap = new LinkedHashMap<String, String>() {{
		put("MT1201", "im_manifest_air_transport_MT1201");
		put("MT2201", "im_manifest_air_transport_MT1201");		
		put("MT3201", "im_manifest_air_transport_MT3201");
		put("MT2201", "im_manifest_air_transport_MT1201");
		put("MT4201", "im_manifest_air_transport_MT4201");
		put("MT5201", "im_manifest_air_transport_MT5201");
		put("MT8201", "im_manifest_air_transport_MT8201");
		put("MT8202", "im_manifest_air_transport_MT8202");
		put("MT8204", "im_manifest_air_transport_MT8204");
		put("MT8205", "im_manifest_air_transport_MT8205");
		put("MT5202", "im_manifest_air_transport_MT3201");
	}};
	
	@Autowired
	JdbcTemplate primaryJdbcTemplate;
	
	public String[] getReplaceString(){
		String[] pixStrings = null;
		if (pixNode != null){
			if (pixNode.contains(",")){
				pixStrings = pixNode.split(",");
			}else{
				pixStrings = new String[]{pixNode};
			}
		}
		return pixStrings;
	}
	
	public String getRealTableName(String functionCode){
		String resultString = null;
		if (functionCode != null && functionCode.length() >0 ){
			
		}
		return resultString;
	}

	public void saveDataToDB(Map<String, String> map) throws Exception{
		// TODO Auto-generated method stub
		Map<String,Object> _headMap = getSubDatas(0,map);
		String _messageType = _headMap.get("MessageType").toString();
		String _dataRealTable = "*";
		if (realTableMap.containsKey(_messageType)){
			_dataRealTable = realTableMap.get(_messageType);
		}
		_headMap.put("Relation_table", _dataRealTable);
		boolean _headflag = executeToSave("insert into im_manifest_totals (",_headMap);
		
		//TODO check _headflag
		if (_headflag == true){
			Map<String,Object> _DataMap = getSubDatas(1,map);
			_DataMap.put("MessageID",_headMap.get("MessageID"));
			_DataMap.put("FunctionCode",_headMap.get("FunctionCode"));
			_DataMap.put("MessageType",_headMap.get("MessageType"));
			executeToSave("insert into "+_dataRealTable+" (",_DataMap);
			logger.debug(_DataMap.toString());
		}
		
	}
	
	/**
	 * 
	 * @param pixString 头文
	 * @param map 数据池
	 * @return
	 */
	private boolean executeToSave(String pixString,Map<String,Object> map){
		boolean flag = false ;
		
		try {
			StringBuffer sbf = new StringBuffer(pixString);
			if ( map != null && map.size() >0 ){
				//sbf.append("insert into im_manifest_totals (");
				StringBuffer _sbf1 = new StringBuffer("");
				Object[] _obj = new Object[map.size()];
				int _index = 0 ;
				for ( String _key : map.keySet() ){
					sbf.append(_key+",");
					_sbf1.append("?,");
					_obj[_index] = map.get(_key);
					_index++;
				}
				sbf.deleteCharAt(sbf.length() - 1);
				_sbf1.deleteCharAt(_sbf1.length() - 1);
				sbf.append(") values(");
				sbf.append(_sbf1+")");
				logger.debug(sbf.toString());
				if (logger.isDebugEnabled()){
					for (Object _temp :_obj)
						logger.debug(_temp.toString());
				}
				primaryJdbcTemplate.update(sbf.toString(),_obj);
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			logger.error(e.getMessage());
		}
		
		return flag;
	}
	
//	public boolean dbExecute(String sql,Object[] obj){
//		primaryJdbcTemplate.update(sql,obj);
//	}
	/**
	 * @category 有强业务逻辑
	 * @param map
	 * @return
	 */
	public Map<String,Object> getSubDatas(int i ,Map<String, String> map) throws ParseException{
		Map<String,Object> _resultMap = null;
		if ( map != null && map.size() >0 ){
			String[] _pixString = getReplaceString();
			if (_pixString.length > 0){
				_resultMap = new LinkedHashMap<String, Object>();
				for (String key : map.keySet()){
					if (key.startsWith(_pixString[i])){
						String _key = key.replace(_pixString[i], "");
						Object _object = map.get(key) ;
						if (key.endsWith("Time")){
							_object = sdfTime.parse(map.get(key));
						}else if (key.endsWith("Date")){
							if (map.get(key) != null && map.get(key).length() == 8){
								_object = sdfDate.parse(map.get(key));
							}else if (map.get(key) != null && map.get(key).length() > 8){
								_object = sdfTime.parse(map.get(key));
							}
						}
						_resultMap.put(_key, _object);
					}
				}
			}
		}
		return _resultMap;
		
	}
}
