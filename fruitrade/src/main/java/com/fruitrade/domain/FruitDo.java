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
 * 水果实体类
 */
@Entity
@Table(name="fruit")//对应数据库的表名
public class FruitDo implements Serializable {
	
	private static final long serialVersionUID = -2385246354465611860L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // strategy=GenerationType.IDENTITY 自增长
	@Column(name = "ID") // 加上这个注解，这个id的含义就是告诉Hibernate，我这个id字段和数据库里面的字段是对应的，另外一个含义就是在正向工程当中，我有个user，表里面有个id字段。
	private Integer id;
	
	/**
	 * 水果种类ID
	 */
	@Column(name = "FRUITCLASSIFYID")
	private Integer fruitClassifyID;

	/**
	 * 水果名称
	 */
	@Column(name = "FRUITNAME")
	private String fruitName;
	
	/**
	 * 图片信息
	 */
	@Column(name = "PIC")
	private String pic;
	
	@Transient
	private String fruitClassifyName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFruitClassifyID() {
		return fruitClassifyID;
	}

	public void setFruitClassifyID(Integer fruitClassifyID) {
		this.fruitClassifyID = fruitClassifyID;
	}

	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getFruitClassifyName() {
		return fruitClassifyName;
	}

	public void setFruitClassifyName(String fruitClassifyName) {
		this.fruitClassifyName = fruitClassifyName;
	}
	
}
