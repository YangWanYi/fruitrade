package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.PurchaseDao;
import com.fruitrade.domain.PurchaseDo;
import com.fruitrade.service.PurchaseService;

@Transactional
@Service("PurchaseService")
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private PurchaseDao purchaseDao;

	@Override
	public void insertPurchase(PurchaseDo purchase) {
		purchaseDao.insertPurchase(purchase);		
	}

	@Override
	public void updatePurchase(PurchaseDo purchase) throws Exception{
		purchaseDao.updatePurchase(purchase);
	}

	@Override
	public void deletePurchase(PurchaseDo purchase) {
		purchaseDao.deletePurchase(purchase);		
	}

	@Override
	public PurchaseDo selectPurchaseById(int purchaseId) {
		return purchaseDao.selectPurchaseById(purchaseId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param purchase
	 * @return List<Object>
	 */
	@Override
	public List<Object> listPurchase(PurchaseDo purchase) throws Exception{
		return purchaseDao.listPurchase(purchase);
	}

}
