package com.fruitrade.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.OutputStoreDao;
import com.fruitrade.domain.OutputStoreDo;

@Repository
public class OutputStoreImpl extends HibernateDaoSupport  implements OutputStoreDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertOutputStore(OutputStoreDo outputStore) {
		super.getHibernateTemplate().save(outputStore);
	}

	@Override
	public void updateOutputStore(OutputStoreDo outputStore) {
		super.getHibernateTemplate().update(outputStore);
	}

	@Override
	public void deleteOutputStore(OutputStoreDo outputStore) {
		super.getHibernateTemplate().delete(outputStore);
	}

	@Override
	public OutputStoreDo selectOutputStoreById(int outputStoreId) {
		return super.getHibernateTemplate().get(OutputStoreDo.class, outputStoreId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param outputStore
	 * @return List<OutputStoreDo>
	 */
	@Override
	public List<OutputStoreDo> listOutputStore(OutputStoreDo outputStore) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from OutputStoreDo where 1=1");
		if(StringUtils.isNoneEmpty(outputStore.getStoreName())) {
			hql.append(" and STORENAME like '%"+outputStore.getStoreName()+"%'");
		}
		if(StringUtils.isNoneEmpty(outputStore.getStoreCode())) {
			hql.append(" and STORECODE like '%"+outputStore.getStoreCode()+"%'");
		}
		if(StringUtils.isNoneEmpty(outputStore.getFruitName())) {
			hql.append(" and FRUITNAME like '%"+outputStore.getFruitName()+"%'");
		}

		@SuppressWarnings("unchecked")
		List<OutputStoreDo> list= (List<OutputStoreDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

	@Override
	public List<Object> listWreckSumByDay() {
		StringBuffer hql=new StringBuffer();
		hql.append("select fruitName,sum(wreckNum) as wreckSum,createTime  from OutputStoreDo group by fruitName,createTime order by createTime desc ");
		@SuppressWarnings("unchecked")
		List<Object> list= (List<Object>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
