/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class GroupBean {
	private String id = null;
	private String name = null;
	@JsonIgnore
	private List<String[]> extendList = null;
	@JsonIgnore
	private String cityId = null;
}
