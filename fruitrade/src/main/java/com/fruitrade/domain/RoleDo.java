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
 *角色实体类
 */
@Entity
@Table(name="role")//对应数据库的表名
public class RoleDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // strategy=GenerationType.IDENTITY 自增长
	@Column(name = "ID") // 加上这个注解，这个id的含义就是告诉Hibernate，我这个id字段和数据库里面的字段是对应的，另外一个含义就是在正向工程当中，我有个user，表里面有个id字段。
	private Integer id;

	/**
	 * 角色名称
	 */
	@Column(name = "ROLENAME")
	private String roleName;
	
	/**
	 *创建人id
	 */
	@Column(name = "CREATEID")
	private Integer createId;
	
	/**
	 *创建人姓名
	 */
	@Column(name = "CREATENAME")
	private String createName;
	
	/**
	 *创建时间
	 */
	@Column(name = "CREATETIME")
	private Date createTime;
	

	/**
	 *修改人id
	 */
	@Column(name = "UPDATEID")
	private Integer updateId;
	
	/**
	 *修改人姓名
	 */
	@Column(name = "UPDATENAME")
	private String updateName;
	
	/**
	 *修改时间
	 */
	@Column(name = "UPDATETIME")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
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

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
