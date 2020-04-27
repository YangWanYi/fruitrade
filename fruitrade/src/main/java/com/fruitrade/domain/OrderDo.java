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
 * 订单实体类
 */
@Entity
@Table(name="fruitorder")//对应数据库的表名
public class OrderDo implements Serializable {

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
	 * 订单状态 0： 未支付 1：已支付
	 */
	@Column(name = "ORDERSTATE")
	private java.lang.String orderState;
	
	/**
	 * 下单总额
	 */
	@Column(name = "TOTALPRICE")
	private Double totalPrice;
	
	/**
	 * 下单时间
	 */
	@Column(name = "CREATETIME")
	private Date createTime;
	
	/**
	 * 邮费
	 */
	@Column(name = "POSTAGE")
	private Integer postage;

	/**
	 * 预计送达时间
	 */
	@Column(name = "PLANDELIVERYTIME")
	private Date planDeliveryTime;
	
	/**
	 * 付款时间
	 */
	@Column(name = "PAYTIME")
	private Date payTime;

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

	public java.lang.String getOrderState() {
		return orderState;
	}

	public void setOrderState(java.lang.String orderState) {
		this.orderState = orderState;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPostage() {
		return postage;
	}

	public void setPostage(Integer postage) {
		this.postage = postage;
	}

	public Date getPlanDeliveryTime() {
		return planDeliveryTime;
	}

	public void setPlanDeliveryTime(Date planDeliveryTime) {
		this.planDeliveryTime = planDeliveryTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

}
