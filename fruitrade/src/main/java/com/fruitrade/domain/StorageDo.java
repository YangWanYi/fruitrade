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
 * 仓库子表货架实体类
 */
@Entity
@Table(name="STORAGE")//对应数据库的表名
public class StorageDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID") 
	private Integer id;
	
	/**
	 * 货架编号
	 */
	@Column(name = "STORAGECODE") 
	private String storageCode;


	/**
	 * 仓库ID 一个仓库存在多个货架
	 */
	@Column(name = "STOREHOUSEID")
	private Integer storeHouseId;

	/**
	 * 供应商和水果关联表ID
	 */
	@Column(name = "SUPPLYVSFRUITID")
	private Integer supplyVsFruitId;
	
	/**
	 * 存货
	 */
	@Column(name = "INVENTORY")
	private Double inventory;
	
	@Transient
	private String storeHouse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStorageCode() {
		return storageCode;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}

	public Integer getStoreHouseId() {
		return storeHouseId;
	}

	public void setStoreHouseId(Integer storeHouseId) {
		this.storeHouseId = storeHouseId;
	}

	public Double getInventory() {
		return inventory;
	}

	public void setInventory(Double inventory) {
		this.inventory = inventory;
	}

	public String getStoreHouse() {
		return storeHouse;
	}

	public void setStoreHouse(String storeHouse) {
		this.storeHouse = storeHouse;
	}

	public Integer getSupplyVsFruitId() {
		return supplyVsFruitId;
	}

	public void setSupplyVsFruitId(Integer supplyVsFruitId) {
		this.supplyVsFruitId = supplyVsFruitId;
	}
	
	
}
