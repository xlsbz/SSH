package com.dhr.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dhr.domain.User;
import com.dhr.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

/**
 * @author Mr DU 客户
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	// 模型驱动
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	// 注入service
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public String register() {
		// 通过属性驱动可以直接获得页面的数据,注入service
		userService.addUser(user);
		return SUCCESS;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public String loginUser() {
		// 模型驱动已经得到了用户的登陆数据
		User user = userService.loginUser(this.user);
		if (user == null) {
			this.addActionError("用户名或密码错误!");
			return "loginError";

		}
		// 登陆成功，把用户信息设置到session
		ActionContext.getContext().getSession().put("user", user);
		return "loginSuccess";
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findAllUser() throws IOException {

		List<User> list = userService.findAll();
		JSONArray array = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(array.toString());
		return NONE;
	}
}
