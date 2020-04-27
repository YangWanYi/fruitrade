package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.RetailDao;
import com.fruitrade.domain.RetailDo;
import com.fruitrade.service.RetailService;

@Transactional
@Service("RetailService")
public class RetailServiceImpl implements RetailService {
	
	@Autowired
	private RetailDao retailDao;

	@Override
	public void insertRetail(RetailDo retail) {
		retailDao.insertRetail(retail);		
	}

	@Override
	public void updateRetail(RetailDo retail) throws Exception{
		retailDao.updateRetail(retail);
	}

	@Override
	public void deleteRetail(RetailDo retail) {
		retailDao.deleteRetail(retail);		
	}

	@Override
	public RetailDo selectRetailById(int retailId) {
		return retailDao.selectRetailById(retailId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param retail
	 * @return List<Object>
	 */
	@Override
	public List<Object> listRetail(RetailDo retail) throws Exception{
		return retailDao.listRetail(retail);
	}

}
