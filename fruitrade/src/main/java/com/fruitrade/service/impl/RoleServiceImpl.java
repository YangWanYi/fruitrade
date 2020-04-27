package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.RoleDao;
import com.fruitrade.domain.RoleDo;
import com.fruitrade.service.RoleService;

@Transactional
@Service("RoleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public void insertRole(RoleDo role) {
		roleDao.insertRole(role);		
	}

	@Override
	public void updateRole(RoleDo role) throws Exception{
		roleDao.updateRole(role);
	}

	@Override
	public void deleteRole(RoleDo role) {
		roleDao.deleteRole(role);		
	}

	@Override
	public RoleDo selectRoleById(int roleId) {
		return roleDao.selectRoleById(roleId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param role
	 * @return List<RoleDo>
	 */
	@Override
	public List<RoleDo> listRole(RoleDo role) throws Exception{
		return roleDao.listRole(role);
	}

}
