package com.fruitrade.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.CartDao;
import com.fruitrade.domain.CartDo;

@Repository
public class CartDaoImpl extends HibernateDaoSupport  implements CartDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertCart(CartDo cart) {
		super.getHibernateTemplate().save(cart);
	}

	@Override
	public void updateCart(CartDo cart) {
		super.getHibernateTemplate().update(cart);
	}

	@Override
	public void deleteCart(CartDo cart) {
		super.getHibernateTemplate().delete(cart);
	}

	@Override
	public CartDo selectCartById(int cartId) {
		return super.getHibernateTemplate().get(CartDo.class, cartId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param Cart
	 * @return List<CartDo>
	 */
	@Override
	public List<CartDo> listCart(CartDo cart) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from CartDo where 1=1");
		if(cart.getUserId()!=null) {
			hql.append(" and USERID ="+cart.getUserId());
		}
//		if(Cart.getRoleId() != null) {
//			hql.append(" and ROLETYPE = '"+Cart.getRoleId()+"'  ");
//		}
//		if(StringUtils.isNoneEmpty(Cart.getGender())) {
//			hql.append(" and GENDER = '"+Cart.getGender()+"'  ");
//		}
		if(StringUtils.isNoneEmpty(cart.getFruitName())) {
			hql.append(" and fruitName like '%"+cart.getFruitName()+"%'  ");
		}

		@SuppressWarnings("unchecked")
		List<CartDo> list= (List<CartDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
