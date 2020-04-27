package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.FruitDao;
import com.fruitrade.domain.FruitDo;
import com.fruitrade.service.FruitService;

@Transactional
@Service("FruitService")
public class FruitServiceImpl implements FruitService {
	
	@Autowired
	private FruitDao fruitDao;

	@Override
	public void insertFruit(FruitDo fruit) {
		fruitDao.insertFruit(fruit);		
	}

	@Override
	public void updateFruit(FruitDo fruit) throws Exception{
		fruitDao.updateFruit(fruit);
	}

	@Override
	public void deleteFruit(FruitDo fruit) {
		fruitDao.deleteFruit(fruit);		
	}

	@Override
	public FruitDo selectFruitById(int fruitId) {
		return fruitDao.selectFruitById(fruitId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param fruit
	 * @return List<FruitDo>
	 */
	@Override
	public List<FruitDo> listFruit(FruitDo fruit) throws Exception{
		return fruitDao.listFruit(fruit);
	}

}
