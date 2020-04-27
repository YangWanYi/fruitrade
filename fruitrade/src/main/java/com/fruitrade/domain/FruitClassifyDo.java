package com.fruitrade.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 水果种类实体类
 */
@Entity
@Table(name="FRUITCLASSIFY")//对应数据库的表名
public class FruitClassifyDo implements Serializable {
	
	private static final long serialVersionUID = -2385246354465611860L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID") 
	private Integer id;
	
	/**
	 * 水果种类编号
	 */
	@Column(name = "CLASSIFYCODE")
	private String classifyCode;
	
	/**
	 * 水果种类名称
	 */
	@Column(name = "classifyName")
	private String classifyName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getClassifyCode() {
		return classifyCode;
	}

	public void setClassifyCode(String classifyCode) {
		this.classifyCode = classifyCode;
	}

}
