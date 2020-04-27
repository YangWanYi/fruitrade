package com.fruitrade.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.PurchaseDao;
import com.fruitrade.domain.PurchaseDo;

@Repository
public class PurchaseDaoImpl extends HibernateDaoSupport  implements PurchaseDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertPurchase(PurchaseDo purchase) {
		super.getHibernateTemplate().save(purchase);
	}

	@Override
	public void updatePurchase(PurchaseDo purchase) {
		super.getHibernateTemplate().update(purchase);
	}

	@Override
	public void deletePurchase(PurchaseDo purchase) {
		super.getHibernateTemplate().delete(purchase);
	}

	@Override
	public PurchaseDo selectPurchaseById(int purchaseId) {
		return super.getHibernateTemplate().get(PurchaseDo.class, purchaseId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param Purchase
	 * @return List<Object>
	 */
	@Override
	public List<Object> listPurchase(PurchaseDo purchase) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from PurchaseDo b left join SupplyVsFruitDo v on b.supplyVsFruitId = v.id");
//		if(StringUtils.isNoneEmpty(Purchase.getPurchaseName())) {
//			hql.append(" and PurchaseNAME like '%"+Purchase.getPurchaseName()+"%'  ");
//		}
//		if(Purchase.getRoleId() != null) {
//			hql.append(" and ROLETYPE = '"+Purchase.getRoleId()+"'  ");
//		}
//		if(StringUtils.isNoneEmpty(Purchase.getGender())) {
//			hql.append(" and GENDER = '"+Purchase.getGender()+"'  ");
//		}
//		if(StringUtils.isNoneEmpty(Purchase.getPhoneNum())) {
//			hql.append(" and PHONENUM like '%"+Purchase.getPhoneNum()+"%'  ");
//		}

		@SuppressWarnings("unchecked")
		List<Object> list= (List<Object>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
