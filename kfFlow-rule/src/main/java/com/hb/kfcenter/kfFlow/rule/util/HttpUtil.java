/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author zhangds
 *
 */
public class HttpUtil {

	public static String sendHttpPost(String url, String JSONBody) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(new StringEntity(JSONBody));
		CloseableHttpResponse response = httpClient.execute(httpPost);
//		System.out.println(response.getStatusLine().getStatusCode() + "\n");
		String responseContent = null ;
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		}
//		System.out.println(responseContent);
		response.close();
		httpClient.close();
		return responseContent;
	}
}
