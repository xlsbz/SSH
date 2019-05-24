package com.dhr.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhr.dao.IUserDao;
import com.dhr.domain.User;
import com.dhr.service.IUserService;
import com.dhr.util.Constant;
import com.dhr.util.MailUtil;
import com.dhr.util.UUIDUtils;

/**
 * 用户业务实现类
 * @author Mr DU
 *
 */
@Transactional
public class UserServiceImpl implements IUserService{

	/**
	 * 注入dao接口
	 */
	private IUserDao userDao;
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User checkName(String name) {
		return userDao.checkName(name);
	}

	@Override
	public void register(User user) {
		//获取随机注册激活码
		String code = UUIDUtils.getUUID();
		//手动设置状态,激活码
		user.setState(Constant.STATE_NOACTIVE);
		user.setCode(code);
		//调用邮件发送工具
		MailUtil.sendMail(Constant.ADDRESS, Constant.PASSWORD, user.getEmail(), code);
		userDao.register(user);
	}

	@Override
	public User findByUser(String code) {
		return userDao.findByUser(code);
	}

	@Override
	public void updateUser(User exitsUser) {
		userDao.updateUser(exitsUser);
	}

	@Override
	public User login(String account, String password) {
		return userDao.loginUser(account,password);
	}


}
