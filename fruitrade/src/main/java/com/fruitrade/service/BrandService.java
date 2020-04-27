package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.BrandDo;

public interface BrandService {
	
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
	 * @throws Exception 
	 */
	void updateBrand(BrandDo brand) throws Exception;

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
	 * 根据条件查询所有数据
	 * @param brand
	 * @return List<Object>
	 * @throws Exception 
	 */
	public List<Object> listBrand(BrandDo brand) throws Exception;

}
