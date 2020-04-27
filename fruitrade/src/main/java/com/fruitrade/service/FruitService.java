package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.FruitDo;

public interface FruitService {
	
	/**
	 * 增加
	 * 
	 * @param fruit
	 */
	void insertFruit(FruitDo fruit);

	/**
	 * 更新
	 * 
	 * @param fruit
	 * @throws Exception 
	 */
	void updateFruit(FruitDo fruit) throws Exception;

	/**
	 * 删除
	 * 
	 * @param fruit
	 */
	void deleteFruit(FruitDo fruit);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param fruitId
	 * @return FruitDo
	 */
	FruitDo selectFruitById(int fruitId);

	/**
	 * 根据条件查询所有数据
	 * @param fruit
	 * @return List<FruitDo>
	 * @throws Exception 
	 */
	public List<FruitDo> listFruit(FruitDo fruit) throws Exception;
}
