package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.OutputStoreDo;

public interface OutputStoreDao {

	/**
	 * 增加
	 * 
	 * @param outputStore
	 */
	void insertOutputStore(OutputStoreDo outputStore);

	/**
	 * 更新
	 * 
	 * @param outputStore
	 */
	void updateOutputStore(OutputStoreDo outputStore);

	/**
	 * 删除
	 * 
	 * @param outputStore
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
	 * 根据条件查询所有
	 * @param outputStore
	 * @return List<OutputStoreDo>
	 */
	List<OutputStoreDo> listOutputStore(OutputStoreDo outputStore);
	
	List<Object> listWreckSumByDay();
}
