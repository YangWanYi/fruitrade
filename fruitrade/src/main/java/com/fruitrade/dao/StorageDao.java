package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.StorageDo;

public interface StorageDao {

	/**
	 * 增加
	 * 
	 * @param storage
	 */
	void insertStorage(StorageDo storage);

	/**
	 * 更新
	 * 
	 * @param storage
	 */
	void updateStorage(StorageDo storage);

	/**
	 * 删除
	 * 
	 * @param storage
	 */
	void deleteStorage(StorageDo storage);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param storageId
	 * @return storageDo
	 */
	StorageDo selectStorageById(int storageId);

	/**
	 * 根据条件查询所有
	 * @param storage
	 * @return List<StorageDo>
	 */
	List<StorageDo> listStorage(StorageDo storage);
}
