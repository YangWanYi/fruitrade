package com.fruitrade.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.UserDao;
import com.fruitrade.domain.UserDo;

@Repository
public class UserDaoImpl extends HibernateDaoSupport  implements UserDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertUser(UserDo user) {
		super.getHibernateTemplate().save(user);
	}

	@Override
	public void updateUser(UserDo user) {
		super.getHibernateTemplate().update(user);
	}

	@Override
	public void deleteUser(UserDo user) {
		super.getHibernateTemplate().delete(user);
	}

	@Override
	public UserDo selectUserById(int userId) {
		return super.getHibernateTemplate().get(UserDo.class, userId);
	}

	/**
	 * 用户登录 根据账号和密码查询对象
	 */
	@Override
	public UserDo loginUser(UserDo user) {
		UserDo userDo = null;
		List<UserDo> list = super.getHibernateTemplate().findByExample(user);
		if (list.size() > 0) { // 同一个账号只可能存在一个 所以取第一条数据即可
			userDo = list.get(0);
		}
		return userDo;
	}

	/**
	 * 根据条件查询所有用户
	 * @param user
	 * @return List<UserDo>
	 */
	@Override
	public List<UserDo> listUser(UserDo user) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from UserDo where 1=1");
		if(StringUtils.isNoneEmpty(user.getUserName())) {
			hql.append(" and USERNAME like '%"+user.getUserName()+"%'  ");
		}
		if(user.getRoleId() != null) {
			hql.append(" and ROLETYPE = '"+user.getRoleId()+"'  ");
		}
		if(StringUtils.isNoneEmpty(user.getGender())) {
			hql.append(" and GENDER = '"+user.getGender()+"'  ");
		}
		if(StringUtils.isNoneEmpty(user.getPhoneNum())) {
			hql.append(" and PHONENUM like '%"+user.getPhoneNum()+"%'  ");
		}

		@SuppressWarnings("unchecked")
		List<UserDo> list= (List<UserDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
