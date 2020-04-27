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
 *供应商和水果关联实体类
 */
@Entity
@Table(name="SUPPLYVSFRUIT")//对应数据库的表名
public class SupplyVsFruitDo implements Serializable {
	
	private static final long serialVersionUID = -2385246354465611860L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // strategy=GenerationType.IDENTITY 自增长
	@Column(name = "ID") // 加上这个注解，这个id的含义就是告诉Hibernate，我这个id字段和数据库里面的字段是对应的，另外一个含义就是在正向工程当中，我有个user，表里面有个id字段。
	private Integer id;

	/**
	 * 供应商类型
	 */
	@Column(name = "supplyType")
	private String supplyType;
	
	/**
	 * 供应商ID
	 */
	@Column(name = "SUPPLYID")
	private Integer supplyId;
	
	/**
	 * 水果分类ID
	 */
	@Column(name = "CLASSIFYID")
	private Integer classifyId;
	
	/**
	 * 水果ID
	 */
	@Column(name = "FRUITID")
	private Integer fruitId;
	
	/**
	 * 产地
	 */
	@Column(name = "PLACE")
	private String place;
	
	/**
	 * 批发单位
	 */
	@Column(name = "UNIT")
	private String unit;
	
	/**
	 * 批发价
	 */
	@Column(name = "price")
	private Double price;
	
	@Transient
	private String supplyName;
	
	@Transient
	private String classifyName;

	@Transient
	private String fruitName;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public Integer getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	public Integer getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	public Integer getFruitId() {
		return fruitId;
	}

	public void setFruitId(Integer fruitId) {
		this.fruitId = fruitId;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
