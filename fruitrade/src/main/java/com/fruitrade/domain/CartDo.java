package com.fruitrade.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 购物车实体类
 */
@Entity
@Table(name="PURCHASECART")//对应数据库的表名
public class CartDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID") 
	private Integer id;

	/**
	 * 关联用户
	 */
	@Column(name = "USERID")
	private Integer userId;
	
	/**
	 * 水果进货id
	 */
	@Column(name = "purchaseId")
	private Integer purchaseId;
	
	/**
	 * 水果购买数量
	 */
	@Column(name = "PURCHASENUM")
	private Integer purchaseNum;
	
	/**
	 * 订单ID
	 */
	@Column(name = "ORDERID")
	private Integer orderId;
	
	/**
	 * 水果ID
	 */
	@Column(name = "FRUITID")
	private Integer fruitId;
	
	@Column(name = "FRUITNAME")
	private String fruitName;
	
	@Transient
	private Double salePrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(Integer purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getFruitId() {
		return fruitId;
	}

	public void setFruitId(Integer fruitId) {
		this.fruitId = fruitId;
	}
	
}
