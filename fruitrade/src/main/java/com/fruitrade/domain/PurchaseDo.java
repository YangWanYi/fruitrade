package com.fruitrade.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 进货实体类
 */
@Entity
@Table(name="PURCHASE")//对应数据库的表名
public class PurchaseDo implements Serializable {
	
	private static final long serialVersionUID = -2385246354465611860L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // strategy=GenerationType.IDENTITY 自增长
	@Column(name = "ID") // 加上这个注解，这个id的含义就是告诉Hibernate，我这个id字段和数据库里面的字段是对应的，另外一个含义就是在正向工程当中，我有个user，表里面有个id字段。
	private Integer id;
	
	/**
	 * 供应商和水果的关联表ID
	 */
	@Column(name = "SUPPLYVSFRUITID")
	private Integer supplyVsFruitId;
	
	/**
	 * 进货数量
	 */
	@Column(name = "PURCHASENUM")
	private Double purchaseNum;
	
	/**
	 * 售价
	 */
	@Column(name = "SALEPRICE")
	private Double salePrice;
	
	/**
	 * 仓库ID
	 */
	@Column(name = "STOREHOUSEID")
	private Integer storeHouseId;
	
	/**
	 * 货架ID
	 */
	@Column(name = "STORAGEID")
	private Integer storageId;
	
	/**
	 * 进货人
	 */
	@Column(name = "CREATEID")
	private Integer createId;
	
	/**
	 * 进货时间
	 */
	@Column(name = "CREATETIME")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSupplyVsFruitId() {
		return supplyVsFruitId;
	}

	public void setSupplyVsFruitId(Integer supplyVsFruitId) {
		this.supplyVsFruitId = supplyVsFruitId;
	}

	public Double getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(Double purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public Integer getStoreHouseId() {
		return storeHouseId;
	}

	public void setStoreHouseId(Integer storeHouseId) {
		this.storeHouseId = storeHouseId;
	}

	public Integer getStorageId() {
		return storageId;
	}

	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
}
