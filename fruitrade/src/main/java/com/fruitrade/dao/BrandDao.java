package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.BrandDo;

public interface BrandDao {

	/**
	 * 增加
	 * 
	 * @param brand
	 */
	void insertBrand(BrandDo brand);

	/**
	 * 更新
	 * 
	 * @param brand
	 */
	void updateBrand(BrandDo brand);

	/**
	 * 删除
	 * 
	 * @param brand
	 */
	void deleteBrand(BrandDo brand);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param brandId
	 * @return BrandDo
	 */
	BrandDo selectBrandById(int brandId);

	/**
	 * 根据条件查询所有
	 * @param brand
	 * @return List<Object>
	 */
	List<Object> listBrand(BrandDo brand);
}
