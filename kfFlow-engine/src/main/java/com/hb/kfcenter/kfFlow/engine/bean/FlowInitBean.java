/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangds
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowInitBean {

	@JsonProperty("flowInId")
	private String initId=null;
	private String flowId=null;
	@JsonProperty("stepId")
	private String nodeId=null;
	private String serviceId=null;
	private String groupId = null;
}
