/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.text.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @author zhangds
 *
 */
public class HttpUtil {

	public static String sendHttpPostBody(String url, String JSONBody) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(new StringEntity(JSONBody));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8"); 
		response.close();
		httpClient.close();
		return responseContent;
	}
	
	public static String sendHttpPost(String url, Map<String, String> params) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url);
		//httpPost.addHeader("Content-Type", "application/json");
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        for (String key : params.keySet()) {
        	qparams.add(new BasicNameValuePair(key,params.get(key)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(qparams,HTTP.UTF_8));
      //设置编码
        CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8"); 
		response.close();
		httpClient.close();
		return responseContent;
	}
}
