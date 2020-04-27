package com.fruitrade.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.StorageDo;
import com.fruitrade.domain.StorehouseDo;
import com.fruitrade.service.StorageService;
import com.fruitrade.service.StorehouseService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class StorageAction implements Action, ModelDriven<StorageDo> {

	@Autowired
	private StorageService storageService;

	@Autowired
	private StorehouseService storehouseService;

	private StorageDo storageData;
	private List<StorageDo> rows;
	private int total;
	private String ids;
	private Integer storeHouseId;// 仓库ID

	@Override
	public StorageDo getModel() {
		this.storageData = new StorageDo();
		return this.storageData;
	}

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<StorageDo> storageList = this.storageService.listStorage(this.storageData);
			this.rows = storageList;
			this.total = storageList.size();
			if(storageList.size()>0) {
				StorehouseDo storehouseDo;
				for (int i = 0; i < storageList.size(); i++) {
					if(storageList.get(i).getStoreHouseId()!=null) {
						storehouseDo = storehouseService.selectStorehouseById(storageList.get(i).getStoreHouseId());
						if(storehouseDo != null) {
							storageList.get(i).setStoreHouse(storehouseDo.getStoreHouseCode());
						}
					}
				}
			}
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
	public String saveOrUpdateStorage() {
		try {
			if (this.storageData.getId() != null) { // 存在主键 走编辑
				this.storageService.updateStorage(this.storageData);
			} else { // 不存在主键 走新增
				this.storageService.insertStorage(this.storageData);
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
	public String deleteStorage() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				StorageDo Storage = new StorageDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						Storage.setId(Integer.parseInt(idsArray[i]));
						this.storageService.deleteStorage(Storage);
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
	public String toUpdateStoragePage() {
		try {
			if (this.storageData.getId() != null) {
				this.storageData = this.storageService.selectStorageById(this.storageData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}


	public List<StorageDo> getRows() {
		return rows;
	}

	public void setRows(List<StorageDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public StorageDo getStorageData() {
		return storageData;
	}

	public void setStorageData(StorageDo storageData) {
		this.storageData = storageData;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getStoreHouseId() {
		return storeHouseId;
	}

	public void setStoreHouseId(Integer storeHouseId) {
		this.storeHouseId = storeHouseId;
	}

}