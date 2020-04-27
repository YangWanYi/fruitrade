package com.fruitrade.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.FruitClassifyDo;
import com.fruitrade.domain.FruitDo;
import com.fruitrade.service.FruitClassifyService;
import com.fruitrade.service.FruitService;
import com.fruitrade.service.StorehouseService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class FruitAction implements Action, ModelDriven<FruitDo> {

	@Autowired
	private FruitService fruitService;

	@Autowired
	private FruitClassifyService fruitClassifyService;

	@Autowired
	private StorehouseService storehouseService;

	private FruitDo fruitData;
	private List<FruitDo> rows;
	private int total;
	private String ids;
	private String path;
	private String fruitName;
	private String purchasingUnit;

	@Override
	public FruitDo getModel() {
		this.fruitData = new FruitDo();
		return this.fruitData;
	}

	/**
	 * 加载水果列表
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<FruitDo> fruitList = this.fruitService.listFruit(this.fruitData);
			this.rows = fruitList;
			this.total = fruitList.size();
			if (fruitList.size() > 0) {
				FruitClassifyDo fruitClassifyDo;
				for (int i = 0; i < fruitList.size(); i++) {
					if (fruitList.get(i).getFruitClassifyID() != null) {
						fruitClassifyDo = fruitClassifyService
								.selectFruitClassifyById(fruitList.get(i).getFruitClassifyID());
						if (fruitClassifyDo != null) {
							fruitList.get(i).setFruitClassifyName(fruitClassifyDo.getClassifyName());
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
	 * 新增或编辑水果信息
	 * 
	 * @return
	 */
	public String saveOrUpdateFruit() {
		try {
			if (this.fruitData.getId() != null) { // 存在主键 走编辑 
				this.fruitService.updateFruit(this.fruitData);
			} else { // 不存在主键 走新增
				this.fruitService.insertFruit(this.fruitData);
			}
			return NONE;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 根据主键批量删除水果
	 * 
	 * @return
	 */
	public String deleteFruit() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				FruitDo Fruit = new FruitDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						Fruit.setId(Integer.parseInt(idsArray[i]));
						this.fruitService.deleteFruit(Fruit);
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
	 * 去编辑水果页面
	 * 
	 * @return
	 */
	public String toUpdateFruitPage() {
		try {
			if (this.fruitData.getId() != null) {
				this.fruitData = this.fruitService.selectFruitById(this.fruitData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
	 * 去新增水果页面
	 * 
	 * @return
	 */
	public String toAddFruitPage() {
		return SUCCESS;
	}

	/**
	 * 去水果列表页面
	 * 
	 * @return
	 */
	public String toFruitListPage() {
		try {
			ActionContext ac = ActionContext.getContext();
			ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			this.path = sc.getContextPath().concat("/ui/imgs/upload/");
			System.out.println(this.path);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 去产品中心页面
	 * 
	 * @return
	 */
	public String toGoodsListPage() {
		try {
			ActionContext ac = ActionContext.getContext();
			ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			this.path = sc.getContextPath().concat("/ui/imgs/upload/");
			System.out.println(this.path);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 去商品新增页面
	 * 
	 * @return
	 */
	public String toAddFruitListPage() {
		return SUCCESS;
	}

	/**
	 * 查根ID据询水果
	 * 
	 * @param request
	 * @return
	 */
	public String getFruitById() {
		try {
			if (this.fruitData.getId() != null) {
				this.fruitData = this.fruitService.selectFruitById(this.fruitData.getId());
				if (this.fruitData != null) {
					this.fruitName = this.fruitData.getFruitName();
//					this.purchasingUnit = this.fruitData.getPurchasingUnit();
				}
				return NONE;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public List<FruitDo> getRows() {
		return rows;
	}

	public void setRows(List<FruitDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public FruitDo getFruitData() {
		return fruitData;
	}

	public void setFruitData(FruitDo fruitData) {
		this.fruitData = fruitData;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public String getPurchasingUnit() {
		return purchasingUnit;
	}

	public void setPurchasingUnit(String purchasingUnit) {
		this.purchasingUnit = purchasingUnit;
	}

}