package com.fruitrade.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
		hql.append(" from BrandDo b left join SupplyVsFruitDo v on b.id = v.supplyId where 1=1");
		if(StringUtils.isNoneEmpty(brand.getBrandName())) {
			hql.append(" and b.brandName like '%"+brand.getBrandName()+"%'  ");
		}
		if(StringUtils.isNoneEmpty(brand.getPrincipal())) {
			hql.append(" and b.principal like '%"+brand.getPrincipal()+"%'  ");
		}

		@SuppressWarnings("unchecked")
		List<Object> list= (List<Object>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
