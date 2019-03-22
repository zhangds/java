/**   
 * @Title: Test.java 
 * @Package org.interestTeam.model.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2017年12月19日 下午5:25:22 
 * @version V1.0  
 */
package org.interestTeam.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.interestTeam.model.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: Test 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2017年12月19日 下午5:25:22 
 *  
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class Test {
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    //@org.junit.Test
    public void test(){
    	String s = Test.sendPost("http://cert.wh-eport.cn:9099/api/user/zhangds", "FNumber=zhangds&FName=zhangds&FMyName=zhangds");
    	System.out.println(s);
    }
   
    public String[] getStarBalance(String isMoblie,String starLevel){
    	String[] result = null;
    	Map<String,String[]> isMobileStarMap = new LinkedHashMap<String,String[]>();
	    String _string = "3##2##10";
	    isMobileStarMap.put("0星",_string.split("##"));
	    isMobileStarMap.put("1星",_string.split("##"));
	    isMobileStarMap.put("2星",_string.split("##"));
	    isMobileStarMap.put("3星",_string.split("##"));
	    isMobileStarMap.put("4星",_string.split("##"));
	    _string = "3##2##100";
	    isMobileStarMap.put("5星",_string.split("##"));
	    _string = "3##2##200";
	    isMobileStarMap.put("6星",_string.split("##"));
	    _string = "3##2##300";
	    isMobileStarMap.put("7星",_string.split("##"));
	    
	    Map<String,String[]> isADSLStarMap = new LinkedHashMap<String,String[]>();
	    isADSLStarMap.put("0星","3".split("##"));
	    isADSLStarMap.put("1星","3".split("##"));
	    isADSLStarMap.put("2星","3".split("##"));
	    isADSLStarMap.put("3星","7".split("##"));
	    isADSLStarMap.put("4星","7".split("##"));
	    isADSLStarMap.put("5星","7".split("##"));
	    isADSLStarMap.put("6星","7".split("##"));
	    isADSLStarMap.put("7星","7".split("##"));
	    starLevel = (starLevel== null||"".equals(starLevel)?"0星":starLevel);
	    //isMoblie = (isMoblie == null?"1":isMoblie);
	    if (isMoblie != null && !"0".equals(isMoblie)){
	    	//手机
	    	if (isMobileStarMap.containsKey(starLevel)){
	    		result = isMobileStarMap.get(starLevel);
	    	}else{
	    		result = isMobileStarMap.get("0星");
	    	}
	    }else{
	    	if (isADSLStarMap.containsKey(starLevel)){
	    		result = isADSLStarMap.get(starLevel);
	    	}else{
	    		result = isADSLStarMap.get("0星");
	    	}
	    }
	    return result;
    }
    
    public boolean isFloatorDouble(String floatString){
		Pattern pattern = Pattern.compile("-?[0-9]+.*[0-9]*");
	    if (pattern.matcher(floatString).matches()){
	    	//double d_cur_balance = Double.valueOf(floatString);
	    	return true;
	    }
	    return false;
	}
    
    @org.junit.Test
    public void t(){
    	Test tt = new Test();
    	/*
    	String[] s = tt.getQryDateString("20180428", "20180428");
    	System.out.println(s);
    	StringBuffer sbf =  new StringBuffer(".,");
    	sbf.deleteCharAt(sbf.length()-1);
    	System.out.println(sbf.toString());*/
//    	String[] y = tt.getStarBalance("", "");
//    	System.out.println(y);
//    	y = tt.getStarBalance("1", "7星");
//    	System.out.println(y);
//    	y = tt.getStarBalance("0", "7星");
//    	System.out.println(y);
//    	
//    	String s = "-0.9972";
//    	System.out.println(isFloatorDouble(s));
//    	s = "0.9972";
//    	System.out.println(isFloatorDouble(s));
//    	s = "10";
//    	System.out.println(isFloatorDouble(s));
//    	
//    	System.out.println(addDates(4));
    	Calendar ca = GregorianCalendar.getInstance();
    	System.out.println(isLastDayOfMonth("20180522"));
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.SIMPLIFIED_CHINESE);
    	try {
			//Date d = sdf.parse("20180531");
			System.out.println(isLastDayOfMonth("20180531"));
			System.out.println("20180531".substring(4, "20180531".length()));
			
			String discreditdDate = "2018-05-25 16:51:26";
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.SIMPLIFIED_CHINESE);
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日", Locale.SIMPLIFIED_CHINESE);
			String ss = sdf2.format(sdf1.parse(discreditdDate));
			System.out.println(discreditdDate.substring(0, 4)+"年"+ discreditdDate.substring(5, 7)+"月"+ discreditdDate.substring(8, 10)+"日");
			System.out.println(ss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public String addDates(int addInt){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
    	Calendar ca = GregorianCalendar.getInstance();
    	ca.add(Calendar.DATE, addInt);
    	
		return sdf.format(ca.getTime());
    }
    
    public String[] getQryDateString(String startDate,String endDate){
    	String[] result = null;
    	try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.SIMPLIFIED_CHINESE);
			Date _startDate = sdf.parse(startDate);
			Date _endDate = sdf.parse(endDate);
			
			int days = (int) ((_endDate.getTime() - _startDate.getTime()) / (1000*3600*24));
			if ( days >= 0 ){
				result = new String[days+1];
				result[0] = startDate ;
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(_startDate);
				for (int i=1;i<days+1;i++){
					calendar.add(Calendar.DATE, 1);
					result[i] = sdf.format(calendar.getTime());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return result;
  	}
    
    boolean isLastDayOfMonth(String yyyyMMdd) {
    	boolean result = false ;
    	try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.SIMPLIFIED_CHINESE);
			Date date = sdf.parse(yyyyMMdd);
			Calendar calendar = Calendar.getInstance(); 
			calendar.setTime(date); 
			calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1)); 
			if (calendar.get(Calendar.DAY_OF_MONTH) == 1) { 
				result = true; 
			}
		} catch (Exception e) {
			result = false ;
		} 
        return result; 
    }
    
    
}
