package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.RoleDo;

public interface RoleDao {

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
	 */
	void updateRole(RoleDo role);

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
	 * @return roleDo
	 */
	RoleDo selectRoleById(int roleId);

	/**
	 * 根据条件查询所有
	 * @param role
	 * @return List<RoleDo>
	 */
	List<RoleDo> listRole(RoleDo role);
}
