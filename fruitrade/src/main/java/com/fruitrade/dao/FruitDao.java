package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.FruitDo;

public interface FruitDao {

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
	 */
	void updateFruit(FruitDo fruit);

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
	 * @return fruitDo
	 */
	FruitDo selectFruitById(int fruitId);

	/**
	 * 根据条件查询所有
	 * @param fruit
	 * @return List<FruitDo>
	 */
	List<FruitDo> listFruit(FruitDo fruit);
}
