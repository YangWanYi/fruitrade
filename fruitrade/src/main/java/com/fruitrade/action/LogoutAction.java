package com.fruitrade.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class LogoutAction implements Action {

	@Override
	public String execute() throws Exception {
		ActionContext.getContext().getSession().clear();
		return NONE;
	}

}