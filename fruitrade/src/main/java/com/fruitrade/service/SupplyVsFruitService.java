package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.SupplyVsFruitDo;

public interface SupplyVsFruitService {
	
	/**
	 * 增加
	 * 
	 * @param supplyVsFruit
	 */
	void insertSupplyVsFruit(SupplyVsFruitDo supplyVsFruit);

	/**
	 * 更新
	 * 
	 * @param supplyVsFruit
	 * @throws Exception 
	 */
	void updateSupplyVsFruit(SupplyVsFruitDo supplyVsFruit) throws Exception;

	/**
	 * 删除
	 * 
	 * @param supplyVsFruit
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
	 * 根据条件查询所有数据
	 * @param supplyVsFruit
	 * @return List<SupplyVsFruitDo>
	 * @throws Exception 
	 */
	public List<SupplyVsFruitDo> listSupplyVsFruit(SupplyVsFruitDo supplyVsFruit) throws Exception;

}
