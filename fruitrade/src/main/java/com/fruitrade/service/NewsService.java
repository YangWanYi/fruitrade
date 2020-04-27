package com.fruitrade.service;

import java.util.List;

import com.fruitrade.domain.NewsDo;

public interface NewsService {
	
	/**
	 * 增加
	 * 
	 * @param news
	 */
	void insertNews(NewsDo news);

	/**
	 * 更新
	 * 
	 * @param news
	 * @throws Exception 
	 */
	void updateNews(NewsDo news) throws Exception;

	/**
	 * 删除
	 * 
	 * @param news
	 */
	void deleteNews(NewsDo news);

	/**
	 * 根据主键去查询数据
	 * 
	 * @param newsId
	 * @return NewsDo
	 */
	NewsDo selectNewsById(int newsId);

	/**
	 * 根据条件查询所有数据
	 * @param news
	 * @return List<NewsDo>
	 * @throws Exception 
	 */
	public List<NewsDo> listNews(NewsDo news) throws Exception;
}
