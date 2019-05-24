package com.dhr.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.dhr.domain.User;
import com.dhr.service.IUserService;
import com.dhr.util.Constant;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户
 * @author Mr DU
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private static final long serialVersionUID = 1L;
	//注入模型驱动
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
	//注入service
	private IUserService userService;
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	//注入验证码字段属性
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	
	/**
	 * 跳转到注册页
	 */
	public String toRegister() {
		return "toRegister";
	}

	
	/**
	 * 验证用户名是否存在
	 */
	public String findByName() {
		//获取用户名称
		User exitsUser = userService.checkName(user.getUsername());
		//获得response
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			if(exitsUser!=null) {
				response.getWriter().println("用户名已经存在!");
			}else {
				response.getWriter().println("恭喜!用户名可以使用");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	/**
	 * 注册用户
	 * 模型驱动自动封装注册传来的数据
	 * @return
	 */
	public String register() {
		//获取验证码信息
		String checkcode = (String) ServletActionContext.getRequest().getSession().getAttribute("code");
		if(this.checkcode.equalsIgnoreCase(checkcode)) {
			userService.register(user);
			this.addActionMessage("恭喜您注册成功，快去邮箱激活吧！");
		}else {
			this.addActionError("验证码输入错误，请重新输入！");
			return "registerError";
		}
		return "msg";
	}
	
	/**
	 * 用户激活
	 * @return
	 */
	public String active() {
		//1.获取code去数据库中查询有没有这个用户
		User exitsUser = userService.findByUser(user.getCode());
		if(exitsUser!=null) {
			//如果查到该用户，则修改用户激活状态，并清理激活码
			exitsUser.setState(Constant.STATE_SUCCESS);
			exitsUser.setCode(null);
			//回写数据库
			userService.updateUser(exitsUser);
			this.addActionMessage("用户激活成功，快去登录吧！");
		}else {
			//没有查到用户则显示失败，重新注册
			this.addActionError("用户激活失败，请重新注册！");
		}
		return "msg";
	}
	
	/**
	 * 去登陆页面
	 * @return
	 */
	public String toLogin() {
		return LOGIN;
	}
	
	/**
	 * 用户登录   邮箱和用户名均可登录
	 * @return
	 */
	public String login() {
		//获取验证码
		String checkcode = (String) ServletActionContext.getRequest().getSession().getAttribute("code");
		if(!checkcode.equalsIgnoreCase(this.checkcode)) {
			this.addActionError("验证码输入错误，请重新输入！");
			return "loginError";
		}else {
			//1.获取用户登录信息
			String username = user.getUsername();
			String password = user.getPassword();
			User u = null;
			u = userService.login(username,password);
			if(u!=null) {
				if(u.getCode()!=null) {
					this.addActionError("用户还没激活，快去邮箱激活吧！");
					return "loginError";
				}
				//将用户信息存到域中
				ServletActionContext.getRequest().getSession().setAttribute("user", u);
			}else {
				this.addActionError("用户名密码错误，请重新登录！");
				return "loginError";
			}
		}
		//清除验证码session
		ServletActionContext.getRequest().getSession().removeAttribute("code");
		return "loginSuccess";
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	public String quit() {
		//清除用户数据session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
