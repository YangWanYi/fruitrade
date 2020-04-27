package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.SupplyVsFruitDao;
import com.fruitrade.domain.SupplyVsFruitDo;
import com.fruitrade.service.SupplyVsFruitService;

@Transactional
@Service("SupplyVsFruitService")
public class SupplyVsFruitServiceImpl implements SupplyVsFruitService {
	
	@Autowired
	private SupplyVsFruitDao supplyVsFruitDao;

	@Override
	public void insertSupplyVsFruit(SupplyVsFruitDo supplyVsFruit) {
		supplyVsFruitDao.insertSupplyVsFruit(supplyVsFruit);		
	}

	@Override
	public void updateSupplyVsFruit(SupplyVsFruitDo supplyVsFruit) throws Exception{
		supplyVsFruitDao.updateSupplyVsFruit(supplyVsFruit);
	}

	@Override
	public void deleteSupplyVsFruit(SupplyVsFruitDo supplyVsFruit) {
		supplyVsFruitDao.deleteSupplyVsFruit(supplyVsFruit);		
	}

	@Override
	public SupplyVsFruitDo selectSupplyVsFruitById(int supplyVsFruitId) {
		return supplyVsFruitDao.selectSupplyVsFruitById(supplyVsFruitId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param supplyVsFruit
	 * @return List<SupplyVsFruitDo>
	 */
	@Override
	public List<SupplyVsFruitDo> listSupplyVsFruit(SupplyVsFruitDo supplyVsFruit) throws Exception{
		return supplyVsFruitDao.listSupplyVsFruit(supplyVsFruit);
	}

}
