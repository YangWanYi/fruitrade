package com.fruitrade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitrade.dao.NewsDao;
import com.fruitrade.domain.NewsDo;
import com.fruitrade.service.NewsService;

@Transactional
@Service("NewsService")
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsDao newsDao;

	@Override
	public void insertNews(NewsDo news) {
		newsDao.insertNews(news);		
	}

	@Override
	public void updateNews(NewsDo news) throws Exception{
		newsDao.updateNews(news);
	}

	@Override
	public void deleteNews(NewsDo news) {
		newsDao.deleteNews(news);		
	}

	@Override
	public NewsDo selectNewsById(int newsId) {
		return newsDao.selectNewsById(newsId);
	}

	/**
	 * 根据条件查询所有数据
	 * @param news
	 * @return List<NewsDo>
	 */
	@Override
	public List<NewsDo> listNews(NewsDo news) throws Exception{
		return newsDao.listNews(news);
	}

}
