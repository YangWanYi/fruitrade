package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.CartDo;

public interface CartDao {

	/**
	 * 增加
	 * 
	 * @param cart
	 */
	void insertCart(CartDo cart);

	/**
	 * 更新
	 * 
	 * @param cart
	 */
	void updateCart(CartDo cart);

	/**
	 * 删除
	 * 
	 * @param cart
	 */
	void deleteCart(CartDo cart);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param cartId
	 * @return cartDo
	 */
	CartDo selectCartById(int cartId);

	/**
	 * 根据条件查询所有
	 * @param cart
	 * @return List<CartDo>
	 */
	List<CartDo> listCart(CartDo cart);
}
