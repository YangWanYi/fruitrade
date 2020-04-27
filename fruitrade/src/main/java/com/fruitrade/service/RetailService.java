package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.RetailDo;

public interface RetailService {
	
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
	 * @throws Exception 
	 */
	void updateRetail(RetailDo retail) throws Exception;

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
	 * @return RetailDo
	 */
	RetailDo selectRetailById(int retailId);

	/**
	 * 根据条件查询所有数据
	 * @param retail
	 * @return List<Object>
	 * @throws Exception 
	 */
	public List<Object> listRetail(RetailDo retail) throws Exception;
}
