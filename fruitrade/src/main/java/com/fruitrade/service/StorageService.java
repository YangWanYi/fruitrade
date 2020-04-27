package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.StorageDo;

public interface StorageService {
	
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
	 * @throws Exception 
	 */
	void updateStorage(StorageDo storage) throws Exception;

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
	 * @return StorageDo
	 */
	StorageDo selectStorageById(int storageId);

	/**
	 * 根据条件查询所有数据
	 * @param storage
	 * @return List<StorageDo>
	 * @throws Exception 
	 */
	public List<StorageDo> listStorage(StorageDo storage) throws Exception;
}
