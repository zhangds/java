/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.text.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhangds
 *
 */
public class BackHistory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/flowDeploy/FlowWebEngine/setBackOption";
		@SuppressWarnings("serial")
		Map<String, String> params = new HashMap<String, String>() {
			{
				put("staffno", "1001");
				put("workCaseId", "2020080500000064215X");
			}
		};
		try {
			String s = HttpUtil.sendHttpPost(url, params);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(s);
			System.out.println(node.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
