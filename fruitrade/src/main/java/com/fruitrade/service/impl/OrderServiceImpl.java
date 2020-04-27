package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.OrderDao;
import com.fruitrade.domain.OrderDo;
import com.fruitrade.service.OrderService;

@Transactional
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public void insertOrder(OrderDo order) {
		orderDao.insertOrder(order);		
	}

	@Override
	public void updateOrder(OrderDo order) throws Exception{
		orderDao.updateOrder(order);
	}

	@Override
	public void deleteOrder(OrderDo order) {
		orderDao.deleteOrder(order);		
	}

	@Override
	public OrderDo selectOrderById(int orderId) {
		return orderDao.selectOrderById(orderId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param order
	 * @return List<OrderDo>
	 */
	@Override
	public List<OrderDo> listOrder(OrderDo order) throws Exception{
		return orderDao.listOrder(order);
	}

}
