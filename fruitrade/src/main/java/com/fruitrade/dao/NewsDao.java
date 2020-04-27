package com.fruitrade.dao;

import java.util.List;

import com.fruitrade.domain.NewsDo;

public interface NewsDao {

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
	 */
	void updateNews(NewsDo news);

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
	 * @return newsDo
	 */
	NewsDo selectNewsById(int newsId);

	/**
	 * 根据条件查询所有
	 * @param news
	 * @return List<NewsDo>
	 */
	List<NewsDo> listNews(NewsDo news);
}
