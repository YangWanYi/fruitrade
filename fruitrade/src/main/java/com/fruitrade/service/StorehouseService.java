package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.StorehouseDo;

public interface StorehouseService {
	
	/**
	 * 增加
	 * 
	 * @param Storehouse
	 */
	void insertStorehouse(StorehouseDo Storehouse);

	/**
	 * 更新
	 * 
	 * @param storehouse
	 * @throws Exception 
	 */
	void updateStorehouse(StorehouseDo storehouse) throws Exception;

	/**
	 * 删除
	 * 
	 * @param storehouse
	 */
	void deleteStorehouse(StorehouseDo storehouse);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param storehouseId
	 * @return StorehouseDo
	 */
	StorehouseDo selectStorehouseById(int storehouseId);

	/**
	 * 根据条件查询所有数据
	 * @param storehouse
	 * @return List<StorehouseDo>
	 * @throws Exception 
	 */
	public List<StorehouseDo> listStorehouse(StorehouseDo storehouse) throws Exception;
}
