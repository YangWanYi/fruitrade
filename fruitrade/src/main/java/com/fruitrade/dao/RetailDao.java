package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.RetailDo;

public interface RetailDao {

	/**
	 * 增加
	 * 
	 * @param retail
	 */
	void insertRetail(RetailDo retail);

	/**
	 * 更新
	 * 
	 * @param retail
	 */
	void updateRetail(RetailDo retail);

	/**
	 * 删除
	 * 
	 * @param retail
	 */
	void deleteRetail(RetailDo retail);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param retailId
	 * @return retailDo
	 */
	RetailDo selectRetailById(int retailId);

	/**
	 * 根据条件查询所有
	 * @param retail
	 * @return List<Object>
	 */
	List<Object> listRetail(RetailDo retail);
}
