/**   
 * @Title: RoleService.java 
 * @Package org.interestTeam.model.service 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com   
 * @date 2018年1月23日 下午3:48:02 
 * @version V1.0  
 */
package org.interestTeam.model.service;

import java.util.ArrayList;
import java.util.List;

import org.interestTeam.model.database.entity.RoleEntity;
import org.interestTeam.model.database.mapper.primarySource.RoleMapper;
import org.interestTeam.model.models.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: RoleService 
 * @Description: TODO
 * @author zhangds zhang198058@hotmail.com 
 * @date 2018年1月23日 下午3:48:02 
 *  
 */
@Service
public class RoleService {
	
	@Autowired
	RoleMapper roleMapper;
	
	public List<RoleDao> getRoleList() throws Exception{
		List<RoleEntity> _list = roleMapper.getRoleList();
		return getRoleHierarchy(_list);
	}
	
	public List<RoleDao> getRoleHierarchy(List<RoleEntity> list){
		
		List<RoleDao> _list = new ArrayList<RoleDao>();
		for (RoleEntity _dao : list){
			if ( _dao.getParentRoleId() == null || "".equals(_dao.getParentRoleId()) ){
				RoleDao _roledao = new RoleDao(_dao.getRoleId(),_dao.getParentRoleId(),_dao.getRoleName(),
						_dao.getCreateUserId(),_dao.getCreateTime(),_dao.getRoleIndex(),null);
				List<RoleDao> _childList = getRecursionRoleDao(list,_dao.getRoleId());
				if (_childList != null && _childList.size()>0)
					_roledao.setChildren(_childList);
				_list.add(_roledao);
			}
		}
		return _list;
	}
	
	public List<RoleDao> getRecursionRoleDao(List<RoleEntity> dlist,String parentId){
		List<RoleDao> list = new ArrayList<RoleDao>();
		for (RoleEntity _entity:dlist){
			if (parentId.equals(_entity.getParentRoleId())){
				RoleDao _dao = new RoleDao(_entity.getRoleId(),_entity.getParentRoleId(),_entity.getRoleName(),
						_entity.getCreateUserId(),_entity.getCreateTime(),_entity.getRoleIndex(),null);
				List<RoleDao> children = getRecursionRoleDao(dlist,_entity.getRoleId());
				if (children != null && children.size()>0)
					_dao.setChildren(children);
				list.add(_dao);
			}
		}
		return list;
	}

}
