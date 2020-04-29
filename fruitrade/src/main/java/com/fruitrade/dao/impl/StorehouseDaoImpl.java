package com.fruitrade.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.StorehouseDao;
import com.fruitrade.domain.StorehouseDo;

@Repository
public class StorehouseDaoImpl extends HibernateDaoSupport  implements StorehouseDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertStorehouse(StorehouseDo storehouse) {
		super.getHibernateTemplate().save(storehouse);
	}

	@Override
	public void updateStorehouse(StorehouseDo storehouse) {
		super.getHibernateTemplate().update(storehouse);
	}

	@Override
	public void deleteStorehouse(StorehouseDo storehouse) {
		super.getHibernateTemplate().delete(storehouse);
	}

	@Override
	public StorehouseDo selectStorehouseById(int storehouseId) {
		return super.getHibernateTemplate().get(StorehouseDo.class, storehouseId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param Storehouse
	 * @return List<StorehouseDo>
	 */
	@Override
	public List<StorehouseDo> listStorehouse(StorehouseDo storehouse) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from StorehouseDo where 1=1");
		if(StringUtils.isNoneEmpty(storehouse.getStoreHouseName())) {
			hql.append(" and storeHouseName like '%"+storehouse.getStoreHouseName()+"%'  ");
		}
		if(StringUtils.isNoneEmpty(storehouse.getStoreHouseCode())) {
			hql.append(" and storeHouseCode like '%"+storehouse.getStoreHouseCode()+"%'  ");
		}

		@SuppressWarnings("unchecked")
		List<StorehouseDo> list= (List<StorehouseDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
