package com.fruitrade.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.UserDo;
import com.fruitrade.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements Action, ModelDriven<UserDo> {

	private static final long serialVersionUID = -6494039198052194264L;

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
		if (StringUtils.isNoneEmpty(this.userData.getAccount())
				&& StringUtils.isNoneEmpty(this.userData.getPassword())) {
			UserDo user = this.userService.loginUser(this.userData.getAccount(), this.userData.getPassword());
			if (user != null) {
				userId = user.getId();
				ActionContext.getContext().getSession().put("user", user);
				return NONE;
			} else {
				return ERROR;
			}
		} else {
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