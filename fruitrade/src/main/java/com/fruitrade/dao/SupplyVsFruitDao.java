package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.SupplyVsFruitDo;

public interface SupplyVsFruitDao {

	/**
	 * 增加
	 * 
	 * @param SupplyVsFruit
	 */
	void insertSupplyVsFruit(SupplyVsFruitDo supplyVsFruit);

	/**
	 * 更新
	 * 
	 * @param SupplyVsFruit
	 */
	void updateSupplyVsFruit(SupplyVsFruitDo supplyVsFruit);

	/**
	 * 删除
	 * 
	 * @param SupplyVsFruit
	 */
	void deleteSupplyVsFruit(SupplyVsFruitDo supplyVsFruit);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param supplyVsFruitId
	 * @return SupplyVsFruitDo
	 */
	SupplyVsFruitDo selectSupplyVsFruitById(int supplyVsFruitId);

	/**
	 * 根据条件查询所有
	 * @param supplyVsFruit
	 * @return List<SupplyVsFruitDo>
	 */
	List<SupplyVsFruitDo> listSupplyVsFruit(SupplyVsFruitDo supplyVsFruit);
}
