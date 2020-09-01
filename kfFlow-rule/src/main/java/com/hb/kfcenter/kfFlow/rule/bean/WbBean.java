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
public class WbBean {
	@JsonProperty("id")
	private String sysId = null;
	@JsonProperty("name")
	private String sysName = null;
	@JsonProperty("path")
	private String classZ = null;
	@JsonProperty("md")
	private String mothod = null;
	@JsonProperty("mdName")
	private String mothodName = null;
	
}
