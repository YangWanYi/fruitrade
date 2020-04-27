package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.StorageDao;
import com.fruitrade.domain.StorageDo;
import com.fruitrade.service.StorageService;

@Transactional
@Service("StorageService")
public class StorageServiceImpl implements StorageService {
	
	@Autowired
	private StorageDao storageDao;

	@Override
	public void insertStorage(StorageDo storage) {
		storageDao.insertStorage(storage);		
	}

	@Override
	public void updateStorage(StorageDo storage) throws Exception{
		storageDao.updateStorage(storage);
	}

	@Override
	public void deleteStorage(StorageDo storage) {
		storageDao.deleteStorage(storage);		
	}

	@Override
	public StorageDo selectStorageById(int storageId) {
		return storageDao.selectStorageById(storageId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param storage
	 * @return List<StorageDo>
	 */
	@Override
	public List<StorageDo> listStorage(StorageDo storage) throws Exception{
		return storageDao.listStorage(storage);
	}

}
