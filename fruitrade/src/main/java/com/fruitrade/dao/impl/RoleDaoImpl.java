package com.fruitrade.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.RoleDao;
import com.fruitrade.domain.RoleDo;

@Repository
public class RoleDaoImpl extends HibernateDaoSupport  implements RoleDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertRole(RoleDo role) {
		super.getHibernateTemplate().save(role);
	}

	@Override
	public void updateRole(RoleDo role) {
		super.getHibernateTemplate().update(role);
	}

	@Override
	public void deleteRole(RoleDo role) {
		super.getHibernateTemplate().delete(role);
	}

	@Override
	public RoleDo selectRoleById(int roleId) {
		return super.getHibernateTemplate().get(RoleDo.class, roleId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param Role
	 * @return List<RoleDo>
	 */
	@Override
	public List<RoleDo> listRole(RoleDo role) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from RoleDo where 1=1");
		if(StringUtils.isNoneEmpty(role.getRoleName())) {
			hql.append(" and roleName like '%"+role.getRoleName()+"%'  ");
		}
		@SuppressWarnings("unchecked")
		List<RoleDo> list= (List<RoleDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
