package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.CartDo;

public interface CartService {
	
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
	 * @throws Exception 
	 */
	void updateCart(CartDo cart) throws Exception;

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
	 * @return CartDo
	 */
	CartDo selectCartById(int cartId);

	/**
	 * 根据条件查询所有数据
	 * @param cart
	 * @return List<CartDo>
	 * @throws Exception 
	 */
	public List<CartDo> listCart(CartDo cart) throws Exception;
}
