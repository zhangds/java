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
public class NodeBean {

	private String id=null;
	
	private String name = null;
	@JsonIgnore
	private String type = null;
	@JsonIgnore
	private List<RuleBean> ruleList = null;
	@JsonIgnore
	private String dGroupId = null;
	//@JsonIgnore
	private List<GroupBean> groupList = null;
	
	private List<ActBean> actList = null;
	
	private double limitDt = 24L;
	
	private double stepLimitDt = 6L;
	
	private BackFormBean backForms = null;
	
	private List<BackFormEleBean> elements = null;
	
}
