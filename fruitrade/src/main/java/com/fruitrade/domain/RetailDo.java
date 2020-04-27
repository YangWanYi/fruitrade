package com.fruitrade.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 散户实体类
 */
@Entity
@Table(name="RETAIL")//对应数据库的表名
public class RetailDo implements Serializable {
	
	private static final long serialVersionUID = -2385246354465611860L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // strategy=GenerationType.IDENTITY 自增长
	@Column(name = "ID") // 加上这个注解，这个id的含义就是告诉Hibernate，我这个id字段和数据库里面的字段是对应的，另外一个含义就是在正向工程当中，我有个user，表里面有个id字段。
	private Integer id;

	/**
	 * 散户编号
	 */
	@Column(name = "RETAILCODE")
	private String retailCode;
	
	/**
	 * 姓名
	 */
	@Column(name = "RETAILNAME")
	private String retailName;
	
	/**
	 * 联系电话
	 */
	@Column(name = "CONTACTNUM")
	private String contactNum;
	
	/**
	 *详细地址
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	/**
	 *应季月份
	 */
	@Column(name = "MONTH")
	private String month;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRetailCode() {
		return retailCode;
	}

	public void setRetailCode(String retailCode) {
		this.retailCode = retailCode;
	}

	public String getRetailName() {
		return retailName;
	}

	public void setRetailName(String retailName) {
		this.retailName = retailName;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	
	
}
