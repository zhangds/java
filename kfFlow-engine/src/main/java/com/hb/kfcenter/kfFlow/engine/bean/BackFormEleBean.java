/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(value=Include.NON_NULL)
public class BackFormEleBean {
	private String eleId = null;
	private String eleName = null;
	private boolean seeAble = false;
	private boolean museAble = false;
	@JsonIgnore
	private String opType = null;
	@JsonIgnore
	private String backFormId = null;
	@JsonIgnore
	private List<RuleBean> seeRules = null;
	@JsonIgnore
	private List<RuleBean> museRules = null;
}
