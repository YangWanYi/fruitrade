package com.fruitrade.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.StorageDao;
import com.fruitrade.domain.StorageDo;

@Repository
public class StorageDaoImpl extends HibernateDaoSupport  implements StorageDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertStorage(StorageDo storage) {
		super.getHibernateTemplate().save(storage);
	}

	@Override
	public void updateStorage(StorageDo storage) {
		super.getHibernateTemplate().update(storage);
	}

	@Override
	public void deleteStorage(StorageDo storage) {
		super.getHibernateTemplate().delete(storage);
	}

	@Override
	public StorageDo selectStorageById(int storageId) {
		return super.getHibernateTemplate().get(StorageDo.class, storageId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param Storage
	 * @return List<StorageDo>
	 */
	@Override
	public List<StorageDo> listStorage(StorageDo storage) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from StorageDo where 1=1");
		if(storage.getStoreHouseId()!=null) {
			hql.append(" and storeHouseId ="+storage.getStoreHouseId());
		}
		if(StringUtils.isNoneEmpty(storage.getStorageCode())) {
			hql.append(" and storageCode like '%"+storage.getStorageCode()+"%'  ");
		}

		@SuppressWarnings("unchecked")
		List<StorageDo> list= (List<StorageDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
