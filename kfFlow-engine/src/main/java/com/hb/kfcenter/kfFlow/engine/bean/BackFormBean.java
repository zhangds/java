/**
 * 
 */
package com.hb.kfcenter.kfFlow.engine.bean;

import java.util.List;

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
public class BackFormBean {

	private String id = null;
	private String name = null;
	List<BackFormEleBean> elements = null;
	
}
