package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.FruitClassifyDao;
import com.fruitrade.domain.FruitClassifyDo;
import com.fruitrade.service.FruitClassifyService;

@Transactional
@Service("FruitClassifyService")
public class FruitClassifyServiceImpl implements FruitClassifyService {
	
	@Autowired
	private FruitClassifyDao fruitClassifyDao;

	@Override
	public void insertFruitClassify(FruitClassifyDo fruitClassify) {
		fruitClassifyDao.insertFruitClassify(fruitClassify);		
	}

	@Override
	public void updateFruitClassify(FruitClassifyDo fruitClassify) throws Exception{
		fruitClassifyDao.updateFruitClassify(fruitClassify);
	}

	@Override
	public void deleteFruitClassify(FruitClassifyDo fruitClassify) {
		fruitClassifyDao.deleteFruitClassify(fruitClassify);		
	}

	@Override
	public FruitClassifyDo selectFruitClassifyById(int fruitClassifyId) {
		return fruitClassifyDao.selectFruitClassifyById(fruitClassifyId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param fruitClassify
	 * @return List<FruitClassifyDo>
	 */
	@Override
	public List<FruitClassifyDo> listFruitClassify(FruitClassifyDo fruitClassify) throws Exception{
		return fruitClassifyDao.listFruitClassify(fruitClassify);
	}

}
