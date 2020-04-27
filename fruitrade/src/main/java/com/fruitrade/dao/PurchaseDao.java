package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.PurchaseDo;

public interface PurchaseDao {

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
	 */
	void updatePurchase(PurchaseDo purchase);

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
	 * @return purchaseDo
	 */
	PurchaseDo selectPurchaseById(int purchaseId);

	/**
	 * 根据条件查询所有
	 * @param purchase
	 * @return List<Object>
	 */
	List<Object> listPurchase(PurchaseDo purchase);
}
