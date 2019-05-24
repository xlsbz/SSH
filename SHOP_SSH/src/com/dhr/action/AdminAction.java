package com.dhr.action;

import org.apache.struts2.ServletActionContext;

import com.dhr.domain.Admin;
import com.dhr.service.IAdminService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 管理员层
 * @author Mr DU
 *
 */
public class AdminAction extends ActionSupport implements ModelDriven<Admin>{
	
	private Admin admin = new Admin();
	@Override
	public Admin getModel() {
		return admin;
	}
	
	private static final long serialVersionUID = 1L;
	//注入service
	private IAdminService adminService;
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}


	/**
	 * 取登录页
	 * @return
	 */
	public String admin() {
		return "loginPage";
	}
	
	public String login() {
		admin = adminService.loginAdmin(admin);
		if(admin!=null) {
			ServletActionContext.getRequest().getSession().setAttribute("admin", admin);
			return "loginSuccess";
		}else {
			this.addActionError("用户名密码错误！");
			return "loginError";
		}
	}


	
}
