package com.fruitrade.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.CartDo;
import com.fruitrade.domain.FruitDo;
import com.fruitrade.domain.OrderDo;
import com.fruitrade.domain.OutputStoreDo;
import com.fruitrade.domain.PurchaseDo;
import com.fruitrade.domain.StorageDo;
import com.fruitrade.domain.StorehouseDo;
import com.fruitrade.domain.SupplyVsFruitDo;
import com.fruitrade.domain.UserDo;
import com.fruitrade.service.CartService;
import com.fruitrade.service.FruitService;
import com.fruitrade.service.OrderService;
import com.fruitrade.service.OutputStoreService;
import com.fruitrade.service.PurchaseService;
import com.fruitrade.service.StorageService;
import com.fruitrade.service.StorehouseService;
import com.fruitrade.service.SupplyVsFruitService;
import com.fruitrade.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction implements Action, ModelDriven<OrderDo> {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	@Autowired
	private FruitService fruitService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private SupplyVsFruitService supplyVsFruitService;
	@Autowired
	private OutputStoreService outputStoreService;
	@Autowired
	private StorehouseService storehouseService;

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
			List<OrderDo> orderList = this.orderService.listOrder(this.orderData);
			if(orderList.size()>0) {
				UserDo userDo;
				for (int i = 0; i < orderList.size(); i++) {
					if(orderList.get(i).getUserId()!=null) {
						userDo = userService.selectUserById(orderList.get(i).getUserId());
						if(userDo!=null) {
							orderList.get(i).setUserName(userDo.getUserName());
						}
						
						if("1".equals(orderList.get(i).getOrderState())) {
							orderList.get(i).setOrderStateX("已支付");
						}else if("0".equals(orderList.get(i).getOrderState())) {
							orderList.get(i).setOrderStateX("未支付");
						}
					}
				}
			}
			this.rows = orderList;
			this.total = orderList.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 新增或编辑订单信息
	 * 
	 * @return
	 */
	public String saveOrUpdateOrder() {
		try {
			ActionContext actionContext = ActionContext.getContext();
			UserDo user = (UserDo) actionContext.getSession().get("user");
			if (this.orderData.getId() != null) { // 存在主键 走编辑
				this.orderData.setUpdateId(user.getId());
				this.orderData.setUpdateTime(new Date());
				this.orderService.updateOrder(this.orderData);
			} else { // 不存在主键 走新增
				if (StringUtils.isNoneEmpty(ids)) {
					String[] idsArray = ids.split(",");
					CartDo cartDo;
					Double sumMoney = 0.0;
					OrderDo order = new OrderDo();
					order.setOrderState("0");// 未支付
					order.setUserId(user.getId());
					order.setTotalPrice(sumMoney);
					order.setCreateTime(new Date());
					order.setPostage(5);// 默认邮费5元
					this.orderService.insertOrder(order);
					for (int i = 0; i < idsArray.length; i++) {
						if (StringUtils.isNoneEmpty(idsArray[i])) {
							cartDo = cartService.selectCartById(Integer.parseInt(idsArray[i]));
							if (cartDo != null) {
								if (cartDo.getSalePrice() != null && cartDo.getPurchaseNum() != null) {
									sumMoney += cartDo.getPurchaseNum() * cartDo.getSalePrice();
								}
								cartDo.setState(1);// 已生成订单
								cartDo.setOrderId(order.getId());// 更新购物车的订单id
								cartService.updateCart(cartDo);
							}
						}
					}
					order.setTotalPrice(sumMoney);// 把总额更新进订单
					orderService.updateOrder(order);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 根据主键批量删除订单
	 * 
	 * @return
	 */
	public String deleteOrder() {
		try {
			if (this.orderData.getId() != null) {
				CartDo cartDo = new CartDo();
				cartDo.setOrderId(this.orderData.getId());
				List<CartDo> cartList = cartService.listCart(cartDo);
				for (int i = 0; i < cartList.size(); i++) {
					cartService.deleteCart(cartList.get(i));// 删除订单关联的购物车
				}
				this.orderService.deleteOrder(this.orderService.selectOrderById(this.orderData.getId()));
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
			if (this.orderData.getId() != null) {
				OrderDo orderDo = this.orderService.selectOrderById(this.orderData.getId());
				StorageDo storageDo;
				PurchaseDo purchaseDo;
				StorehouseDo storehouseDo;
				SupplyVsFruitDo supplyVsFruitDo;
				FruitDo fruitDo;
				OutputStoreDo outputStoreQuery = new OutputStoreDo();
				List<OutputStoreDo> outputStoreList;
				OutputStoreDo outputStore;
				ActionContext actionContext = ActionContext.getContext();
				UserDo user = (UserDo) actionContext.getSession().get("user");
				if (orderDo != null) {
					CartDo cartQuery = new CartDo();
					cartQuery.setOrderId(this.orderData.getId());// 找到订单关联的购物车信息
					List<CartDo> cartList = cartService.listCart(cartQuery);
					for (int i = 0; i < cartList.size(); i++) {
						purchaseDo = purchaseService.selectPurchaseById(cartList.get(i).getPurchaseId());
						if (purchaseDo != null) {
							storageDo = storageService.selectStorageById(purchaseDo.getStorageId());
							if (storageDo != null) {
								if (cartList.get(i).getSalePrice() != null && cartList.get(i).getPurchaseNum() != null
										&& storageDo.getInventory() != null) {
									storageDo.setInventory(storageDo.getInventory()
											- (cartList.get(i).getPurchaseNum() * cartList.get(i).getSalePrice()));
									storageService.updateStorage(storageDo);// 减少库存
								}
							}
							// 增加出库数据
							supplyVsFruitDo = supplyVsFruitService
									.selectSupplyVsFruitById(purchaseDo.getSupplyVsFruitId());
							if (supplyVsFruitDo != null) {
								storehouseDo = storehouseService.selectStorehouseById(purchaseDo.getStoreHouseId());
								if (storehouseDo != null) {
									outputStoreQuery.setStoreId(storehouseDo.getId());
								}
								outputStoreQuery.setCreateTime(new Date());
								outputStoreQuery.setFruitId(supplyVsFruitDo.getFruitId());
								outputStoreList = outputStoreService.listOutputStore(outputStoreQuery);
								if (outputStoreList.size() > 0) {// 更新
									outputStore = outputStoreList.get(0);
									outputStore.setUpdateId(user.getId());
									outputStore.setUpdateTime(new Date());
									if (outputStore.getOutPutNum() != null
											&& cartList.get(i).getPurchaseNum() != null) {
										outputStore.setOutPutNum(
												outputStore.getOutPutNum() + cartList.get(i).getPurchaseNum());
									}
									outputStoreService.updateOutputStore(outputStore);
								} else {// 新增
									outputStore = new OutputStoreDo();
									if (storehouseDo != null) {
										outputStore.setStoreId(storehouseDo.getId());
										outputStore.setStoreName(storehouseDo.getStoreHouseName());
										outputStore.setStoreCode(storehouseDo.getStoreHouseCode());
										outputStore.setStorePlace(storehouseDo.getAddress());
										outputStore.setLinkman(storehouseDo.getPrincipal());
										outputStore.setContactNum(storehouseDo.getContactNum());
										outputStore.setOutPutNum(cartList.get(i).getPurchaseNum());
									}
									outputStore.setUnit(supplyVsFruitDo.getUnit());
									outputStore.setCreateId(user.getId());
									outputStore.setCreateTime(new Date());
									outputStore.setFruitId(supplyVsFruitDo.getFruitId());
									fruitDo = fruitService.selectFruitById(supplyVsFruitDo.getFruitId());
									if (fruitDo != null) {
										outputStore.setFruitName(fruitDo.getFruitName());
									}
									outputStoreService.insertOutputStore(outputStore);
								}
							}
						}
					}
					orderDo.setOrderState("1");// 已支付
					orderDo.setPayTime(new Date());
					orderDo.setUpdateId(user.getId());
					orderDo.setUpdateTime(new Date());
					orderService.updateOrder(orderDo);
					// 减少用户余额
					user.setBalance(user.getBalance()-orderDo.getTotalPrice());
					user.setUpdateTime(new Date());
					userService.updateUser(user);
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

	/**
	 * 去编辑订单页面
	 * 
	 * @return
	 */
	public String toUpdateOrderPage() {
		try {
			if (this.orderData.getId() != null) {
				this.orderData = this.orderService.selectOrderById(this.orderData.getId());
				if(this.orderData!=null) {
					UserDo	userDo = userService.selectUserById(this.orderData.getUserId());
					this.orderData.setUserName(userDo.getUserName());
					if("1".equals(this.orderData.getOrderState())) {
						this.orderData.setOrderStateX("已支付");
					}else if("0".equals(this.orderData.getOrderState())) {
						this.orderData.setOrderStateX("未支付");
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
	 * 去订单相关商品列表页面
	 * 
	 * @return
	 */
	public String toorderFruitListPage() {
		if(this.orderData.getId()!=null) {
			return SUCCESS;
		}else {
			return ERROR;
		}
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