package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.CartDao;
import com.fruitrade.domain.CartDo;
import com.fruitrade.service.CartService;

@Transactional
@Service("CartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao cartDao;

	@Override
	public void insertCart(CartDo cart) {
		cartDao.insertCart(cart);		
	}

	@Override
	public void updateCart(CartDo cart) throws Exception{
		cartDao.updateCart(cart);
	}

	@Override
	public void deleteCart(CartDo cart) {
		cartDao.deleteCart(cart);		
	}

	@Override
	public CartDo selectCartById(int cartId) {
		return cartDao.selectCartById(cartId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param cart
	 * @return List<CartDo>
	 */
	@Override
	public List<CartDo> listCart(CartDo cart) throws Exception{
		return cartDao.listCart(cart);
	}

}
