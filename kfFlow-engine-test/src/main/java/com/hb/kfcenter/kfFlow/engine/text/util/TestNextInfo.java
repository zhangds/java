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
public class TestNextInfo {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/flowDeploy/FlowWebEngine/toNextFlowNode";
		@SuppressWarnings("serial")
		Map<String,String> params = new HashMap<String, String>(){{
			put("staffno","1001");put("flowId","1234");
			put("workCaseId","2020080500000064215X111");put("serviceId","037003002003");
			put("lineId","demo_line_6");
			put("nodeId","demo_node_3");
			put("userGroupId","20100821000019");
			put("paramJson","{\"baseForm\":[{\"colName\":\"VAL76\",\"val\":\"tel\",\"id\":\"NBR_TYPE\"},{\"colName\":\"VAL5\",\"val\":\"1001\",\"id\":\"SERVICE_CITY\"},{\"colName\":\"VAL1\",\"val\":\"1111111111\",\"id\":\"SUBS_NUMBER\"},{\"colName\":\"VAL2\",\"val\":\"1111111111\",\"id\":\"CALLER_NO\"},{\"colName\":\"VAL3\",\"val\":\"2020-08-20 10:41:59\",\"id\":\"ACCEPT_TIME\"},{\"colName\":\"VAL4\",\"val\":\"null(null)\",\"id\":\"SUBS_NAME\"},{\"colName\":\"VAL6\",\"val\":\"1111111111\",\"id\":\"CONTACT_PHONE1\"},{\"colName\":\"VAL7\",\"val\":\"\",\"id\":\"CONTACT_PHONE2\"},{\"colName\":\"VAL75\",\"val\":\"农村\",\"id\":\"COUNTRY_FLAG\"},{\"colName\":\"VAL9\",\"val\":\"001(测试勿删)\",\"id\":\"ACCEPT_STAFFNO\"},{\"colName\":\"VAL10\",\"val\":\"1\",\"id\":\"CONTACT_CHANNEL\"},{\"colName\":\"VAL11\",\"val\":\"\",\"id\":\"LINE_NBR\"},{\"colName\":\"VAL8\",\"val\":\"0星\",\"id\":\"SUBS_LEVEL\"},{\"colName\":\"VAL77\",\"val\":\"-65536\",\"id\":\"MAJOR_COMPLAINT_TYPE\"},{\"colName\":\"VAL78\",\"val\":\"0\",\"id\":\"REPEAT_COMPLAIN_COUNT\"},{\"colName\":\"VAL16\",\"val\":\"001\",\"id\":\"CUSTOMER_MOOD\"},{\"colName\":\"VAL13\",\"val\":\"\",\"id\":\"G_ORDER_CODE\"},{\"colName\":\"VAL14\",\"val\":\"省公司\",\"id\":\"ACCEPT_STAFFDEPT\"},{\"colName\":\"VAL21\",\"val\":\"\",\"id\":\"GRID_MANAGER\"},{\"colName\":\"VAL25\",\"val\":\"\",\"id\":\"ADDRESS\"},{\"colName\":\"VAL26\",\"val\":\"\",\"id\":\"DISPATCH_ID\"},{\"colName\":\"VAL27\",\"val\":\"\",\"id\":\"RECEIVE_ID\"},{\"colName\":\"VAL28\",\"val\":\"\",\"id\":\"DISPATCH_NBR\"},{\"colName\":\"VAL29\",\"val\":\"\",\"id\":\"MIIT_CODE\"},{\"colName\":\"VAL30\",\"val\":\"\",\"id\":\"MIIT_CONTACT\"},{\"colName\":\"VAL31\",\"val\":\"-65536\",\"id\":\"COMPLAIN_ORDER_TYPE\"},{\"colName\":\"VAL32\",\"val\":\"-65536\",\"id\":\"COMPLAIN_TYPE\"},{\"colName\":\"VAL33\",\"val\":\"-65536\",\"id\":\"COMPLAIN_ORDER_STATE\"},{\"colName\":\"VAL34\",\"val\":\"\",\"id\":\"RECEIVE_NBR\"},{\"colName\":\"VAL35\",\"val\":\"\",\"id\":\"HANDLE_HOURS\"},{\"colName\":\"VAL36\",\"val\":\"-65536\",\"id\":\"BRAND_TYPE\"},{\"colName\":\"VAL37\",\"val\":\"\",\"id\":\"COMPLAIN_SOURCE\"},{\"colName\":\"VAL38\",\"val\":\"-65536\",\"id\":\"COMPLAIN_REASON_TYPE\"},{\"colName\":\"VAL39\",\"val\":\"\",\"id\":\"POSTAL_CODE\"},{\"colName\":\"VAL40\",\"val\":\"-65536\",\"id\":\"HANDLE_PROVINCE\"},{\"colName\":\"VAL41\",\"val\":\"-65536\",\"id\":\"CUSTOMERS_TYPE\"},{\"colName\":\"VAL42\",\"val\":\"-65536\",\"id\":\"COMPLAIN_GRADE\"},{\"colName\":\"VAL43\",\"val\":\"\",\"id\":\"ISMI_NBR\"},{\"colName\":\"VAL44\",\"val\":\"\",\"id\":\"PHONE_TYPE\"},{\"colName\":\"VAL45\",\"val\":\"\",\"id\":\"SERVICE_TYPE\"},{\"colName\":\"VAL46\",\"val\":\"\",\"id\":\"REPEAT_FLAG\"},{\"colName\":\"VAL47\",\"val\":\"\",\"id\":\"G_SPECIAL_REQUEST\"},{\"colName\":\"VAL48\",\"val\":\"-65536\",\"id\":\"COMMON_PHRASES\"},{\"colName\":\"VAL49\",\"val\":\"\",\"id\":\"G_COMPLAIN_TYPE\"},{\"colName\":\"VAL50\",\"val\":\"-65536\",\"id\":\"COMPLAIN_TYPE\"},{\"colName\":\"VAL51\",\"val\":\"-65536\",\"id\":\"G_COMPLAIN_ORDER_TYPE\"},{\"colName\":\"VAL52\",\"val\":\"\",\"id\":\"G_SERIAL\"},{\"colName\":\"VAL53\",\"val\":\"\",\"id\":\"AGAIN_REASON\"},{\"colName\":\"VAL54\",\"val\":\"\",\"id\":\"PROVINCE_OPINION\"},{\"colName\":\"VAL55\",\"val\":\"\",\"id\":\"REMARK\"},{\"colName\":\"VAL56\",\"val\":\"\",\"id\":\"HANDLE_RESULT\"},{\"colName\":\"VAL17\",\"val\":\"\",\"id\":\"BUSINESSHALL_ID\"},{\"colName\":\"VAL18\",\"val\":\"\",\"id\":\"SALE_BUSINESSHALL_ID\"},{\"colName\":\"VAL19\",\"val\":\"否\",\"id\":\"DAILY_REPAIR_SCOPE\"},{\"colName\":\"VAL20\",\"val\":\"-65536\",\"id\":\"FIRST_APPOINTMENT_TIME\"},{\"colName\":\"VAL15\",\"val\":\"\",\"id\":\"SERVICE_TITLE\"},{\"colName\":\"VAL22\",\"val\":\"\",\"id\":\"CUSTOMER_TYPE\"},{\"colName\":\"VAL23\",\"val\":\"\",\"id\":\"HIGH_END_CUSTOMER\"},{\"colName\":\"VAL24\",\"val\":\"\",\"id\":\"SERVICE_TRADES_NAME\"},{\"colName\":\"VAL57\",\"val\":\"\",\"id\":\"CONTACTS\"},{\"colName\":\"VAL58\",\"val\":\"手机\",\"id\":\"PHONE_MODEL\"},{\"colName\":\"VAL59\",\"val\":\"人工\",\"id\":\"CONTACT_INFORMATION\"},{\"colName\":\"VAL60\",\"val\":\"\",\"id\":\"EMAIL\"},{\"colName\":\"VAL61\",\"val\":\"\",\"id\":\"MIIT_CONTACT2\"},{\"colName\":\"VAL62\",\"val\":\"-65536\",\"id\":\"USER_BRAND\"},{\"colName\":\"VAL63\",\"val\":\"\",\"id\":\"USER_LOCATION\"},{\"colName\":\"VAL64\",\"val\":\"\",\"id\":\"USER_BUREAU\"},{\"colName\":\"VAL65\",\"val\":\"\",\"id\":\"REQUEST_NUM\"},{\"colName\":\"VAL66\",\"val\":\"人工\",\"id\":\"ACCEPT_WAY\"},{\"colName\":\"VAL67\",\"val\":\"一般\",\"id\":\"EMERGENCY\"},{\"colName\":\"VAL68\",\"val\":\"中\",\"id\":\"IMPORTANCE\"},{\"colName\":\"VAL69\",\"val\":\"中\",\"id\":\"PRIORITY\"},{\"colName\":\"VAL70\",\"val\":\"\",\"id\":\"SPECIAL_REQUEST\"},{\"colName\":\"VAL71\",\"val\":\"正常\",\"id\":\"WARNING_LEVEL\"},{\"colName\":\"VAL72\",\"val\":\"是\",\"id\":\"IS_UPGRADE\"},{\"colName\":\"VAL73\",\"val\":\"表示不满（C类客户）\",\"id\":\"CUSTOMER_PROPERTY\"},{\"colName\":\"VAL74\",\"val\":\"普通话\",\"id\":\"LANGUAGES\"},{\"colName\":\"VAL80\",\"val\":\"\",\"id\":\"SERV_ID\"},{\"colName\":\"VAL79\",\"val\":\"\",\"id\":\"ACCT_ID\"},{\"colName\":\"VAL81\",\"val\":\"0\",\"id\":\"REPEAT_COUNT\"},{\"colName\":\"VAL82\",\"val\":\"\",\"id\":\"PRODUCT_ID\"},{\"colName\":\"VAL83\",\"val\":\"\",\"id\":\"CHANNEL_BRANCH\"},{\"colName\":\"VAL84\",\"val\":\"\",\"id\":\"CONTACT_ID\"},{\"colName\":\"VAL12\",\"val\":\"1111111111\\n进入电子发票场景检测：已检测（场景无法解决）；\\n投诉场景：重新推送成功仍未收到；\\n发票信息修改：通过电子发票场景检测答复（不可手动记单）；\",\"id\":\"CONTENT\"}],\"highForm\":[{\"id\":\"870ca620-b3a4-4aa4-8d63-750e3446e95c\",\"val\":\"已检测（场景无法解决）\"},{\"id\":\"246706fd-3d5f-4f92-823d-25735ea86bb8\",\"val\":\"重新推送成功仍未收到\"},{\"id\":\"80197426-1adc-4ff6-86ea-26961a70250b\",\"val\":\"通过电子发票场景检测答复（不可手动记单）\"}],\"replyForm\":[]}");
		}};
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
