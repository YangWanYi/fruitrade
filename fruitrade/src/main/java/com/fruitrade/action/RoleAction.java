package com.fruitrade.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.RoleDo;
import com.fruitrade.domain.UserDo;
import com.fruitrade.service.RoleService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class RoleAction implements Action, ModelDriven<RoleDo> {

	@Autowired
	private RoleService roleService;

	private RoleDo roleData;
	private List<RoleDo> rows;
	private int total;
	private String ids;

	@Override
	public RoleDo getModel() {
		this.roleData = new RoleDo();
		return this.roleData;
	}

	/**
	 * 加载角色列表
	 * 
	 * @return
	 */
	@Override
	public String execute() {
		try {
			List<RoleDo> roleList = this.roleService.listRole(this.roleData);
			this.rows = roleList;
			this.total = roleList.size();
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
	public String saveOrUpdateRole() {
		try {
			ActionContext actionContext = ActionContext.getContext();
			UserDo user = (UserDo) actionContext.getSession().get("user");
			if (this.roleData.getId() != null) { // 存在主键 走编辑
				this.roleData.setUpdateId(user.getId());
				this.roleData.setUpdateName(user.getUserName());
				this.roleData.setUpdateTime(new Date());
				this.roleService.updateRole(this.roleData);
			} else { // 不存在主键 走新增
				this.roleData.setCreateId(user.getId());
				this.roleData.setCreateName(user.getUserName());
				this.roleData.setCreateTime(new Date());
				this.roleService.insertRole(this.roleData);
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
	public String deleteRole() {
		try {
			if (StringUtils.isNoneEmpty(ids)) {
				String[] idsArray = ids.split(",");
				RoleDo Role = new RoleDo();
				for (int i = 0; i < idsArray.length; i++) {
					if (StringUtils.isNoneEmpty(idsArray[i])) {
						Role.setId(Integer.parseInt(idsArray[i]));
						this.roleService.deleteRole(Role);
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
	public String toUpdateRolePage() {
		try {
			if (this.roleData.getId() != null) {
				this.roleData = this.roleService.selectRoleById(this.roleData.getId());
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	public List<RoleDo> getRows() {
		return rows;
	}

	public void setRows(List<RoleDo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public RoleDo getRoleData() {
		return roleData;
	}

	public void setRoleData(RoleDo roleData) {
		this.roleData = roleData;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}