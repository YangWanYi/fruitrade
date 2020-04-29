package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.OutputStoreDo;

public interface OutputStoreService {
	
	/**
	 * 增加
	 * 
	 * @param OutputStore
	 */
	void insertOutputStore(OutputStoreDo outputStore);

	/**
	 * 更新
	 * 
	 * @param OutputStore
	 * @throws Exception 
	 */
	void updateOutputStore(OutputStoreDo outputStore) throws Exception;

	/**
	 * 删除
	 * 
	 * @param OutputStore
	 */
	void deleteOutputStore(OutputStoreDo outputStore);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param outputStoreId
	 * @return OutputStoreDo
	 */
	OutputStoreDo selectOutputStoreById(int outputStoreId);

	/**
	 * 根据条件查询所有数据
	 * @param outputStore
	 * @return List<OutputStoreDo>
	 * @throws Exception 
	 */
	public List<OutputStoreDo> listOutputStore(OutputStoreDo outputStore) throws Exception;

	List<Object> listWreckSumByDay();
}
