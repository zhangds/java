/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.bean;

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
public class LineBean {
	@JsonProperty("id")
	private String LineId=null;
	@JsonProperty("name")
	private String LineName=null;
	@JsonProperty("formName")
	private String formNodeName=null;
	@JsonProperty("nextName")
	private String toNodeName=null;
	@JsonProperty("isSet")
	private boolean isSetFlag=false;
}
