package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.FruitClassifyDo;

public interface FruitClassifyDao {

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
	 */
	void updateFruitClassify(FruitClassifyDo fruitClassify);

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
	 * @return fruitClassifyDo
	 */
	FruitClassifyDo selectFruitClassifyById(int fruitClassifyId);

	/**
	 * 根据条件查询所有
	 * @param fruitClassify
	 * @return List<FruitClassifyDo>
	 */
	List<FruitClassifyDo> listFruitClassify(FruitClassifyDo fruitClassify);
}
