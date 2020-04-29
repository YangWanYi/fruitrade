package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.OutputStoreDao;
import com.fruitrade.domain.OutputStoreDo;
import com.fruitrade.service.OutputStoreService;

@Transactional
@Service("OutputStoreService")
public class OutputStoreServiceImpl implements OutputStoreService {
	
	@Autowired
	private OutputStoreDao OutputStoreDao;

	@Override
	public void insertOutputStore(OutputStoreDo outputStore) {
		OutputStoreDao.insertOutputStore(outputStore);		
	}

	@Override
	public void updateOutputStore(OutputStoreDo outputStore) throws Exception{
		OutputStoreDao.updateOutputStore(outputStore);
	}

	@Override
	public void deleteOutputStore(OutputStoreDo outputStore) {
		OutputStoreDao.deleteOutputStore(outputStore);		
	}

	@Override
	public OutputStoreDo selectOutputStoreById(int outputStoreId) {
		return OutputStoreDao.selectOutputStoreById(outputStoreId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param outputStore
	 * @return List<OutputStoreDo>
	 */
	@Override
	public List<OutputStoreDo> listOutputStore(OutputStoreDo outputStore) throws Exception{
		return OutputStoreDao.listOutputStore(outputStore);
	}

	@Override
	public List<Object> listWreckSumByDay() {
		return OutputStoreDao.listWreckSumByDay();
	}

}
