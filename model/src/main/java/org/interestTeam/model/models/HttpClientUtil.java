package org.interestTeam.model.models;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Deprecated
public class HttpClientUtil {
	private static final String ContentEncoding = "UTF-8";
	private static final int SocketTimeout = 5000;

	/**
	 * httpClient的get请求方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url) {
		log.info("==================================doGet=={}", url);
		/*
		 * 使用 GetMethod 来访问一个 URL 对应的网页,实现步骤: 1:生成一个 HttpClinet 对象并设置相应的参数。
		 * 2:生成一个 GetMethod 对象并设置响应的参数。 3:用 HttpClinet 生成的对象来执行 GetMethod
		 * 生成的Get方法。 4:处理响应状态码。 5:若响应正常，处理 HTTP 响应内容。 6:释放连接。
		 */
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(SocketTimeout);
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, SocketTimeout);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		getMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		String response = "";
		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			log.info("==================================doGet=={}", statusCode);
			if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED
					&& statusCode != HttpStatus.SC_NO_CONTENT) {
				log.error("请求出错: " + getMethod.getStatusLine());
				return response;
			}
			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
			/*
			 * Header[] headers = getMethod.getResponseHeaders(); for (Header h
			 * : headers){ logger.info(h.getName() + "------------ " +
			 * h.getValue()); }
			 */
			// 读取 HTTP 响应内容，这里简单打印网页内容
			byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
			response = new String(responseBody, ContentEncoding);
			log.info("----------response:" + response);
			// 读取为 InputStream，在网页内容数据量大时候推荐使用
			// InputStream response = getMethod.getResponseBodyAsStream();
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			log.error("请检查输入的URL!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			log.error("发生网络异常!");
			e.printStackTrace();
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return response;
	}

	/**
	 * HttpClient PUT请求
	 * 
	 * @author huang
	 * @date 2013-4-10
	 * @return
	 */
	public static String doPut(String uri, String jsonObj) {
		log.info("==================================doPut=={},{}", uri, jsonObj);
		String resStr = "";
		HttpClient htpClient = new HttpClient();
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		putMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, ContentEncoding);
		putMethod.setRequestBody(jsonObj);
		try {
			int statusCode = htpClient.executeMethod(putMethod);
			log.info("==================================doPut=={}", statusCode);
			if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED
					&& statusCode != HttpStatus.SC_NO_CONTENT) {
				log.error("Method failed: " + putMethod.getStatusLine());
				return resStr;
			}
			byte[] responseBody = putMethod.getResponseBody();
			resStr = new String(responseBody, ContentEncoding);
			log.info("----------response:" + resStr);
		} catch (Exception e) {
			log.error(" failed: " + e.getMessage());
			e.printStackTrace();
		} finally {
			putMethod.releaseConnection();
		}
		return resStr;
	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @param jsonObj
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String doPost(String url, String jsonObj) {
		
		log.info("==================================doPost=={},{}", url, jsonObj);
		//post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonObj) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonObj.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/x-www-form-urlencoded");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            log.info("==================================doPost=={}", result.getStatusLine().getStatusCode());
            
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    return EntityUtils.toString(result.getEntity());
                } catch (Exception e) {
                    log.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
        	log.error("post请求提交失败:" + url, e);
        } finally {
        	if (method != null)
        		method.releaseConnection();
		}
        return null;
//		DefaultHttpClient client = new DefaultHttpClient();
//		HttpPost post = new HttpPost(url);
//		post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//		String response = "";
//		try {
//			StringEntity stringEntity = new StringEntity(jsonObj, "UTF-8");
//			stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded"));
//			post.setEntity(stringEntity);
//			HttpResponse res = client.execute(post);
//			int statusCode = res.getStatusLine().getStatusCode();
//			log.info("==================================doPost=={}", statusCode);
//			if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED
//					&& statusCode != HttpStatus.SC_NO_CONTENT) {
//				log.error("Method failed: " + res.getStatusLine());
//				return response;
//			}
//			response = EntityUtils.toString(res.getEntity());// 返回json格式：
//			log.info("----------response:" + response);
//		} catch (Exception e) {
//			log.error(" failed: " + e.getMessage());
//			e.printStackTrace();
//		}
//		return response;
	}

	public static String doDelete(String uri) {
		log.info("==================================doDelete=={}", uri);

		String data = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, ContentEncoding);
		DeleteMethod method = null;
		try {
			method = new DeleteMethod();
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			method.setURI(new URI(uri, false));
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, SocketTimeout);
			
			int statusCode = httpClient.executeMethod(method);
			log.info("==================================doDelete=={}", statusCode);
			if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED
					&& statusCode != HttpStatus.SC_NO_CONTENT) {
				log.error("Method failed: " + method.getStatusLine());
				return data;
			}
			data = new String(method.getResponseBody(), ContentEncoding);
			log.info("----------response:" + data);
		} catch (HttpException e) {
			e.printStackTrace();
			log.error("Please check your provided http address!");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			if (method != null)
				method.releaseConnection();
		}
		return data;
	}

	public static void main(String args[]) {
		/*
		 * String url = "http://*:8082/rest/identity/users"; Map<String, Object>
		 * vars = Maps.newHashMap(); vars.put("id", "bb"); vars.put("firstName",
		 * "我们是中国人"); HttpClientUtil.doPost(url,
		 * JsonUtil.JSON_Bean2String(vars));
		 */

		/*
		 * String url = "http://*:8082/rest/identity/users/bb"; Map<String,
		 * Object> vars = Maps.newHashMap(); vars.put("firstName",
		 * "我们是中国人"); HttpClientUtil.doPut(url,
		 * JsonUtil.JSON_Bean2String(vars));
		 */
	}
}
