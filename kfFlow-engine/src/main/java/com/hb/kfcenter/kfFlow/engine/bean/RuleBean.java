/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class RuleBean {

	private String id=null;
	@JsonProperty("pId")
	private String pId=null;
	@JsonProperty("type")
	private String type = null;
	@JsonProperty("p1")
	private String paraOne = null;
	@JsonProperty("p2")
	private String paraTwo = null;
	@JsonIgnore
	private String paraThree = null;
	@JsonIgnore
	private String ortherId = null;
	@JsonIgnore
	private String opType =null;
	
}
