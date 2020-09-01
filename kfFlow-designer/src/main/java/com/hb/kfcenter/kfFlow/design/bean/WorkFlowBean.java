/**
 * 
 */
package com.hb.kfcenter.kfFlow.design.bean;

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
public class WorkFlowBean {

	private String wkId,wkName, wkRemark, createrId,createDt,
	currVersion = "V1.0",defForm,wkState="A",updaterId;
	
}
