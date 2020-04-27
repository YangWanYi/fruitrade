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
 * 仓库实体类
 */
@Entity
@Table(name="storehouse")//对应数据库的表名
public class StorehouseDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID") 
	private Integer id;
	
	/**
	 *仓库编号
	 */
	@Column(name = "STOREHOUSECODE")
	private String storeHouseCode;
	
	/**
	 *负责人 
	 */
	@Column(name = "PRINCIPAL")
	private String principal;

	/**
	 * 仓库联系方式
	 */
	@Column(name = "contactNum")
	private String contactNum;
	
	/**
	 * 仓库地址
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	/**
	 * 创建人
	 */
	@Column(name = "CREATEID")
	private Integer createId;
	
	/**
	 * 创建时间
	 */
	@Column(name = "CREATETIME")
	private Date createTime;
	
	/**
	 * 修改人
	 */
	@Column(name = "UPDATEID")
	private Integer updateId;

	/**
	 * 修改时间
	 */
	@Column(name = "UPDATETIME")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoreHouseCode() {
		return storeHouseCode;
	}

	public void setStoreHouseCode(String storeHouseCode) {
		this.storeHouseCode = storeHouseCode;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
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

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
