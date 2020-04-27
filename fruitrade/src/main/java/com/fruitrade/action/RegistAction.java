package com.fruitrade.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.UserDo;
import com.fruitrade.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class RegistAction implements Action, ModelDriven<UserDo> {

	@Autowired
	private UserService userService;
	
	private UserDo userData;
	private int userId;
	

	@Override
	public UserDo getModel() {
		this.userData = new UserDo();
		return this.userData;
	}

	@Override
	public String execute() throws Exception {
		if (StringUtils.isNoneEmpty(this.userData.getAccount())&&StringUtils.isNoneEmpty(this.userData.getPassword())) {
			this.userData.setCreateTime(new Date());
			this.userService.insertUser(this.userData);
			userId = this.userData.getId();
			ActionContext.getContext().getSession().put("user", this.userData);
			return SUCCESS;
		}else {
			return ERROR;
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}