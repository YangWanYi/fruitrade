package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.StorehouseDao;
import com.fruitrade.domain.StorehouseDo;
import com.fruitrade.service.StorehouseService;

@Transactional
@Service("StorehouseService")
public class StorehouseServiceImpl implements StorehouseService {
	
	@Autowired
	private StorehouseDao storehouseDao;

	@Override
	public void insertStorehouse(StorehouseDo storehouse) {
		storehouseDao.insertStorehouse(storehouse);		
	}

	@Override
	public void updateStorehouse(StorehouseDo storehouse) throws Exception{
		storehouseDao.updateStorehouse(storehouse);
	}

	@Override
	public void deleteStorehouse(StorehouseDo storehouse) {
		storehouseDao.deleteStorehouse(storehouse);		
	}

	@Override
	public StorehouseDo selectStorehouseById(int storehouseId) {
		return storehouseDao.selectStorehouseById(storehouseId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param storehouse
	 * @return List<StorehouseDo>
	 */
	@Override
	public List<StorehouseDo> listStorehouse(StorehouseDo storehouse) throws Exception{
		return storehouseDao.listStorehouse(storehouse);
	}

}
