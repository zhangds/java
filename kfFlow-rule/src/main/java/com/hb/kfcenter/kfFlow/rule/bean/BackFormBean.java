/**
 * 
 */
package com.hb.kfcenter.kfFlow.rule.bean;

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
public class BackFormBean {
	private String id = null;
	private String name = null;
	private String isKj = null;
	private String isBt = null;
	private String type = "backForm";
	private String bkId = null;
}
