/**   
 * @Title: MenuDao.java 
 * @Package org.interestTeam.model2.dao 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2020年1月15日 下午4:01:20 
 * @version V1.0  
 */
package org.interestTeam.model2.dao;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @ClassName: MenuDao 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2020年1月15日 下午4:01:20 
 *  
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDao {

	@JSONField(name="id")
	private String menuId;
	@JSONField(name="pid")
	private String parentId;
	@JSONField(name="text")
	private String menuName;
	@JSONField(name="state")
	private String iconState;
	@JSONField(name="iconCls")
	private String iconCls;
	@JSONField(name="url")
	private String url;
	@JSONField(name="params")
	private String params;
	@JSONField(name="createrId")
	private String createrId;
	@JSONField(name="menuState")
	private String state;
	@JSONField(name="orderNum")
	private int orderNum;
	private List<MenuDao> children;
}
