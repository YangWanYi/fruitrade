package com.fruitrade.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.SupplyVsFruitDao;
import com.fruitrade.domain.SupplyVsFruitDo;

@Repository
public class SupplyVsFruitDaoImpl extends HibernateDaoSupport  implements SupplyVsFruitDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertSupplyVsFruit(SupplyVsFruitDo supplyVsFruit) {
		super.getHibernateTemplate().save(supplyVsFruit);
	}

	@Override
	public void updateSupplyVsFruit(SupplyVsFruitDo supplyVsFruit) {
		super.getHibernateTemplate().update(supplyVsFruit);
	}

	@Override
	public void deleteSupplyVsFruit(SupplyVsFruitDo supplyVsFruit) {
		super.getHibernateTemplate().delete(supplyVsFruit);
	}

	@Override
	public SupplyVsFruitDo selectSupplyVsFruitById(int supplyVsFruitId) {
		return super.getHibernateTemplate().get(SupplyVsFruitDo.class, supplyVsFruitId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param supplyVsFruit
	 * @return List<SupplyVsFruitDo>
	 */
	@Override
	public List<SupplyVsFruitDo> listSupplyVsFruit(SupplyVsFruitDo supplyVsFruit) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from SupplyVsFruitDo where 1=1");
		if(StringUtils.isNoneEmpty(supplyVsFruit.getSupplyType())) {// 供货商类型
			hql.append(" and supplyType ="+supplyVsFruit.getSupplyType());
		}
		if(supplyVsFruit.getSupplyId() != null) {
			hql.append(" and supplyId = "+supplyVsFruit.getSupplyId());
		}
		if(supplyVsFruit.getClassifyId() != null) {
			hql.append(" and classifyId = "+supplyVsFruit.getClassifyId());
		}

		@SuppressWarnings("unchecked")
		List<SupplyVsFruitDo> list= (List<SupplyVsFruitDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
