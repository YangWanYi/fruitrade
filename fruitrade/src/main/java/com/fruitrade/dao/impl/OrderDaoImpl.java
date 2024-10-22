package com.fruitrade.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.OrderDao;
import com.fruitrade.domain.OrderDo;

@Repository
public class OrderDaoImpl extends HibernateDaoSupport  implements OrderDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertOrder(OrderDo order) {
		super.getHibernateTemplate().save(order);
	}

	@Override
	public void updateOrder(OrderDo order) {
		super.getHibernateTemplate().update(order);
	}

	@Override
	public void deleteOrder(OrderDo order) {
		super.getHibernateTemplate().delete(order);
	}

	@Override
	public OrderDo selectOrderById(int orderId) {
		return super.getHibernateTemplate().get(OrderDo.class, orderId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param Order
	 * @return List<OrderDo>
	 */
	@Override
	public List<OrderDo> listOrder(OrderDo order) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from OrderDo where 1=1");
		if(order.getUserId()!=null) {
			hql.append(" and USERID ="+order.getUserId());
		}
		if(order.getId()!=null) {
			hql.append(" and id ="+order.getId());
		}

		@SuppressWarnings("unchecked")
		List<OrderDo> list= (List<OrderDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
