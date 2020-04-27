package com.fruitrade.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.BrandDao;
import com.fruitrade.domain.BrandDo;

@Repository
public class BrandDaoImpl extends HibernateDaoSupport  implements BrandDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertBrand(BrandDo brand) {
		super.getHibernateTemplate().save(brand);
	}

	@Override
	public void updateBrand(BrandDo brand) {
		super.getHibernateTemplate().update(brand);
	}

	@Override
	public void deleteBrand(BrandDo brand) {
		super.getHibernateTemplate().delete(brand);
	}

	@Override
	public BrandDo selectBrandById(int brandId) {
		return super.getHibernateTemplate().get(BrandDo.class, brandId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param brand
	 * @return List<Object>
	 */
	@Override
	public List<Object> listBrand(BrandDo brand) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from BrandDo b left join SupplyVsFruitDo v on b.id = v.supplyId");
//		if(StringUtils.isNoneEmpty(Brand.getBrandName())) {
//			hql.append(" and brandNAME like '%"+Brand.getBrandName()+"%'  ");
//		}
//		if(Brand.getRoleId() != null) {
//			hql.append(" and ROLETYPE = '"+Brand.getRoleId()+"'  ");
//		}
//		if(StringUtils.isNoneEmpty(Brand.getGender())) {
//			hql.append(" and GENDER = '"+Brand.getGender()+"'  ");
//		}
//		if(StringUtils.isNoneEmpty(Brand.getPhoneNum())) {
//			hql.append(" and PHONENUM like '%"+Brand.getPhoneNum()+"%'  ");
//		}

		@SuppressWarnings("unchecked")
		List<Object> list= (List<Object>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
