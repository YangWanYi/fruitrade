package com.fruitrade.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.RoleDo;
import com.fruitrade.domain.UserDo;
import com.fruitrade.service.RoleService;
import com.fruitrade.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction implements Action, ModelDriven<UserDo> {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;


	private UserDo userData;
	private List<UserDo> rows;
	private int total;
	private String ids;
	

	@Override
	public UserDo getModel() {
		this.userData = new UserDo();
		return this.userData;
	}
	

	/**
	 * 修改个人信息  个人中心调用
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			if (this.userData.getId() != null) {
				this.userData.setUpdateTime(new Date());
				this.userService.updateUser(this.userData);
				ActionContext.getContext().getSession().put("user", this.userData);// 更新session
				return NONE;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 *新增或编辑用户信息
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		try {
			if (this.userData.getId() != null) { // 存在主键 走编辑 
				this.userData.setUpdateTime(new Date());
				this.userService.updateUser(this.userData);
			} else { // 不存在主键 走新增
				this.userData.setCreateTime(new Date());
				this.userService.insertUser(this.userData);
			}
			return NONE;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
	 *充值
	 * 
	 * @return
	 */
	public String recharge() {
		try {
			if (this.userData.getId() != null) { // 存在主键 走编辑 
				Double num = this.userData.getBalance();
				this.userData = this.userService.selectUserById(this.userData.getId());
				if(this.userData != null) {
					this.userData.setBalance(this.userData.getBalance()==null?num:this.userData.getBalance()+num);
					this.userData.setUpdateTime(new Date());
					this.userService.updateUser(this.userData);
					ActionContext.getContext().getSession().put("user", this.userData);// 更新session
				}else {
					return ERROR;
				}
			} else {
				return ERROR;
			}
			return NONE;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
	 *根据主键批量删除用户
	 * 
	 * @return
	 */
	public String deleteUser() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				UserDo user = new UserDo();
				for (int i = 0; i < idsArray.length; i++) {
					if(StringUtils.isNoneEmpty(idsArray[i])) {
						user.setId(Integer.parseInt(idsArray[i]));
						this.userService.deleteUser(user);
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
	 * 修改密码
	 * 
	 * @param request
	 * @return
	 */
	public String updatePwd() {
		try {
			if (this.userData.getId() != null) {
				String pwd = this.userData.getPassword();
				this.userData = this.userService.selectUserById(this.userData.getId());
				this.userData.setUpdateTime(new Date());
				this.userData.setPassword(pwd);
				this.userService.updateUser(this.userData);
				ActionContext.getContext().getSession().clear();;// 清除session以重新登录
				return NONE;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
	 * 去编辑用户页面
	 * @return
	 */
	public String toUpdateUserPage() {
		try {
			if (this.userData.getId() != null) {
				this.userData = this.userService.selectUserById(this.userData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
	 * 查询所有用户
	 * 
	 * @param request
	 * @return
	 */
	public String listUser() {
		try {
			List<UserDo> userList = this.userService.listUser(this.userData);
			if(userList.size()>0) {
				RoleDo roleDo;
				for (int i = 0; i < userList.size(); i++) {
					if(userList.get(i).getRoleId()!=null) {
						roleDo = roleService.selectRoleById(userList.get(i).getRoleId());
						if(roleDo != null ) {
							userList.get(i).setRoleType(roleDo.getRoleName());
						}
					}
				}
			}
			this.rows = userList;
			this.total = userList.size();
			return NONE;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public List<UserDo> getRows() {
		return rows;
	}

	public void setRows(List<UserDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public UserDo getUserData() {
		return userData;
	}


	public void setUserData(UserDo userData) {
		this.userData = userData;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}
	
}