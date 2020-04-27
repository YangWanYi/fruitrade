package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.PurchaseDo;

public interface PurchaseService {
	
	/**
	 * 增加
	 * 
	 * @param purchase
	 */
	void insertPurchase(PurchaseDo purchase);

	/**
	 * 更新
	 * 
	 * @param purchase
	 * @throws Exception 
	 */
	void updatePurchase(PurchaseDo purchase) throws Exception;

	/**
	 * 删除
	 * 
	 * @param purchase
	 */
	void deletePurchase(PurchaseDo purchase);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param purchaseId
	 * @return PurchaseDo
	 */
	PurchaseDo selectPurchaseById(int purchaseId);

	/**
	 * 根据条件查询所有数据
	 * @param purchase
	 * @return List<Object>
	 * @throws Exception 
	 */
	public List<Object> listPurchase(PurchaseDo purchase) throws Exception;
}
