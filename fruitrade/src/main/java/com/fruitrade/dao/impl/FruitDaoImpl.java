package com.fruitrade.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.FruitDao;
import com.fruitrade.domain.FruitDo;

@Repository
public class FruitDaoImpl extends HibernateDaoSupport  implements FruitDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertFruit(FruitDo fruit) {
		super.getHibernateTemplate().save(fruit);
	}

	@Override
	public void updateFruit(FruitDo fruit) {
		super.getHibernateTemplate().update(fruit);
	}

	@Override
	public void deleteFruit(FruitDo fruit) {
		super.getHibernateTemplate().delete(fruit);
	}

	@Override
	public FruitDo selectFruitById(int fruitId) {
		return super.getHibernateTemplate().get(FruitDo.class, fruitId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param Fruit
	 * @return List<FruitDo>
	 */
	@Override
	public List<FruitDo> listFruit(FruitDo fruit) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from FruitDo where 1=1");
		if(fruit.getFruitClassifyID() !=null) {
			hql.append(" and FRUITCLASSIFYID ="+fruit.getFruitClassifyID()+"  ");
		}
		if(StringUtils.isNoneEmpty(fruit.getFruitName())) {
			hql.append(" and fruitName like '%"+fruit.getFruitName()+"%'  ");
		}

		@SuppressWarnings("unchecked")
		List<FruitDo> list= (List<FruitDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
