package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.OrderDo;

public interface OrderService {
	
	/**
	 * 增加
	 * 
	 * @param order
	 */
	void insertOrder(OrderDo order);

	/**
	 * 更新
	 * 
	 * @param order
	 * @throws Exception 
	 */
	void updateOrder(OrderDo order) throws Exception;

	/**
	 * 删除
	 * 
	 * @param order
	 */
	void deleteOrder(OrderDo order);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param orderId
	 * @return OrderDo
	 */
	OrderDo selectOrderById(int orderId);

	/**
	 * 根据条件查询所有数据
	 * @param order
	 * @return List<OrderDo>
	 * @throws Exception 
	 */
	public List<OrderDo> listOrder(OrderDo order) throws Exception;
}
