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
public class ParamBean {

	@JsonProperty("id")
	private String keyId=null;
	@JsonProperty("name")
	private String keyValue=null;
	@JsonProperty("form")
	private String forms=null;
	@JsonProperty("fieldId")
	private String fieldName=null;
}
