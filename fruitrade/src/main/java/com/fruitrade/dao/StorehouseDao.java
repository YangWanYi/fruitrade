package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.StorehouseDo;

public interface StorehouseDao {

	/**
	 * 增加
	 * 
	 * @param storehouse
	 */
	void insertStorehouse(StorehouseDo storehouse);

	/**
	 * 更新
	 * 
	 * @param storehouse
	 */
	void updateStorehouse(StorehouseDo storehouse);

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
	 * @return storehouseDo
	 */
	StorehouseDo selectStorehouseById(int storehouseId);

	/**
	 * 根据条件查询所有
	 * @param storehouse
	 * @return List<StorehouseDo>
	 */
	List<StorehouseDo> listStorehouse(StorehouseDo storehouse);
}
