package com.fruitrade.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.RetailDao;
import com.fruitrade.domain.RetailDo;

@Repository
public class RetailDaoImpl extends HibernateDaoSupport  implements RetailDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertRetail(RetailDo retail) {
		super.getHibernateTemplate().save(retail);
	}

	@Override
	public void updateRetail(RetailDo retail) {
		super.getHibernateTemplate().update(retail);
	}

	@Override
	public void deleteRetail(RetailDo retail) {
		super.getHibernateTemplate().delete(retail);
	}

	@Override
	public RetailDo selectRetailById(int retailId) {
		return super.getHibernateTemplate().get(RetailDo.class, retailId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param Retail
	 * @return List<Object>
	 */
	@Override
	public List<Object> listRetail(RetailDo retail) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from RetailDo b left join SupplyVsFruitDo v on b.id = v.supplyId");
//		if(StringUtils.isNoneEmpty(Retail.getRetailName())) {
//			hql.append(" and RetailNAME like '%"+Retail.getRetailName()+"%'  ");
//		}
//		if(Retail.getRoleId() != null) {
//			hql.append(" and ROLETYPE = '"+Retail.getRoleId()+"'  ");
//		}
//		if(StringUtils.isNoneEmpty(Retail.getGender())) {
//			hql.append(" and GENDER = '"+Retail.getGender()+"'  ");
//		}
//		if(StringUtils.isNoneEmpty(Retail.getPhoneNum())) {
//			hql.append(" and PHONENUM like '%"+Retail.getPhoneNum()+"%'  ");
//		}

		@SuppressWarnings("unchecked")
		List<Object> list= (List<Object>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
