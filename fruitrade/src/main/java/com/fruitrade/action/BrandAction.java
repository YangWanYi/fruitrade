package com.fruitrade.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.BrandDo;
import com.fruitrade.domain.FruitClassifyDo;
import com.fruitrade.domain.FruitDo;
import com.fruitrade.domain.SupplyVsFruitDo;
import com.fruitrade.service.BrandService;
import com.fruitrade.service.FruitClassifyService;
import com.fruitrade.service.FruitService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class BrandAction implements Action, ModelDriven<BrandDo> {

	@Autowired
	private BrandService brandService;
	@Autowired
	private FruitClassifyService fruitClassifyService;
	@Autowired
	private FruitService fruitService;

	private BrandDo brandData;
	private List<Map<String, Object>> rows;
	private int total;
	private String ids;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	@Override
	public BrandDo getModel() {
		this.brandData = new BrandDo();
		return this.brandData;
	}

	/**
	 * 加载品牌列表数据
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<Object> mapList = this.brandService.listBrand(this.brandData);
			if (mapList.size() > 0) {
				FruitClassifyDo fruitClassify;
				FruitDo fruit;
				Object[] obj;
				SupplyVsFruitDo supplyVsFruit;
				BrandDo brand;
				for (int i = 0; i < mapList.size(); i++) {
					obj = (Object[]) mapList.get(i);
					Map<String, Object> map = new HashMap<String, Object>();
					brand = (BrandDo) obj[0];
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
					map.put("id", brand.getId());
					map.put("brandName", brand.getBrandName());
					map.put("brandCode", brand.getBrandCode());
					map.put("principal", brand.getPrincipal());
					map.put("contactNum", brand.getContactNum());
					map.put("address", brand.getAddress());
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
	public String saveOrUpdateBrand() {
		try {
			if (this.brandData.getId() != null) { // 存在主键 走编辑
				this.brandService.updateBrand(this.brandData);
			} else { // 不存在主键 走新增
				this.brandService.insertBrand(this.brandData);
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
	public String deleteBrand() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				BrandDo Brand = new BrandDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						Brand.setId(Integer.parseInt(idsArray[i]));
						this.brandService.deleteBrand(Brand);
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
	public String toUpdateBrandPage() {
		try {
			if (this.brandData.getId() != null) {
				this.brandData = this.brandService.selectBrandById(this.brandData.getId());
				return SUCCESS;
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
	public String toAddBrandPage() {
		return SUCCESS;
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

	public BrandDo getBrandData() {
		return brandData;
	}

	public void setBrandData(BrandDo brandData) {
		this.brandData = brandData;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}