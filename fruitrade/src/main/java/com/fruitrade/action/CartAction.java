package com.fruitrade.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.CartDo;
import com.fruitrade.domain.FruitDo;
import com.fruitrade.domain.PurchaseDo;
import com.fruitrade.domain.StorageDo;
import com.fruitrade.domain.SupplyVsFruitDo;
import com.fruitrade.service.CartService;
import com.fruitrade.service.FruitService;
import com.fruitrade.service.PurchaseService;
import com.fruitrade.service.StorageService;
import com.fruitrade.service.SupplyVsFruitService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class CartAction implements Action, ModelDriven<CartDo> {

	@Autowired
	private CartService cartService;
	@Autowired
	private SupplyVsFruitService supplyVsFruitService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private FruitService fruitService;
	@Autowired
	private PurchaseService purchaseService;

	private CartDo cartData;
	private List<CartDo> rows;
	private int total;
	private String ids;

	@Override
	public CartDo getModel() {
		this.cartData = new CartDo();
		return this.cartData;
	}

	/**
	 * 加载数据列表
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<CartDo> cartList = this.cartService.listCart(this.cartData);
			if(cartList.size()>0) {
				PurchaseDo purchaseDo;
				SupplyVsFruitDo supplyVsFruitDo;
				FruitDo fruitDo;
				for (int i = 0; i < cartList.size(); i++) {
					purchaseDo = purchaseService.selectPurchaseById(cartList.get(i).getPurchaseId());
					if(purchaseDo!=null) {
						cartList.get(i).setSalePrice(purchaseDo.getSalePrice());
						supplyVsFruitDo = supplyVsFruitService.selectSupplyVsFruitById(purchaseDo.getSupplyVsFruitId());
						if(supplyVsFruitDo!=null) {
							fruitDo = fruitService.selectFruitById(supplyVsFruitDo.getFruitId());
							if(fruitDo!=null) {
								cartList.get(i).setFruitName(fruitDo.getFruitName());
							}
						}
					}
				}
			}
			this.rows = cartList;
			this.total = cartList.size();
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
	public String saveOrUpdateCart() {
		try {
			PurchaseDo purchaseDo = purchaseService.selectPurchaseById(this.cartData.getPurchaseId());
			if(purchaseDo!=null) {
				StorageDo storageDo = storageService.selectStorageById(purchaseDo.getStorageId());
				if(storageDo.getInventory()!=null&&this.cartData.getPurchaseNum()!=null) {
					storageDo.setInventory(storageDo.getInventory()-this.cartData.getPurchaseNum());
					storageService.updateStorage(storageDo);
				}
				SupplyVsFruitDo supplyVsFruitDo = supplyVsFruitService.selectSupplyVsFruitById(purchaseDo.getSupplyVsFruitId());
				if(supplyVsFruitDo!=null) {
					this.cartData.setFruitId(supplyVsFruitDo.getFruitId());
					FruitDo fruitDo=fruitService.selectFruitById(supplyVsFruitDo.getFruitId());
					if(fruitDo!=null) {
						this.cartData.setFruitName(fruitDo.getFruitName());
					}
				}
			}
			if (this.cartData.getId() != null) { // 存在主键 走编辑
				this.cartService.updateCart(this.cartData);
			} else { // 不存在主键 走新增
				this.cartData.setState(0);// 未生成订单
				this.cartService.insertCart(this.cartData);
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
	public String deleteCart() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				CartDo Cart = new CartDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						Cart.setId(Integer.parseInt(idsArray[i]));
						this.cartService.deleteCart(Cart);
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
	public String toUpdateCartPage() {
		try {
			if (this.cartData.getId() != null) {
				this.cartData = this.cartService.selectCartById(this.cartData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	public List<CartDo> getRows() {
		return rows;
	}

	public void setRows(List<CartDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public CartDo getCartData() {
		return cartData;
	}

	public void setCartData(CartDo cartData) {
		this.cartData = cartData;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}