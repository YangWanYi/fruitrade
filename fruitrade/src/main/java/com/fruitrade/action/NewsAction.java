package com.fruitrade.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.NewsDo;
import com.fruitrade.service.NewsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class NewsAction implements Action, ModelDriven<NewsDo> {

	@Autowired
	private NewsService newsService;

	private NewsDo newsData;
	private NewsDo frontNewsData;
	private List<NewsDo> rows;
	private int total;
	private String ids;
	

	@Override
	public NewsDo getModel() {
		this.newsData = new NewsDo();
		return this.newsData;
	}
	

	/**
	 * 去新闻详情页
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		return SUCCESS;
	}

	/**
	 *新增或编辑新闻信息
	 * 
	 * @return
	 */
	public String saveOrUpdateNews() {
		try {
			if (this.newsData.getId() != null) { // 存在主键 走编辑 
				this.newsService.updateNews(this.newsData);
			} else { // 不存在主键 走新增
				this.newsData.setCreateTime(new Date());
				this.newsService.insertNews(this.newsData);
			}
			return NONE;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
	 *根据主键批量删除新闻
	 * 
	 * @return
	 */
	public String deleteNews() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				NewsDo News = new NewsDo();
				for (int i = 0; i < idsArray.length; i++) {
					if(StringUtils.isNoneEmpty(idsArray[i])) {
						News.setId(Integer.parseInt(idsArray[i]));
						this.newsService.deleteNews(News);
					}
				}
				return NONE;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
	 * 去编辑新闻页面
	 * @return
	 */
	public String toUpdateNewsPage() {
		try {
			if (this.newsData.getId() != null) {
				this.newsData = this.newsService.selectNewsById(this.newsData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
	 * 去新闻详情页面
	 * @return
	 */
	public String toNewsDetailPage() {
		return SUCCESS;
//		try {
//			if (this.newsData.getId() != null) {
//				this.frontNewsData = this.newsService.selectNewsById(this.newsData.getId());
//				return SUCCESS;
//			} else {
//				return ERROR;
//			}
//		} catch (Exception e) {
//			return ERROR;
//		}
	}
	
	/**
	 * 查询所有新闻
	 * 
	 * @param request
	 * @return
	 */
	public String listNews() {
		try {
			List<NewsDo> NewsList = this.newsService.listNews(this.newsData);
			this.rows = NewsList;
			this.total = NewsList.size();
			return NONE;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	

	/**
	 * 查根ID据询水果
	 * 
	 * @param request
	 * @return
	 */
	public String getNewsById() {
		try {
			if(this.newsData.getId() != null) {
				this.frontNewsData = this.newsService.selectNewsById(this.newsData.getId());
				return NONE;
			}else {
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public List<NewsDo> getRows() {
		return rows;
	}

	public void setRows(List<NewsDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public NewsDo getNewsData() {
		return newsData;
	}


	public void setNewsData(NewsDo newsData) {
		this.newsData = newsData;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public NewsDo getFrontNewsData() {
		return frontNewsData;
	}


	public void setFrontNewsData(NewsDo frontNewsData) {
		this.frontNewsData = frontNewsData;
	}
	
}