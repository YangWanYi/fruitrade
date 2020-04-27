package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.UserDo;

/**
 * 用户类接口
 */
public interface UserDao {

	/**
	 * 增加
	 * 
	 * @param User
	 */
	void insertUser(UserDo user);

	/**
	 * 更新
	 * 
	 * @param User
	 */
	void updateUser(UserDo user);

	/**
	 * 删除
	 * 
	 * @param User
	 */
	void deleteUser(UserDo user);

	/**
	 * 根据主键去查询
	 * 
	 * @param userId
	 * @return UserDo
	 */
	UserDo selectUserById(int userId);

	/**
	 * 根据账号和密码查询user对象
	 * 
	 * @param user
	 * @return UserDo
	 */
	UserDo loginUser(UserDo user);

	/**
	 * 根据条件查询所有用户
	 * @param user
	 * @return List<UserDo>
	 */
	List<UserDo> listUser(UserDo user);
}
