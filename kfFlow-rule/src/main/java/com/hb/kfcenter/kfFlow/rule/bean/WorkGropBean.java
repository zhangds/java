/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkGropBean {
	private String id=null;
	@JsonProperty("pId")
	private String pid=null;
	private String name=null;
	@JsonIgnore
	private String flowId = null;
	@JsonIgnore
	private String nodeId = null;
	@JsonIgnore
	private String creater = null;
	@JsonProperty("state")
	private String states = "00A";
}
