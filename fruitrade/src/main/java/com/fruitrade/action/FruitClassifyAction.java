package com.fruitrade.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.FruitClassifyDo;
import com.fruitrade.service.FruitClassifyService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class FruitClassifyAction implements Action, ModelDriven<FruitClassifyDo> {

	@Autowired
	private FruitClassifyService fruitClassifyService;

	private FruitClassifyDo fruitClassifyData;
	private List<FruitClassifyDo> rows;
	private int total;
	private String ids;

	@Override
	public FruitClassifyDo getModel() {
		this.fruitClassifyData = new FruitClassifyDo();
		return this.fruitClassifyData;
	}

	/**
	 * 加载数据列表
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<FruitClassifyDo> fruitClassifyList = this.fruitClassifyService.listFruitClassify(this.fruitClassifyData);
			this.rows = fruitClassifyList;
			this.total = fruitClassifyList.size();
			return NONE;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 新增或编辑数据
	 * 
	 * @return
	 */
	public String saveOrUpdateFruitKind() {
		try {
			if (this.fruitClassifyData.getId() != null) { // 存在主键 走编辑
				this.fruitClassifyService.updateFruitClassify(this.fruitClassifyData);
			} else { // 不存在主键 走新增
				this.fruitClassifyService.insertFruitClassify(this.fruitClassifyData);
			}
			return NONE;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 根据主键批量删除数据
	 * 
	 * @return
	 */
	public String deleteFruitClassify() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				FruitClassifyDo FruitClassify = new FruitClassifyDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						FruitClassify.setId(Integer.parseInt(idsArray[i]));
						this.fruitClassifyService.deleteFruitClassify(FruitClassify);
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
	 * 去编辑页面
	 * 
	 * @return
	 */
	public String toUpdateFruitClassifyPage() {
		try {
			if (this.fruitClassifyData.getId() != null) {
				this.fruitClassifyData = this.fruitClassifyService.selectFruitClassifyById(this.fruitClassifyData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	public List<FruitClassifyDo> getRows() {
		return rows;
	}

	public void setRows(List<FruitClassifyDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public FruitClassifyDo getFruitClassifyData() {
		return fruitClassifyData;
	}

	public void setFruitClassifyData(FruitClassifyDo fruitClassifyData) {
		this.fruitClassifyData = fruitClassifyData;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}