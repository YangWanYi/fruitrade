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
 * 出库管理实体类
 */
@Entity
@Table(name="OUTPUTSTORE")//对应数据库的表名
public class OutputStoreDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID") 
	private Integer id;
	
	/**
	 *仓库ID
	 */
	@Column(name = "STOREID")
	private Integer storeId;
	
	/**
	 *仓库名
	 */
	@Column(name = "STORENAME")
	private String storeName;
	
	/**
	 *仓库编号
	 */
	@Column(name = "STORECODE")
	private String storeCode;
	
	/**
	 *仓库位置 
	 */
	@Column(name = "STOREPLACE")
	private String storePlace;
	
	/**
	 *联系人名称
	 */
	@Column(name = "LINKMAN")
	private String linkman;

	/**
	 * 联系人电话
	 */
	@Column(name = "contactNum")
	private String contactNum;
	
	/**
	 * 水果ID
	 */
	@Column(name = "FRUITID")
	private Integer fruitId;
	
	/**
	 * 水果出货量  
	 */
	@Column(name = "OUTPUTNUM")
	private Integer outPutNum;
	
	/**
	 * 水果折损量
	 */
	@Column(name = "WRECKNUM")
	private Double wreckNum;
	
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
	
	@Column(name = "FRUITNAME")
	private String fruitName;
	
	@Column(name = "UNIT")
	private String unit;
	
	private Double wreckSum;
	private String dayStr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStorePlace() {
		return storePlace;
	}

	public void setStorePlace(String storePlace) {
		this.storePlace = storePlace;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public Integer getFruitId() {
		return fruitId;
	}

	public void setFruitId(Integer fruitId) {
		this.fruitId = fruitId;
	}

	public Integer getOutPutNum() {
		return outPutNum;
	}

	public void setOutPutNum(Integer outPutNum) {
		this.outPutNum = outPutNum;
	}

	public Double getWreckNum() {
		return wreckNum;
	}

	public void setWreckNum(Double wreckNum) {
		this.wreckNum = wreckNum;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public Double getWreckSum() {
		return wreckSum;
	}

	public void setWreckSum(Double wreckSum) {
		this.wreckSum = wreckSum;
	}

	public String getDayStr() {
		return dayStr;
	}

	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}
	
}
