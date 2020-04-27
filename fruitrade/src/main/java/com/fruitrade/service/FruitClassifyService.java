package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.FruitClassifyDo;

public interface FruitClassifyService {
	
	/**
	 * 增加
	 * 
	 * @param fruitClassify
	 */
	void insertFruitClassify(FruitClassifyDo fruitClassify);

	/**
	 * 更新
	 * 
	 * @param fruitClassify
	 * @throws Exception 
	 */
	void updateFruitClassify(FruitClassifyDo fruitClassify) throws Exception;

	/**
	 * 删除
	 * 
	 * @param fruitClassify
	 */
	void deleteFruitClassify(FruitClassifyDo fruitClassify);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param fruitClassifyId
	 * @return FruitClassifyDo
	 */
	FruitClassifyDo selectFruitClassifyById(int fruitClassifyId);

	/**
	 * 根据条件查询所有数据
	 * @param fruitClassify
	 * @return List<FruitClassifyDo>
	 * @throws Exception 
	 */
	public List<FruitClassifyDo> listFruitClassify(FruitClassifyDo fruitClassify) throws Exception;
}
