package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.OrderDo;

public interface OrderDao {

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
	 */
	void updateOrder(OrderDo order);

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
	 * @return orderDo
	 */
	OrderDo selectOrderById(int orderId);

	/**
	 * 根据条件查询所有
	 * @param order
	 * @return List<OrderDo>
	 */
	List<OrderDo> listOrder(OrderDo order);
}
