package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.RoleDo;

public interface RoleService {
	
	/**
	 * 增加
	 * 
	 * @param role
	 */
	void insertRole(RoleDo role);

	/**
	 * 更新
	 * 
	 * @param role
	 * @throws Exception 
	 */
	void updateRole(RoleDo role) throws Exception;

	/**
	 * 删除
	 * 
	 * @param role
	 */
	void deleteRole(RoleDo role);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param roleId
	 * @return RoleDo
	 */
	RoleDo selectRoleById(int roleId);

	/**
	 * 根据条件查询所有数据
	 * @param role
	 * @return List<RoleDo>
	 * @throws Exception 
	 */
	public List<RoleDo> listRole(RoleDo role) throws Exception;
}
