package com.fruitrade.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.FruitClassifyDo;
import com.fruitrade.domain.FruitDo;
import com.fruitrade.domain.RetailDo;
import com.fruitrade.domain.SupplyVsFruitDo;
import com.fruitrade.service.FruitClassifyService;
import com.fruitrade.service.FruitService;
import com.fruitrade.service.RetailService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class RetailAction implements Action, ModelDriven<RetailDo> {

	@Autowired
	private RetailService retailService;
	@Autowired
	private FruitClassifyService fruitClassifyService;
	@Autowired
	private FruitService fruitService;

	private RetailDo retailData;
	private List<Map<String, Object>> rows;
	private int total;
	private String ids;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	@Override
	public RetailDo getModel() {
		this.retailData = new RetailDo();
		return this.retailData;
	}

	/**
	 * 加载数据列表
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<Object> retailList = this.retailService.listRetail(this.retailData);
			if (retailList.size() > 0) {
				FruitClassifyDo fruitClassify;
				FruitDo fruit;
				Object[] obj;
				SupplyVsFruitDo supplyVsFruit;
				RetailDo retail;
				for (int i = 0; i < retailList.size(); i++) {
					obj = (Object[]) retailList.get(i);
					Map<String, Object> map = new HashMap<String, Object>();
					retail = (RetailDo) obj[0];
					supplyVsFruit = (SupplyVsFruitDo) obj[1];
					if (supplyVsFruit != null) {
						if (supplyVsFruit.getClassifyId() != null) {
							fruitClassify = fruitClassifyService.selectFruitClassifyById(supplyVsFruit.getClassifyId());
							if (fruitClassify != null) {
								supplyVsFruit.setClassifyName(fruitClassify.getClassifyName());
							}
						}
						if (supplyVsFruit.getFruitId() != null) {
							fruit = fruitService.selectFruitById(supplyVsFruit.getFruitId());
							if (fruit != null) {
								supplyVsFruit.setFruitName(fruit.getFruitName());
							}
						}
						map.put("vid", supplyVsFruit.getId());
						map.put("classifyName", supplyVsFruit.getClassifyName());
						map.put("fruitName", supplyVsFruit.getFruitName());
						map.put("place", supplyVsFruit.getPlace());
						map.put("price", supplyVsFruit.getPrice());
						map.put("unit", supplyVsFruit.getUnit());
					}
					map.put("id", retail.getId());
					map.put("retailCode", retail.getRetailCode());
					map.put("retailName", retail.getRetailName());
					map.put("contactNum", retail.getContactNum());
					map.put("address", retail.getAddress());
					map.put("month", retail.getMonth());
					list.add(map);
				}
			}
			this.rows = list;
			this.total = list.size();
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
	public String saveOrUpdateRetail() {
		try {
			if (this.retailData.getId() != null) { // 存在主键 走编辑
				this.retailService.updateRetail(this.retailData);
			} else { // 不存在主键 走新增
				this.retailService.insertRetail(this.retailData);
			}
			return NONE;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 根据主键批量删除数据
	 * 
	 * @return
	 */
	public String deleteRetail() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				RetailDo Retail = new RetailDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						Retail.setId(Integer.parseInt(idsArray[i]));
						this.retailService.deleteRetail(Retail);
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
	public String toUpdateRetailPage() {
		try {
			if (this.retailData.getId() != null) {
				this.retailData = this.retailService.selectRetailById(this.retailData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public RetailDo getRetailData() {
		return retailData;
	}

	public void setRetailData(RetailDo retailData) {
		this.retailData = retailData;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}