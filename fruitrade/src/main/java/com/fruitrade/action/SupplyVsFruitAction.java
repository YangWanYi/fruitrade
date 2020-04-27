package com.fruitrade.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.BrandDo;
import com.fruitrade.domain.FruitClassifyDo;
import com.fruitrade.domain.FruitDo;
import com.fruitrade.domain.RetailDo;
import com.fruitrade.domain.SupplyVsFruitDo;
import com.fruitrade.service.BrandService;
import com.fruitrade.service.FruitClassifyService;
import com.fruitrade.service.FruitService;
import com.fruitrade.service.RetailService;
import com.fruitrade.service.SupplyVsFruitService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class SupplyVsFruitAction implements Action, ModelDriven<SupplyVsFruitDo> {

	@Autowired
	private SupplyVsFruitService supplyVsFruitService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private RetailService retailService;
	@Autowired
	private FruitClassifyService fruitClassifyService;
	@Autowired
	private FruitService fruitService;

	private SupplyVsFruitDo supplyVsFruitData;
	private List<SupplyVsFruitDo> rows;
	private int total;
	private String ids;

	@Override
	public SupplyVsFruitDo getModel() {
		this.supplyVsFruitData = new SupplyVsFruitDo();
		return this.supplyVsFruitData;
	}

	/**
	 * 加载列表数据
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<SupplyVsFruitDo> supplyVsFruitList = this.supplyVsFruitService
					.listSupplyVsFruit(this.supplyVsFruitData);
			if(supplyVsFruitList.size()>0) {
				BrandDo brand;
				RetailDo retail;
				FruitClassifyDo fruitClassify;
				FruitDo fruit;
				for (int i = 0; i < supplyVsFruitList.size(); i++) {
					if ("1".equals(supplyVsFruitList.get(i).getSupplyType())) { // 品牌
						brand = brandService.selectBrandById(supplyVsFruitList.get(i).getSupplyId());
						if (brand != null) {
							supplyVsFruitList.get(i).setSupplyName(brand.getBrandName());
						}
					} else if ("2".equals(supplyVsFruitList.get(i).getSupplyType())) {// 散户
						retail = retailService.selectRetailById(supplyVsFruitList.get(i).getSupplyId());
						if (retail != null) {
							supplyVsFruitList.get(i).setSupplyName(retail.getRetailName());
						}
					}
					if (supplyVsFruitList.get(i).getClassifyId()!=null) {// 水果分类
						fruitClassify = fruitClassifyService.selectFruitClassifyById(supplyVsFruitList.get(i).getClassifyId());
						if (fruitClassify != null) {
							supplyVsFruitList.get(i).setClassifyName(fruitClassify.getClassifyName());
						}
					}
					if (supplyVsFruitList.get(i).getFruitId()!=null) {// 水果名称
						fruit = fruitService.selectFruitById(supplyVsFruitList.get(i).getFruitId());
						if (fruit != null) {
							supplyVsFruitList.get(i).setFruitName(fruit.getFruitName());
						}
					}
				}
			}
			this.rows = supplyVsFruitList;
			this.total = supplyVsFruitList.size();
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
	public String saveOrUpdateSupplyVsFruit() {
		try {
			if (this.supplyVsFruitData.getId() != null) { // 存在主键 走编辑
				this.supplyVsFruitService.updateSupplyVsFruit(this.supplyVsFruitData);
			} else { // 不存在主键 走新增
				this.supplyVsFruitService.insertSupplyVsFruit(this.supplyVsFruitData);
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
	public String deleteSupplyVsFruit() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				SupplyVsFruitDo SupplyVsFruit = new SupplyVsFruitDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						SupplyVsFruit.setId(Integer.parseInt(idsArray[i]));
						this.supplyVsFruitService.deleteSupplyVsFruit(SupplyVsFruit);
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
	 * 去新增页面
	 * 
	 * @return
	 */
	public String toAddSupplyVsFruitPage() {
		try {
			if (this.supplyVsFruitData.getSupplyType() != null && this.supplyVsFruitData.getSupplyId() != null) {
				if ("1".equals(this.supplyVsFruitData.getSupplyType())) { // 品牌
					BrandDo brand = brandService.selectBrandById(this.supplyVsFruitData.getSupplyId());
					if (brand != null) {
						this.supplyVsFruitData.setSupplyName(brand.getBrandName());
					}
				} else if ("2".equals(this.supplyVsFruitData.getSupplyType())) {// 散户
					RetailDo retail = retailService.selectRetailById(this.supplyVsFruitData.getSupplyId());
					if (retail != null) {
						this.supplyVsFruitData.setSupplyName(retail.getRetailName());
					}
				}
				return SUCCESS;
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
	public String toUpdateSupplyVsFruitPage() {
		try {
			if (this.supplyVsFruitData.getId() != null) {
				this.supplyVsFruitData = this.supplyVsFruitService
						.selectSupplyVsFruitById(this.supplyVsFruitData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	public List<SupplyVsFruitDo> getRows() {
		return rows;
	}

	public void setRows(List<SupplyVsFruitDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public SupplyVsFruitDo getSupplyVsFruitData() {
		return supplyVsFruitData;
	}

	public void setSupplyVsFruitData(SupplyVsFruitDo supplyVsFruitData) {
		this.supplyVsFruitData = supplyVsFruitData;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}