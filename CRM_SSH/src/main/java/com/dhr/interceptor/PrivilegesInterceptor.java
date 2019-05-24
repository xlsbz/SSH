package com.dhr.interceptor;

import org.apache.struts2.ServletActionContext;

import com.dhr.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @author Mr DU 权限拦截
 */
public class PrivilegesInterceptor extends MethodFilterInterceptor {

	/**
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 判断用户是否登陆
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user != null) {
			return invocation.invoke();
		} else {
			// 让用户登录
			ActionSupport support = (ActionSupport) invocation.getAction();
			support.addActionError("你先登录！！！");
			return support.LOGIN;
		}
	}

}
