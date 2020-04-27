package com.fruitrade.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.BrandDo;
import com.fruitrade.domain.FruitClassifyDo;
import com.fruitrade.domain.FruitDo;
import com.fruitrade.domain.PurchaseDo;
import com.fruitrade.domain.RetailDo;
import com.fruitrade.domain.StorageDo;
import com.fruitrade.domain.StorehouseDo;
import com.fruitrade.domain.SupplyVsFruitDo;
import com.fruitrade.domain.UserDo;
import com.fruitrade.service.BrandService;
import com.fruitrade.service.FruitClassifyService;
import com.fruitrade.service.FruitService;
import com.fruitrade.service.PurchaseService;
import com.fruitrade.service.RetailService;
import com.fruitrade.service.StorageService;
import com.fruitrade.service.StorehouseService;
import com.fruitrade.service.SupplyVsFruitService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class PurchaseAction implements Action, ModelDriven<PurchaseDo> {

	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private FruitClassifyService fruitClassifyService;
	@Autowired
	private FruitService fruitService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private StorehouseService storehouseService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private RetailService retailService;
	@Autowired
	private SupplyVsFruitService supplyVsFruitService;

	private PurchaseDo purchaseData;
	private List<Map<String,Object>> rows;
	private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	private Map<String,Object> purchaseMap = new HashMap<String,Object>();
	private int total;
	private String ids;

	@Override
	public PurchaseDo getModel() {
		this.purchaseData = new PurchaseDo();
		return this.purchaseData;
	}

	/**
	 * 加载数据列表
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<Object> purchaseList = this.purchaseService.listPurchase(this.purchaseData);
			if (purchaseList.size() > 0) {
				FruitClassifyDo fruitClassify;
				FruitDo fruit;
				Object[] obj;
				SupplyVsFruitDo supplyVsFruit;
				PurchaseDo purchase;
				StorehouseDo storehouseDo;
				StorageDo storageDo;
				BrandDo brandDo;
				RetailDo retailDo;
				for (int i = 0; i < purchaseList.size(); i++) {
					obj = (Object[]) purchaseList.get(i);
					Map<String, Object> map = new HashMap<String, Object>();
					purchase = (PurchaseDo) obj[0];
					supplyVsFruit = (SupplyVsFruitDo) obj[1];
					if (supplyVsFruit != null) {
						if ("1".equals(supplyVsFruit.getSupplyType())) {
							map.put("supplyType", "品牌");
							if (supplyVsFruit.getSupplyId() != null) {
								brandDo = brandService.selectBrandById(supplyVsFruit.getSupplyId());
								if(brandDo != null) {
									map.put("supplyName", brandDo.getBrandName());
								}
							}
						}else if ("2".equals(supplyVsFruit.getSupplyType())) {
							map.put("supplyType", "散户");
							if (supplyVsFruit.getSupplyId() != null) {
								retailDo = retailService.selectRetailById(supplyVsFruit.getSupplyId());
								if(retailDo != null) {
									map.put("supplyName", retailDo.getRetailName());
								}
							}
						}
						if (supplyVsFruit.getClassifyId() != null) {
							fruitClassify = fruitClassifyService.selectFruitClassifyById(supplyVsFruit.getClassifyId());
							if (fruitClassify != null) {
								map.put("classifyName", fruitClassify.getClassifyName());
							}
						}
						map.put("supplyId", supplyVsFruit.getSupplyId());
						map.put("classifyId", supplyVsFruit.getClassifyId());
						map.put("fruitId", supplyVsFruit.getFruitId());
						if (supplyVsFruit.getFruitId() != null) {
							fruit = fruitService.selectFruitById(supplyVsFruit.getFruitId());
							if (fruit != null) {
								map.put("fruitName", fruit.getFruitName());
								map.put("pic", fruit.getPic());
							}
						}
						map.put("place", supplyVsFruit.getPlace());
						map.put("price", supplyVsFruit.getPrice());
						map.put("unit", supplyVsFruit.getUnit());
					}
					map.put("id", purchase.getId());
					map.put("purchaseNum", purchase.getPurchaseNum());
					map.put("salePrice", purchase.getSalePrice());
					map.put("storeHouseId", purchase.getStoreHouseId());
					map.put("storageId", purchase.getStorageId());
					if(purchase.getStoreHouseId()!=null) {// 仓库
						storehouseDo = storehouseService.selectStorehouseById(purchase.getStoreHouseId());
						if(storehouseDo!=null) {
							map.put("storeHouseCode", storehouseDo.getStoreHouseCode());
						}
					}
					if(purchase.getStorageId()!=null) {// 货架
						storageDo = storageService.selectStorageById(purchase.getStorageId());
						if(storageDo!=null) {
							map.put("storageCode", storageDo.getStorageCode());
							map.put("inventory", storageDo.getInventory());
						}
					}
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
	public String saveOrUpdatePurchase() {
		try {
			ActionContext actionContext = ActionContext.getContext();
			UserDo user = (UserDo) actionContext.getSession().get("user");
			if (this.purchaseData.getId() != null) { // 存在主键 走编辑
				this.purchaseService.updatePurchase(this.purchaseData);
			} else { // 不存在主键 走新增
				this.purchaseData.setCreateId(user.getId());
				this.purchaseData.setCreateTime(new Date());
				this.purchaseService.insertPurchase(this.purchaseData);
			}
			StorageDo storageDo = storageService.selectStorageById(this.purchaseData.getStorageId());
			if(storageDo!=null) {
				storageDo.setInventory(this.purchaseData.getPurchaseNum());
				storageDo.setSupplyVsFruitId(this.purchaseData.getSupplyVsFruitId());
				storageService.updateStorage(storageDo);
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
	public String deletePurchase() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				PurchaseDo Purchase = new PurchaseDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						Purchase.setId(Integer.parseInt(idsArray[i]));
						this.purchaseService.deletePurchase(Purchase);
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
	public String toUpdatePurchasePage() {
		try {
			if (this.purchaseData.getId() != null) {
				this.purchaseData = this.purchaseService.selectPurchaseById(this.purchaseData.getId());
				SupplyVsFruitDo supplyVsFruit = supplyVsFruitService.selectSupplyVsFruitById(this.purchaseData.getSupplyVsFruitId());
				if (supplyVsFruit != null) {
					if ("1".equals(supplyVsFruit.getSupplyType())) {
						this.purchaseMap.put("supplyType", "品牌");
						if (supplyVsFruit.getSupplyId() != null) {
							BrandDo brandDo = brandService.selectBrandById(supplyVsFruit.getSupplyId());
							if(brandDo != null) {
								this.purchaseMap.put("supplyName", brandDo.getBrandName());
							}
						}
					}else if ("2".equals(supplyVsFruit.getSupplyType())) {
						this.purchaseMap.put("supplyType", "散户");
						if (supplyVsFruit.getSupplyId() != null) {
							RetailDo retailDo = retailService.selectRetailById(supplyVsFruit.getSupplyId());
							if(retailDo != null) {
								this.purchaseMap.put("supplyName", retailDo.getRetailName());
							}
						}
					}
					if (supplyVsFruit.getClassifyId() != null) {
						FruitClassifyDo fruitClassify = fruitClassifyService.selectFruitClassifyById(supplyVsFruit.getClassifyId());
						if (fruitClassify != null) {
							this.purchaseMap.put("classifyName", fruitClassify.getClassifyName());
						}
					}
					this.purchaseMap.put("supplyId", supplyVsFruit.getSupplyId());
					this.purchaseMap.put("classifyId", supplyVsFruit.getClassifyId());
					this.purchaseMap.put("fruitId", supplyVsFruit.getFruitId());
					if (supplyVsFruit.getFruitId() != null) {
						FruitDo fruit = fruitService.selectFruitById(supplyVsFruit.getFruitId());
						if (fruit != null) {
							this.purchaseMap.put("fruitName", fruit.getFruitName());
							this.purchaseMap.put("pic", fruit.getPic());
						}
					}
					this.purchaseMap.put("place", supplyVsFruit.getPlace());
					this.purchaseMap.put("price", supplyVsFruit.getPrice());
					this.purchaseMap.put("unit", supplyVsFruit.getUnit());
				}
				this.purchaseMap.put("id", this.purchaseData.getId());
				this.purchaseMap.put("purchaseNum", this.purchaseData.getPurchaseNum());
				this.purchaseMap.put("salePrice", this.purchaseData.getSalePrice());
				this.purchaseMap.put("storeHouseId", this.purchaseData.getStoreHouseId());
				this.purchaseMap.put("storageId", this.purchaseData.getStorageId());
				if(this.purchaseData.getStoreHouseId()!=null) {// 仓库
					StorehouseDo storehouseDo = storehouseService.selectStorehouseById(this.purchaseData.getStoreHouseId());
					if(storehouseDo!=null) {
						this.purchaseMap.put("storeHouseCode", storehouseDo.getStoreHouseCode());
					}
				}
				if(this.purchaseData.getStorageId()!=null) {// 货架
					StorageDo storageDo = storageService.selectStorageById(this.purchaseData.getStorageId());
					if(storageDo!=null) {
						this.purchaseMap.put("storageCode", storageDo.getStorageCode());
						this.purchaseMap.put("inventory", storageDo.getInventory());
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

	public List<Map<String,Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String,Object>> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public PurchaseDo getPurchaseData() {
		return purchaseData;
	}

	public void setPurchaseData(PurchaseDo purchaseData) {
		this.purchaseData = purchaseData;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public Map<String, Object> getPurchaseMap() {
		return purchaseMap;
	}

	public void setPurchaseMap(Map<String, Object> purchaseMap) {
		this.purchaseMap = purchaseMap;
	}

}