package com.fruitrade.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fruitrade.dao.NewsDao;
import com.fruitrade.domain.NewsDo;

@Repository
public class NewsDaoImpl extends HibernateDaoSupport  implements NewsDao {
	
	@Autowired
	public void setMyFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void insertNews(NewsDo news) {
		super.getHibernateTemplate().save(news);
	}

	@Override
	public void updateNews(NewsDo news) {
		super.getHibernateTemplate().update(news);
	}

	@Override
	public void deleteNews(NewsDo news) {
		super.getHibernateTemplate().delete(news);
	}

	@Override
	public NewsDo selectNewsById(int newsId) {
		return super.getHibernateTemplate().get(NewsDo.class, newsId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param News
	 * @return List<NewsDo>
	 */
	@Override
	public List<NewsDo> listNews(NewsDo news) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from NewsDo where 1=1");
//		if(StringUtils.isNoneEmpty(News.getNewsName())) {
//			hql.append(" and NewsNAME like '%"+News.getNewsName()+"%'  ");
//		}
//		if(News.getRoleId() != null) {
//			hql.append(" and ROLETYPE = '"+News.getRoleId()+"'  ");
//		}
//		if(StringUtils.isNoneEmpty(News.getGender())) {
//			hql.append(" and GENDER = '"+News.getGender()+"'  ");
//		}
//		if(StringUtils.isNoneEmpty(News.getPhoneNum())) {
//			hql.append(" and PHONENUM like '%"+News.getPhoneNum()+"%'  ");
//		}

		@SuppressWarnings("unchecked")
		List<NewsDo> list= (List<NewsDo>) super.getHibernateTemplate().find(hql.toString());
		return list;
	}

}
