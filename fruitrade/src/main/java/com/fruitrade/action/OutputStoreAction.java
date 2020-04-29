package com.fruitrade.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.OutputStoreDo;
import com.fruitrade.service.OutputStoreService;
import com.fruitrade.service.StorehouseService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class OutputStoreAction implements Action, ModelDriven<OutputStoreDo> {

	@Autowired
	private OutputStoreService outputStoreService;

	@Autowired
	private StorehouseService storehouseService;

	private OutputStoreDo outputStoreData;
	private List<OutputStoreDo> rows;
	private int total;
	private String ids;
	private Integer storeHouseId;// 仓库ID

	@Override
	public OutputStoreDo getModel() {
		this.outputStoreData = new OutputStoreDo();
		return this.outputStoreData;
	}

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<OutputStoreDo> outputStoreList = this.outputStoreService.listOutputStore(this.outputStoreData);
			this.rows = outputStoreList;
			this.total = outputStoreList.size();
			return NONE;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 查询每日水果折损总览
	 * 
	 * @return
	 */
	public String listWreckSumByDay() {
		try {
			List<Object> outputStoreList = this.outputStoreService.listWreckSumByDay();
			List<OutputStoreDo> list = new ArrayList<OutputStoreDo>();
			if(outputStoreList.size()>0) {
				Object[] obj;
				for (int i = 0; i < outputStoreList.size(); i++) {
					obj = (Object[]) outputStoreList.get(i);
					OutputStoreDo outputStoreDo = new OutputStoreDo();
					outputStoreDo.setFruitName(obj[0].toString());
					if(obj[1]!=null) {
						outputStoreDo.setWreckSum(Double.valueOf(obj[1].toString()));
					}
					outputStoreDo.setDayStr(obj[2].toString());
					list.add(outputStoreDo);
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
	public String saveOrUpdateOutputStore() {
		try {
			if (this.outputStoreData.getId() != null) { // 存在主键 走编辑
				this.outputStoreService.updateOutputStore(this.outputStoreData);
			} else { // 不存在主键 走新增
				this.outputStoreService.insertOutputStore(this.outputStoreData);
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
	public String deleteOutputStore() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				OutputStoreDo OutputStore = new OutputStoreDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						OutputStore.setId(Integer.parseInt(idsArray[i]));
						this.outputStoreService.deleteOutputStore(OutputStore);
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
	public String toUpdateOutputStorePage() {
		try {
			if (this.outputStoreData.getId() != null) {
				this.outputStoreData = this.outputStoreService.selectOutputStoreById(this.outputStoreData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}


	public List<OutputStoreDo> getRows() {
		return rows;
	}

	public void setRows(List<OutputStoreDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public OutputStoreDo getOutputStoreData() {
		return outputStoreData;
	}

	public void setOutputStoreData(OutputStoreDo outputStoreData) {
		this.outputStoreData = outputStoreData;
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