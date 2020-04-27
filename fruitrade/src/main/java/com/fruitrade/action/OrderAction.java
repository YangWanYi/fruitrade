package com.fruitrade.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.CartDo;
import com.fruitrade.domain.OrderDo;
import com.fruitrade.domain.PurchaseDo;
import com.fruitrade.domain.StorageDo;
import com.fruitrade.service.CartService;
import com.fruitrade.service.OrderService;
import com.fruitrade.service.PurchaseService;
import com.fruitrade.service.StorageService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction implements Action, ModelDriven<OrderDo> {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private StorageService storageService;

	private OrderDo orderData;
	private List<OrderDo> rows;
	private int total;
	private String ids;

	@Override
	public OrderDo getModel() {
		this.orderData = new OrderDo();
		return this.orderData;
	}

	/**
	 * 加载订单列表
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			if (this.orderData.getUserId() != null) {
				List<OrderDo> OrderList = this.orderService.listOrder(this.orderData);
				this.rows = OrderList;
				this.total = OrderList.size();
				return NONE;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 新增或编辑订单信息
	 * 
	 * @return
	 */
	public String saveOrUpdateOrder() {
		try {
			if (this.orderData.getId() != null) { // 存在主键 走编辑
				this.orderService.updateOrder(this.orderData);
			} else { // 不存在主键 走新增
				this.orderData.setCreateTime(new Date());
				this.orderData.setOrderState("0"); //订单状态 0： 未支付 1：已支付0
//				if(this.orderData.getUnitPrice() != null && this.orderData.getPurchaseNum() != null) {
//					this.orderData.setTotalPrice(this.orderData.getUnitPrice()*this.orderData.getPurchaseNum());
//				}
				this.orderService.insertOrder(this.orderData);
			}
			return NONE;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 根据主键批量删除订单
	 * 
	 * @return
	 */
	public String deleteOrder() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				OrderDo Order = new OrderDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						Order.setId(Integer.parseInt(idsArray[i]));
						this.orderService.deleteOrder(Order);
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
	 * 支付订单
	 * 
	 * @return
	 */
	public String payOrder() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				StorageDo storageDo;
				CartDo cartDo;
				Double sumMoney = 0.0;
				Integer userId = null;
				PurchaseDo purchaseDo;
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						cartDo = cartService.selectCartById(Integer.parseInt(idsArray[i]));
						if(cartDo!=null) {
							purchaseDo = purchaseService.selectPurchaseById(cartDo.getPurchaseId());
							if(purchaseDo !=null) {
								storageDo = storageService.selectStorageById(purchaseDo.getStorageId());
									if(storageDo!=null) {
										if(cartDo.getSalePrice()!=null&&cartDo.getPurchaseNum()!=null&&storageDo.getInventory()!=null) {
											storageDo.setInventory(storageDo.getInventory()-(cartDo.getPurchaseNum()*cartDo.getSalePrice()));
											storageService.updateStorage(storageDo);// 减少库存
										}
									}
							}
							userId = cartDo.getUserId();
							if(cartDo.getSalePrice()!=null&&cartDo.getPurchaseNum()!=null) {
								sumMoney += cartDo.getPurchaseNum()*cartDo.getSalePrice();
							}
						}
					}
				}
				OrderDo order = new OrderDo();
				order.setOrderState("1");//已支付
				order.setUserId(userId);
				order.setTotalPrice(sumMoney);
				order.setCreateTime(new Date());
				order.setPayTime(new Date());
				orderService.insertOrder(order);
				return NONE;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 去编辑订单页面
	 * 
	 * @return
	 */
	public String toUpdateOrderPage() {
		try {
			if (this.orderData.getId() != null) {
				this.orderData = this.orderService.selectOrderById(this.orderData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 去订单列表页面
	 * 
	 * @return
	 */
	public String toOrderListPage() {
		return SUCCESS;
	}

	public List<OrderDo> getRows() {
		return rows;
	}

	public void setRows(List<OrderDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public OrderDo getOrderData() {
		return orderData;
	}

	public void setOrderData(OrderDo orderData) {
		this.orderData = orderData;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}