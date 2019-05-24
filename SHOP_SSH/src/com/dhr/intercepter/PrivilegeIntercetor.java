package com.dhr.intercepter;


import org.apache.struts2.ServletActionContext;

import com.dhr.domain.Admin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 拦截器
 * @author Mr DU
 *
 */
public class PrivilegeIntercetor extends MethodFilterInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
		if(admin==null) {
			ActionSupport context = (ActionSupport) actionInvocation.getAction();
			context.addActionMessage("您还没有登录！！！");
			return "loginError";
		}else {
			return actionInvocation.invoke();
		}
	}


}
