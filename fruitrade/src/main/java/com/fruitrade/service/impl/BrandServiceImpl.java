package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.BrandDao;
import com.fruitrade.domain.BrandDo;
import com.fruitrade.service.BrandService;

@Transactional
@Service("BrandService")
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandDao brandDao;

	@Override
	public void insertBrand(BrandDo brand) {
		brandDao.insertBrand(brand);		
	}

	@Override
	public void updateBrand(BrandDo brand) throws Exception{
		brandDao.updateBrand(brand);
	}

	@Override
	public void deleteBrand(BrandDo brand) {
		brandDao.deleteBrand(brand);		
	}

	@Override
	public BrandDo selectBrandById(int brandId) {
		return brandDao.selectBrandById(brandId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param brand
	 * @return List<Object>
	 */
	@Override
	public List<Object> listBrand(BrandDo brand) throws Exception{
		return brandDao.listBrand(brand);
	}

}
