package com.fruitrade.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.StorehouseDo;
import com.fruitrade.domain.UserDo;
import com.fruitrade.service.StorehouseService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class StorehouseAction implements Action, ModelDriven<StorehouseDo> {

	@Autowired
	private StorehouseService storehouseService;

	private StorehouseDo storehouseData;
	private List<StorehouseDo> rows;
	private int total;
	private String ids;

	@Override
	public StorehouseDo getModel() {
		this.storehouseData = new StorehouseDo();
		return this.storehouseData;
	}

	/**
	 * 加载仓库列表
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<StorehouseDo> storehouseList = this.storehouseService.listStorehouse(this.storehouseData);
			this.rows = storehouseList;
			this.total = storehouseList.size();
			return NONE;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 新增或编辑仓库信息
	 * 
	 * @return
	 */
	public String saveOrUpdateStorehouse() {
		try {
			ActionContext actionContext = ActionContext.getContext();
			UserDo user = (UserDo) actionContext.getSession().get("user");
			if (this.storehouseData.getId() != null) { // 存在主键 走编辑
				this.storehouseData.setUpdateId(user.getId());
				this.storehouseData.setUpdateTime(new Date());
				this.storehouseService.updateStorehouse(this.storehouseData);
			} else { // 不存在主键 走新增
				this.storehouseData.setCreateId(user.getId());
				this.storehouseData.setCreateTime(new Date());
				this.storehouseService.insertStorehouse(this.storehouseData);
			}
			return NONE;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 根据主键批量删除仓库
	 * 
	 * @return
	 */
	public String deleteStorehouse() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				StorehouseDo Storehouse = new StorehouseDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						Storehouse.setId(Integer.parseInt(idsArray[i]));
						this.storehouseService.deleteStorehouse(Storehouse);
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
	 * 去编辑仓库页面
	 * 
	 * @return
	 */
	public String toUpdateStorehousePage() {
		try {
			if (this.storehouseData.getId() != null) {
				this.storehouseData = this.storehouseService.selectStorehouseById(this.storehouseData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 去仓库详情页面
	 * 
	 * @return
	 */
	public String toViewStorehousePage() {
		try {
			if (this.storehouseData.getId() != null) {
				this.storehouseData = this.storehouseService.selectStorehouseById(this.storehouseData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	public List<StorehouseDo> getRows() {
		return rows;
	}

	public void setRows(List<StorehouseDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public StorehouseDo getStorehouseData() {
		return storehouseData;
	}

	public void setStorehouseData(StorehouseDo storehouseData) {
		this.storehouseData = storehouseData;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}