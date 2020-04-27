package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.UserDo;

public interface UserService {
	
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
	 * @throws Exception 
	 */
	void updateUser(UserDo user) throws Exception;

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
	UserDo loginUser(String account, String pwd);

	/**
	 * 根据条件查询所有用户
	 * @param user
	 * @return List<UserDo>
	 * @throws Exception 
	 */
	public List<UserDo> listUser(UserDo user) throws Exception;
}
