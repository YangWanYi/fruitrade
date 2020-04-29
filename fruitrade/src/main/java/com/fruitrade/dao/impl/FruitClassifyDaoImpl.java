package com.fruitrade.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.FruitClassifyDao;
import com.fruitrade.domain.FruitClassifyDo;

@Repository
public class FruitClassifyDaoImpl extends HibernateDaoSupport  implements FruitClassifyDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertFruitClassify(FruitClassifyDo fruitClassify) {
		super.getHibernateTemplate().save(fruitClassify);
	}

	@Override
	public void updateFruitClassify(FruitClassifyDo fruitClassify) {
		super.getHibernateTemplate().update(fruitClassify);
	}

	@Override
	public void deleteFruitClassify(FruitClassifyDo fruitClassify) {
		super.getHibernateTemplate().delete(fruitClassify);
	}

	@Override
	public FruitClassifyDo selectFruitClassifyById(int fruitClassifyId) {
		return super.getHibernateTemplate().get(FruitClassifyDo.class, fruitClassifyId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param FruitClassify
	 * @return List<FruitClassifyDo>
	 */
	@Override
	public List<FruitClassifyDo> listFruitClassify(FruitClassifyDo fruitClassify) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from FruitClassifyDo where 1=1");
		if(StringUtils.isNoneEmpty(fruitClassify.getClassifyName())) {
			hql.append(" and classifyName like '%"+fruitClassify.getClassifyName()+"%'  ");
		}
		if(StringUtils.isNoneEmpty(fruitClassify.getClassifyCode())) {
			hql.append(" and classifyCode like '%"+fruitClassify.getClassifyCode()+"%'  ");
		}

		@SuppressWarnings("unchecked")
		List<FruitClassifyDo> list= (List<FruitClassifyDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
